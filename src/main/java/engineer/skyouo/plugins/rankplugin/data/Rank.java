package engineer.skyouo.plugins.rankplugin.data;

import java.util.ArrayList;
import java.util.List;

public enum Rank {
    Zero("&7無", 0, 0),
    One("&b青銅三&r", 100, 600),
    Two("&b青銅二&r", 238, 1000),
    Three("&b青銅一&r", 412, 3500),
    Four("&b白銀三&r", 632, 5700),
    Five("&b白銀二&r", 910, 8400),
    Six("&b白銀一&r", 1292, 11600),
    Seven("&a黃金三&r", 1856, 15300),
    Eight("&a黃金二&r", 2602, 19500),
    Nine("&a黃金一&r", 3502, 24200),
    Ten("&a鉑金三&r", 4556, 29400),
    Eleven("&a鉑金二&r", 5801, 35100),
    Twelve("&a鉑金一&r", 7271, 41300),
    Thirteen("&e鑽石三&r", 9006, 48000),
    Fourteen("&e鑽石二&r", 11034, 52000),
    Fifteen("&e鑽石一&r", 13309, 62900),
    Sixteen("&e星曜三&r", 15905, 71100),
    Seventeen("&e星曜二&r", 18560, 79800),
    Eighteen("&e星曜一&r", 23289, 89000),
    Nineteen("&6璀璨三&r", 29594, 98700),
    Twenty("&6璀璨二&r", 32326, 100700),
    TwentyOne("&6璀璨一&r", 42414, 102720),
    TwentyTwo("&c菁英二&r", 54520, 104760),
    TwentyThree("&c菁英一&r", 71951, 106820),
    TOP("&d輝煌一&r", 104551, 120000);

    String prefix;
    int expRequired;
    int moneyReward;

    Rank(String prefix, int expRequired, int moneyReward) {
        this.prefix = prefix;
        this.expRequired = expRequired;
        this.moneyReward = moneyReward;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpRequired() {
        return expRequired;
    }

    public int getMoneyReward() {
        return moneyReward;
    }

    public static Rank getRankByEXP(double exp) {
        Rank result = Rank.Zero;
        for (Rank rank : Rank.values()) {
            if (exp >= rank.expRequired) {
                result = rank;
            } else break;
        }

        return result;
    }

    public static Rank getNextLevel(double exp) {
        Rank result = Rank.One;
        for (Rank rank : Rank.values()) {
            if (exp >= rank.expRequired)
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
            if (exp >= rank.expRequired) {
                result.add(rank);
            } else break;
        }

        return result;
    }
}
