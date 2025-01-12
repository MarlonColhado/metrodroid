//
//  BalanceViewCell.swift
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
import UIKit
import metrolib

class BalanceViewCell : UICollectionViewCell {
    @IBOutlet weak var balanceLabel: UILabel?
    
    @IBOutlet weak var nameLabel: UILabel?
    @IBOutlet weak var validityLabel: UILabel?
    func setBalance(_ b: TransitBalance) {
        balanceLabel?.attributedText = b.balance.maybeObfuscateBalance()
            .formatCurrencyString(isBalance: true).attributed
        nameLabel?.text = b.name
        validityLabel?.attributedText = TransitBalanceCompanion.init().formatValidity(balance: b)?.attributed
    }
}

