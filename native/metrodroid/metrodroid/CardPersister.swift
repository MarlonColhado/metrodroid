//
//  CardPersister.swift
//
// Copyright 2019 Google
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
//

import Foundation
import metrolib
import ZIPFoundation
import CommonCrypto

class CardPersister {
    class func cardsDirectory() throws -> URL {
        let DocumentsDirectory = FileManager().urls(for: .documentDirectory, in: .userDomainMask).first!
        let dir = DocumentsDirectory.appendingPathComponent("cards")
        try FileManager.default.createDirectory(at: dir, withIntermediateDirectories: true, attributes: nil)
        return dir
    }
    
    class func fileName(card: Card, generation: Int) -> String {
        let base = "Metrodroid-\(card.tagId.toHexString())-\(card.scannedAt.isoDateTimeFilenameFormat())"
        let ext = "json"
        if (generation == 0) {
            return "\(base).\(ext)"
        }
        return "\(base)-\(generation).\(ext)"
    }
    
    class func newFileName(card: Card) throws -> URL {
        var generation = 0
        while true {
            let at = fileName(card: card, generation: generation)
            let u = try cardsDirectory().appendingPathComponent(at)
            if (!FileManager.default.fileExists(atPath: u.path)) {
                return u
            }
            generation += 1
        }
    }
    
    class func persistCard(card: Card, json: String) throws -> URL {
        let fname = try newFileName(card: card)
        try json.write(toFile: fname.path, atomically: true, encoding: String.Encoding.utf8)
        return fname
    }
    
    class func persistCard(card: Card) throws -> URL {
        return try persistCard(card: card, json: CardSerializer.init().toJson(card: card))
    }
    
    class Entry :NSObject {
        let fname: String
        let dateGen: String
        let uid: String
        let date: Date
        
        init?(fname: String) {
            self.fname = fname
            if (!fname.hasPrefix("Metrodroid-") || !fname.hasSuffix(".json")){
                return nil
            }
            
            let main = fname[fname.index(fname.startIndex, offsetBy: 11)..<fname.index(fname.endIndex, offsetBy: -5)]
            guard let sepIdx = main.firstIndex(of: "-") else {
                return nil
            }
            self.uid = String(main[main.startIndex..<sepIdx])
            self.dateGen = String(main[main.index(sepIdx, offsetBy: +1)..<main.endIndex])
            guard let dateSepFirstIdx = self.dateGen.firstIndex(of: "-") else {
                return nil
            }
            guard let dateSepLastIdx = self.dateGen.lastIndex(of: "-") else {
                return nil
            }
            let dateStr: String
            if (dateSepFirstIdx != dateSepLastIdx) {
                dateStr = String(self.dateGen[self.dateGen.startIndex..<dateSepLastIdx])
            } else {
                dateStr = self.dateGen
            }
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = "yyyyMMdd-HHmmss"
            dateFormatter.timeZone = TimeZone.init(secondsFromGMT: 0)
            guard let date = dateFormatter.date(from: dateStr) else {
                return nil
            }
            self.date = date
        }
        
        override var description: String {
            get {
                return "\(fname) [\(uid); \(dateGen)]"
            }
            set {
            }
        }
        
        func getUrl() throws -> URL {
            return try CardPersister.cardsDirectory().appendingPathComponent(fname)
        }
        
        func loadJson() throws -> String? {
            let url = try getUrl()
            return CardPersister.loadJsonAtUrl(url: url)
        }
        
        func load() throws -> Card? {
            guard let json = try loadJson() else {
                return nil
            }
            return try CardSerializer.init().fromPersist(input: json)
        }
        
        func delete() throws {
            try FileManager.default.removeItem(at: getUrl())
        }
        
        func getSha512() throws -> String {
            let ret = try sha512(url: getUrl())
            print("\(fname) -> \(ret)")
            return ret
        }
    }
    
    class func loadJsonAtUrl(url: URL) -> String? {
        guard let jsonRaw = FileManager.default.contents(atPath: url.path) else {
            return nil
        }
        return String(data: jsonRaw, encoding: .utf8)!
    }
    
    class func listCards() throws -> [Entry] {
        return try FileManager.default.contentsOfDirectory(atPath: cardsDirectory().path).compactMap { Entry (fname: $0) }.sorted(by: {
            a,b in a.date > b.date
        })
    }
    
    class func makeTempFile() throws -> URL {
        let temporaryDirectoryURL = URL(fileURLWithPath: NSTemporaryDirectory(),
                                        isDirectory: true)
        let dir =  temporaryDirectoryURL.appendingPathComponent("sharezip")
        try FileManager.default.createDirectory(at: dir, withIntermediateDirectories: true, attributes: nil)
        return dir.appendingPathComponent("Metrodroid-" + UUID.init().uuidString + ".zip")
    }

    class func addZipFileFromString(archive: Archive, name: String, contents: String) throws {
        let bin = contents.data(using: .utf8)!
        
        try archive.addEntry(with: name, type: .file, uncompressedSize: UInt32(bin.count),
                             provider: { (position, size) -> Data in return bin}
        )
    }
    
    class func makeZip() throws -> URL {
        let url = try makeTempFile()
        try FileManager.default.zipItem(at: cardsDirectory(), to: url, shouldKeepParent: false)
        let archive = Archive(url: url, accessMode: .update)!
        let now = TimestampFull.Companion.init().now()
        try addZipFileFromString(archive: archive, name: "README." + Localizer.init().language + ".txt",
                             contents: Utils.localizeString(RKt.R.string.exported_at, now.format()) + Utils.getDeviceString())
        try addZipFileFromString(archive: archive, name: "README.txt",
                             contents: Utils.englishString(RKt.R.string.exported_at, now.isoDateTimeFormat()) + Utils.getDeviceStringEnglish())

        return url
    }
    
    class func readBinaryEntry(arch: Archive, entry: ZIPFoundation.Entry) throws -> Data {
        let res = NSMutableData(length: 0)!
        _ = try arch.extract(entry, consumer: { (data) in
            res.append(data)
        })
        return res as Data
    }
    
    class func readStringEntry(arch: Archive, entry: ZIPFoundation.Entry) throws -> String {
        return String(data: try readBinaryEntry(arch: arch, entry: entry), encoding: .utf8)!
    }
    
    class func readJson(jsonUrl: URL) throws -> (Card?, URL?, Int) {
        let data = try Data(contentsOf: jsonUrl)
        try FileManager.default.removeItem(at: jsonUrl)
        let card = try CardSerializer.init().fromPersist(input: String(data: data, encoding: .utf8)!)
        let importUrl = try CardPersister.persistCard(card: card)
        return (card, importUrl, 1)
    }
    
    class func readZip(zipUrl: URL) throws -> (Card?, URL?, Int) {
        let arch = Archive(url: zipUrl, accessMode: .read)!
        var count = 0
        let jsonFormat = JsonKotlinFormat()
        let mfcFormat = MfcCardImporter()
        var singleCard: Card? = nil
        var singleURL: URL? = nil
        for entry in arch.makeIterator() {
            let name = entry.path(using: .utf8)
            if (name.hasSuffix(".json")) {
                let str = try readStringEntry(arch: arch, entry: entry)
                let card = jsonFormat.readCard(input: str)
                let url = try persistCard(card: card)
                count += 1
                if count == 1 {
                    singleCard = card
                    singleURL = url
                } else {
                    singleCard = nil
                    singleURL = nil
                }
                continue
            }
            if (name.hasSuffix(".mfc")) {
                let bin = try readBinaryEntry(arch: arch, entry: entry)
                let card = mfcFormat.readCard(bin: UtilsKt.toByteArray(bin))
                let url = try persistCard(card: card)
                count += 1
                if count == 1 {
                    singleCard = card
                    singleURL = url
                } else {
                    singleCard = nil
                    singleURL = nil
                }
                continue
            }
        }
        
        return (singleCard, singleURL, count)
    }
    
    class func readAutodetect(url: URL) throws -> (Card?, URL?, Int) {
        let i = InputStream.init(url: url)!
        i.open()
        let head = UnsafeMutablePointer<UInt8>.allocate(capacity: 16)
        defer {
            head.deallocate()
        }
        let r = i.read(head, maxLength: 1)
        i.close()
        print("Head byte \(head[0]) [\(r)] \(String(describing: i.streamError))")
        if head[0] == 0x7b {
            return try readJson(jsonUrl: url)
        }
        
        if head[0] == 0x50 {
            return try readZip(zipUrl: url)
        }
        
        return (nil, nil, 0)
    }
    
    class func sha512(url: URL) throws -> String {
        let bufSize = 8192
        let i = InputStream.init(url: url)!
        i.open()
        defer {
            i.close()
        }
        let buf = UnsafeMutablePointer<UInt8>.allocate(capacity: bufSize)
        defer {
            buf.deallocate()
        }
        var context = CC_SHA512_CTX()
        CC_SHA512_Init(&context)
        while true {
            let r = i.read(buf, maxLength: bufSize)
            if r <= 0 {
                break
            }
            CC_SHA512_Update(&context, buf, numericCast(r))
        }
        
        var digest = Data(count: Int(CC_SHA512_DIGEST_LENGTH))
        digest.withUnsafeMutableBytes {
            _ = CC_SHA512_Final($0, &context)
        }
        
        return digest.map { String(format: "%02hhx", $0) }.joined()
    }
    
    class func dedup() throws -> Int {
        let hashes = NSMutableSet()
        var count = 0
        for card in try listCards() {
            let hash = try card.getSha512()
            if hashes.contains(hash) {
                try card.delete()
                count += 1
            }
            hashes.add(hash)
        }
        return count
    }
}
