package engineer.skyouo.plugins.rankplugin.data;

import java.util.ArrayList;
import java.util.List;

public enum Rank {
    Zero("&7無", 0, 0),
    One("&b青銅三&r", 100, 600),
    Two("&b青銅二&r", 100+138, 1000),
    Three("&b青銅一&r", 100+138+174, 3500),
    Four("&b白銀三&r", 100+138+174+220, 5700),
    Five("&b白銀二&r", 100+138+174+220+278, 8400),
    Six("&b白銀一&r", 100+138+174+220+278+382, 11600),
    Seven("&a黃金三&r", 100+138+174+220+278+382+564, 15300),
    Eight("&a黃金二&r", 100+138+174+220+278+382+564+746, 19500),
    Nine("&a黃金一&r", 100+138+174+220+278+382+564+746+900, 24200),
    Ten("&a鉑金三&r", 100+138+174+220+278+382+564+746+900+1054, 29400),
    Eleven("&a鉑金二&r", 100+138+174+220+278+382+564+746+900+1054+1245, 35100),
    Twelve("&a鉑金一&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470, 41300),
    Thirteen("&e鑽石三&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735, 48000),
    Fourteen("&e鑽石二&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028, 52000),
    Fifteen("&e鑽石一&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275, 62900),
    Sixteen("&e星曜三&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596, 71100),
    Seventeen("&e星曜二&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655, 79800),
    Eighteen("&e星曜一&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729, 89000),
    Nineteen("&6璀璨三&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729+6305, 98700),
    Twenty("&6璀璨二&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729+630+8407, 100700),
    TwentyOne("&6璀璨一&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729+630+8407+10088, 102720),
    TwentyTwo("&c菁英二&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729+630+8407+10088+12106, 104760),
    TwentyThree("&c菁英一&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729+630+8407+10088+12106+17431, 106820),
    TOP("&d輝煌一&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729+630+8407+10088+12106+17431+32600, 120000);

    String prefix;
    int expReqiured;
    int moneyReward;

    Rank(String prefix, int expReqiured, int moneyReward) {
        this.prefix = prefix;
        this.expReqiured = expReqiured;
        this.moneyReward = moneyReward;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpReqiured() {
        return expReqiured;
    }

    public int getMoneyReward() {
        return moneyReward;
    }

    public static Rank getRankByEXP(double exp) {
        Rank result = Rank.Zero;
        for (Rank rank : Rank.values()) {
            if (exp >= rank.expReqiured) {
                result = rank;
            } else break;
        }

        return result;
    }

    public static Rank getNextLevel(double exp) {
        Rank result = Rank.One;
        for (Rank rank : Rank.values()) {
            if (exp >= rank.expReqiured)
                continue;
            else {
                result = rank;
                break;
            }
        }

        return result;
    }

    public static List<Rank> getAllRankUserHas(double exp) {
        ArrayList<Rank> result = new ArrayList();
        for (Rank rank : Rank.values()) {
            if (exp >= rank.expReqiured) {
                result.add(rank);
            } else break;
        }

        return result;
    }
}
