package engineer.skyouo.plugins.rankplugin.data;

import java.util.ArrayList;
import java.util.List;

public enum Rank {
    Zero("&7無", 0, 0),
    One("&b初級信徒&r", 100, 600),
    Two("&b中級使者&r", 100+138, 1000),
    Three("&b上級使徒&r", 100+138+174, 3500),
    Four("&b高級執事&r", 100+138+174+220, 5700),
    Five("&b聖級司鐸&r", 100+138+174+220+278, 8400),
    Six("&a王級首長&r", 100+138+174+220+278+382, 11600),
    Seven("&a皇級院長&r", 100+138+174+220+278+382+564, 15300),
    Eight("&a帝級主教&r", 100+138+174+220+278+382+564+746, 19500),
    Nine("&a神級教宗&r", 100+138+174+220+278+382+564+746+900, 24200),
    Ten("&a終級魔王&r", 100+138+174+220+278+382+564+746+900+1054, 29400),
    Eleven("&e史詩男爵&r", 100+138+174+220+278+382+564+746+900+1054+1245, 35100),
    Twelve("&e奇蹟子爵&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470, 41300),
    Thirteen("&e傳奇伯爵&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735, 48000),
    Fourteen("&e傳說侯爵&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028, 52000),
    Fifteen("&e神聖公爵&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275, 62900),
    Sixteen("&6青銅階級&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596, 71100),
    Seventeen("&6白銀階級&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655, 79800),
    Eighteen("&6黃金階級&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729, 89000),
    Nineteen("&6鉑金階級&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729+6305, 98700),
    Twenty("&6鑽石階級&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729+630+8407, 100700),
    TwentyOne("&c星曜階級&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729+630+8407+10088, 102720),
    TwentyTwo("&c璀璨階級&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729+630+8407+10088+12106, 104760),
    TwentyThree("&c菁英階級&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729+630+8407+10088+12106+17431, 106820),
    TOP("&d輝煌之星&r", 100+138+174+220+278+382+564+746+900+1054+1245+1470+1735+2028+2275+2596+2655+4729+630+8407+10088+12106+17431+32600, 120000);

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
