/*
 * LuhnTest.java
 *
 * Copyright 2016-2018 Michael Farrell <micolous+git@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package au.id.micolous.metrodroid.test

import au.id.micolous.metrodroid.util.Utils
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Testing the Luhn checksum calculator
 */
class LuhnTest {
    @Test
    fun testValidation() {
        assertTrue(Utils.validateLuhn("14455833625"))
        assertTrue(Utils.validateLuhn("2132023611"))
        assertTrue(Utils.validateLuhn("22278878354"))
        assertTrue(Utils.validateLuhn("16955109885"))
        assertTrue(Utils.validateLuhn("20705769295"))
        assertTrue(Utils.validateLuhn("5141418763"))
        assertTrue(Utils.validateLuhn("13076501629"))
        assertTrue(Utils.validateLuhn("26625862995"))
        assertTrue(Utils.validateLuhn("13622972688"))
        assertTrue(Utils.validateLuhn("11981944561"))
        assertTrue(Utils.validateLuhn("7868205860"))
        assertTrue(Utils.validateLuhn("12769832796"))
        assertTrue(Utils.validateLuhn("13738153843"))
        assertTrue(Utils.validateLuhn("33032358864"))
        assertTrue(Utils.validateLuhn("17675980209"))
        assertTrue(Utils.validateLuhn("17992698740"))
        assertTrue(Utils.validateLuhn("23711490617"))
        assertTrue(Utils.validateLuhn("25099325414"))
        assertTrue(Utils.validateLuhn("32328053437"))
        assertTrue(Utils.validateLuhn("5468460836"))
        assertTrue(Utils.validateLuhn("7326462152"))
        assertTrue(Utils.validateLuhn("20546726827"))
        assertTrue(Utils.validateLuhn("900318908"))
        assertTrue(Utils.validateLuhn("28759945042"))
        assertTrue(Utils.validateLuhn("26024096005"))
        assertTrue(Utils.validateLuhn("32803807406"))
        assertTrue(Utils.validateLuhn("41950380174"))
        assertTrue(Utils.validateLuhn("7144685935"))
        assertTrue(Utils.validateLuhn("200247740"))
        assertTrue(Utils.validateLuhn("3580259228"))
        assertTrue(Utils.validateLuhn("35103155830"))
        assertTrue(Utils.validateLuhn("38832859524"))
        assertTrue(Utils.validateLuhn("15520499730"))
        assertTrue(Utils.validateLuhn("42895092221"))
        assertTrue(Utils.validateLuhn("42445712377"))
        assertTrue(Utils.validateLuhn("23589471772"))
        assertTrue(Utils.validateLuhn("24185368255"))
        assertTrue(Utils.validateLuhn("27584849593"))
        assertTrue(Utils.validateLuhn("14286020574"))
        assertTrue(Utils.validateLuhn("10209508851"))
        assertTrue(Utils.validateLuhn("12103634601"))
        assertTrue(Utils.validateLuhn("9882041909"))
        assertTrue(Utils.validateLuhn("21735085231"))
        assertTrue(Utils.validateLuhn("26734471720"))
        assertTrue(Utils.validateLuhn("660001215"))
        assertTrue(Utils.validateLuhn("34667618408"))
        assertTrue(Utils.validateLuhn("23145570083"))
        assertTrue(Utils.validateLuhn("9885843319"))
        assertTrue(Utils.validateLuhn("7579437711"))
        assertTrue(Utils.validateLuhn("32784123336"))
        assertTrue(Utils.validateLuhn("7847703084"))
        assertTrue(Utils.validateLuhn("21127514533"))
        assertTrue(Utils.validateLuhn("632990271"))
        assertTrue(Utils.validateLuhn("33021014510"))
        assertTrue(Utils.validateLuhn("11666056244"))
        assertTrue(Utils.validateLuhn("35440463616"))
        assertTrue(Utils.validateLuhn("15409942420"))
        assertTrue(Utils.validateLuhn("39828628881"))
        assertTrue(Utils.validateLuhn("16118274394"))
        assertTrue(Utils.validateLuhn("12211164111"))
        assertTrue(Utils.validateLuhn("9604520834"))
        assertTrue(Utils.validateLuhn("22614593253"))
        assertTrue(Utils.validateLuhn("25859215862"))
        assertTrue(Utils.validateLuhn("23067679268"))
        assertTrue(Utils.validateLuhn("28214834377"))
        assertTrue(Utils.validateLuhn("28781966271"))
        assertTrue(Utils.validateLuhn("3811009145"))
        assertTrue(Utils.validateLuhn("25973242313"))
        assertTrue(Utils.validateLuhn("14198135569"))
        assertTrue(Utils.validateLuhn("26997711937"))
        assertTrue(Utils.validateLuhn("24467620969"))
        assertTrue(Utils.validateLuhn("6556551593"))
        assertTrue(Utils.validateLuhn("1557591078"))
        assertTrue(Utils.validateLuhn("27628820907"))
        assertTrue(Utils.validateLuhn("5311479991"))
        assertTrue(Utils.validateLuhn("12002033574"))
        assertTrue(Utils.validateLuhn("32934191498"))
        assertTrue(Utils.validateLuhn("20720982733"))
        assertTrue(Utils.validateLuhn("38009252107"))
        assertTrue(Utils.validateLuhn("33292581635"))
        assertTrue(Utils.validateLuhn("7681531666"))
        assertTrue(Utils.validateLuhn("26341189681"))
        assertTrue(Utils.validateLuhn("22497297667"))
        assertTrue(Utils.validateLuhn("26097655984"))
        assertTrue(Utils.validateLuhn("15925093864"))
        assertTrue(Utils.validateLuhn("3645297643"))
        assertTrue(Utils.validateLuhn("37672018977"))
        assertTrue(Utils.validateLuhn("27585874590"))
        assertTrue(Utils.validateLuhn("5346444127"))
        assertTrue(Utils.validateLuhn("26083423199"))
        assertTrue(Utils.validateLuhn("19272674524"))
        assertTrue(Utils.validateLuhn("7431451645"))
        assertTrue(Utils.validateLuhn("9742753537"))
        assertTrue(Utils.validateLuhn("10462043414"))
        assertTrue(Utils.validateLuhn("8992851777"))
        assertTrue(Utils.validateLuhn("5384023908"))
        assertTrue(Utils.validateLuhn("7618265594"))
        assertTrue(Utils.validateLuhn("34876414250"))
        assertTrue(Utils.validateLuhn("29661424837"))
        assertTrue(Utils.validateLuhn("4531175455"))
    }

    @Test
    fun testInvalidation() {
        assertFalse(Utils.validateLuhn("4139648926"))
        assertFalse(Utils.validateLuhn("1694387920"))
        assertFalse(Utils.validateLuhn("258151280"))
        assertFalse(Utils.validateLuhn("314237730"))
        assertFalse(Utils.validateLuhn("423646643"))
        assertFalse(Utils.validateLuhn("4189231277"))
        assertFalse(Utils.validateLuhn("3941601643"))
        assertFalse(Utils.validateLuhn("3049254051"))
        assertFalse(Utils.validateLuhn("2324038570"))
        assertFalse(Utils.validateLuhn("2318610013"))
        assertFalse(Utils.validateLuhn("3424436428"))
        assertFalse(Utils.validateLuhn("2547597866"))
        assertFalse(Utils.validateLuhn("93214216"))
        assertFalse(Utils.validateLuhn("1118934985"))
        assertFalse(Utils.validateLuhn("2533600774"))
        assertFalse(Utils.validateLuhn("2773955884"))
        assertFalse(Utils.validateLuhn("2586548382"))
        assertFalse(Utils.validateLuhn("319313528"))
        assertFalse(Utils.validateLuhn("3788114908"))
        assertFalse(Utils.validateLuhn("3865367972"))
        assertFalse(Utils.validateLuhn("2379273829"))
        assertFalse(Utils.validateLuhn("1889557132"))
        assertFalse(Utils.validateLuhn("3740082978"))
        assertFalse(Utils.validateLuhn("477182936"))
        assertFalse(Utils.validateLuhn("4079410192"))
        assertFalse(Utils.validateLuhn("242136626"))
        assertFalse(Utils.validateLuhn("3654739564"))
        assertFalse(Utils.validateLuhn("2681152772"))
        assertFalse(Utils.validateLuhn("3543499891"))
        assertFalse(Utils.validateLuhn("2701898946"))
        assertFalse(Utils.validateLuhn("3064898346"))
        assertFalse(Utils.validateLuhn("2086310111"))
        assertFalse(Utils.validateLuhn("315035024"))
        assertFalse(Utils.validateLuhn("403593642"))
        assertFalse(Utils.validateLuhn("1066883963"))
        assertFalse(Utils.validateLuhn("2726445073"))
        assertFalse(Utils.validateLuhn("3937438646"))
        assertFalse(Utils.validateLuhn("2534677247"))
        assertFalse(Utils.validateLuhn("3387630627"))
        assertFalse(Utils.validateLuhn("2006818881"))
        assertFalse(Utils.validateLuhn("4032867810"))
        assertFalse(Utils.validateLuhn("1095257309"))
        assertFalse(Utils.validateLuhn("2841923898"))
        assertFalse(Utils.validateLuhn("1331063085"))
        assertFalse(Utils.validateLuhn("116236061"))
        assertFalse(Utils.validateLuhn("1967204659"))
        assertFalse(Utils.validateLuhn("416070218"))
        assertFalse(Utils.validateLuhn("1057178451"))
        assertFalse(Utils.validateLuhn("3319596230"))
        assertFalse(Utils.validateLuhn("2673774471"))
        assertFalse(Utils.validateLuhn("3963343113"))
        assertFalse(Utils.validateLuhn("936531716"))
        assertFalse(Utils.validateLuhn("382724971"))
        assertFalse(Utils.validateLuhn("904105927"))
        assertFalse(Utils.validateLuhn("1871391278"))
        assertFalse(Utils.validateLuhn("3130081581"))
        assertFalse(Utils.validateLuhn("4059361904"))
        assertFalse(Utils.validateLuhn("3714616229"))
        assertFalse(Utils.validateLuhn("4015708833"))
        assertFalse(Utils.validateLuhn("3519864641"))
        assertFalse(Utils.validateLuhn("2706248333"))
        assertFalse(Utils.validateLuhn("388265254"))
        assertFalse(Utils.validateLuhn("175583925"))
        assertFalse(Utils.validateLuhn("3272693851"))
        assertFalse(Utils.validateLuhn("3296821468"))
        assertFalse(Utils.validateLuhn("4057853413"))
        assertFalse(Utils.validateLuhn("1710156309"))
        assertFalse(Utils.validateLuhn("3823186111"))
        assertFalse(Utils.validateLuhn("3466869908"))
        assertFalse(Utils.validateLuhn("2321599513"))
        assertFalse(Utils.validateLuhn("3057128038"))
        assertFalse(Utils.validateLuhn("953972225"))
        assertFalse(Utils.validateLuhn("395188"))
        assertFalse(Utils.validateLuhn("2078905303"))
        assertFalse(Utils.validateLuhn("1276633190"))
        assertFalse(Utils.validateLuhn("2507894399"))
        assertFalse(Utils.validateLuhn("277038187"))
        assertFalse(Utils.validateLuhn("412128760"))
        assertFalse(Utils.validateLuhn("2943125634"))
        assertFalse(Utils.validateLuhn("776811136"))
        assertFalse(Utils.validateLuhn("3399817169"))
        assertFalse(Utils.validateLuhn("2611010924"))
        assertFalse(Utils.validateLuhn("661442521"))
        assertFalse(Utils.validateLuhn("1215280457"))
        assertFalse(Utils.validateLuhn("2815909804"))
        assertFalse(Utils.validateLuhn("1238511920"))
        assertFalse(Utils.validateLuhn("1308763876"))
    }

    @Test
    fun testCalculation() {
        assertEquals(4, Utils.calculateLuhn("3524280191"))
        assertEquals(7, Utils.calculateLuhn("2162879206"))
        assertEquals(9, Utils.calculateLuhn("468820099"))
        assertEquals(5, Utils.calculateLuhn("1841157647"))
        assertEquals(4, Utils.calculateLuhn("1545923558"))
        assertEquals(8, Utils.calculateLuhn("3505726769"))
        assertEquals(4, Utils.calculateLuhn("1270456073"))
        assertEquals(2, Utils.calculateLuhn("1350238745"))
        assertEquals(5, Utils.calculateLuhn("297648390"))
        assertEquals(6, Utils.calculateLuhn("1843301911"))
        assertEquals(3, Utils.calculateLuhn("855896294"))
        assertEquals(4, Utils.calculateLuhn("1339351812"))
        assertEquals(5, Utils.calculateLuhn("2931244069"))
        assertEquals(0, Utils.calculateLuhn("4293179176"))
        assertEquals(2, Utils.calculateLuhn("1039761808"))
        assertEquals(9, Utils.calculateLuhn("582144696"))
        assertEquals(0, Utils.calculateLuhn("191657718"))
        assertEquals(8, Utils.calculateLuhn("2577191480"))
        assertEquals(1, Utils.calculateLuhn("4272424725"))
        assertEquals(7, Utils.calculateLuhn("1347722771"))
        assertEquals(6, Utils.calculateLuhn("4291357200"))
        assertEquals(5, Utils.calculateLuhn("2367098207"))
        assertEquals(6, Utils.calculateLuhn("3267329712"))
        assertEquals(7, Utils.calculateLuhn("210530659"))
        assertEquals(9, Utils.calculateLuhn("2778144206"))
        assertEquals(9, Utils.calculateLuhn("2702657753"))
        assertEquals(1, Utils.calculateLuhn("1467634285"))
        assertEquals(3, Utils.calculateLuhn("10756416"))
        assertEquals(1, Utils.calculateLuhn("2018745132"))
        assertEquals(8, Utils.calculateLuhn("258813855"))
        assertEquals(0, Utils.calculateLuhn("2045829124"))
        assertEquals(1, Utils.calculateLuhn("2462276418"))
        assertEquals(1, Utils.calculateLuhn("2898416195"))
        assertEquals(8, Utils.calculateLuhn("1406469808"))
        assertEquals(5, Utils.calculateLuhn("485914030"))
        assertEquals(0, Utils.calculateLuhn("3349988592"))
        assertEquals(3, Utils.calculateLuhn("890535187"))
        assertEquals(4, Utils.calculateLuhn("464388418"))
        assertEquals(3, Utils.calculateLuhn("4110810463"))
        assertEquals(5, Utils.calculateLuhn("4089731496"))
        assertEquals(9, Utils.calculateLuhn("1323902639"))
        assertEquals(3, Utils.calculateLuhn("2710573885"))
        assertEquals(6, Utils.calculateLuhn("1902004343"))
        assertEquals(8, Utils.calculateLuhn("4037723041"))
        assertEquals(4, Utils.calculateLuhn("836953707"))
        assertEquals(9, Utils.calculateLuhn("2586413396"))
        assertEquals(9, Utils.calculateLuhn("3157553598"))
        assertEquals(0, Utils.calculateLuhn("4036721495"))
        assertEquals(6, Utils.calculateLuhn("829504720"))
        assertEquals(2, Utils.calculateLuhn("1825557101"))
        assertEquals(9, Utils.calculateLuhn("3195187675"))
        assertEquals(2, Utils.calculateLuhn("1853435002"))
        assertEquals(6, Utils.calculateLuhn("1201030091"))
        assertEquals(7, Utils.calculateLuhn("1549083952"))
        assertEquals(1, Utils.calculateLuhn("3600954721"))
        assertEquals(2, Utils.calculateLuhn("2228034841"))
        assertEquals(8, Utils.calculateLuhn("1846380485"))
        assertEquals(6, Utils.calculateLuhn("3299485817"))
        assertEquals(7, Utils.calculateLuhn("4266356531"))
        assertEquals(4, Utils.calculateLuhn("80494393"))
        assertEquals(1, Utils.calculateLuhn("3338502087"))
        assertEquals(4, Utils.calculateLuhn("1210755169"))
        assertEquals(8, Utils.calculateLuhn("4126449397"))
        assertEquals(0, Utils.calculateLuhn("1362375873"))
        assertEquals(0, Utils.calculateLuhn("3113577816"))
        assertEquals(5, Utils.calculateLuhn("1188635514"))
        assertEquals(1, Utils.calculateLuhn("2946063998"))
        assertEquals(0, Utils.calculateLuhn("1719371154"))
        assertEquals(3, Utils.calculateLuhn("1895514650"))
        assertEquals(4, Utils.calculateLuhn("2080829998"))
        assertEquals(3, Utils.calculateLuhn("3609894519"))
        assertEquals(2, Utils.calculateLuhn("3511856319"))
        assertEquals(5, Utils.calculateLuhn("1952932537"))
        assertEquals(4, Utils.calculateLuhn("1910620955"))
        assertEquals(1, Utils.calculateLuhn("935913671"))
        assertEquals(9, Utils.calculateLuhn("725760186"))
        assertEquals(4, Utils.calculateLuhn("233933984"))
        assertEquals(7, Utils.calculateLuhn("1968137531"))
        assertEquals(7, Utils.calculateLuhn("3437612629"))
        assertEquals(4, Utils.calculateLuhn("3516015717"))
        assertEquals(0, Utils.calculateLuhn("1945185765"))
        assertEquals(7, Utils.calculateLuhn("207931382"))
        assertEquals(3, Utils.calculateLuhn("2373789959"))
        assertEquals(1, Utils.calculateLuhn("3847636398"))
        assertEquals(1, Utils.calculateLuhn("1062556296"))
        assertEquals(1, Utils.calculateLuhn("4085951795"))
        assertEquals(5, Utils.calculateLuhn("2630252765"))
        assertEquals(0, Utils.calculateLuhn("3970196936"))
        assertEquals(4, Utils.calculateLuhn("21259608"))
        assertEquals(7, Utils.calculateLuhn("2238013911"))
        assertEquals(3, Utils.calculateLuhn("1319502209"))
        assertEquals(9, Utils.calculateLuhn("895861044"))
        assertEquals(9, Utils.calculateLuhn("1585306656"))
        assertEquals(9, Utils.calculateLuhn("3367246111"))
        assertEquals(8, Utils.calculateLuhn("903071289"))
        assertEquals(3, Utils.calculateLuhn("2430231960"))
        assertEquals(9, Utils.calculateLuhn("345922272"))
        assertEquals(8, Utils.calculateLuhn("1233909707"))
        assertEquals(5, Utils.calculateLuhn("2553083072"))
        assertEquals(8, Utils.calculateLuhn("3053346265"))
    }
}