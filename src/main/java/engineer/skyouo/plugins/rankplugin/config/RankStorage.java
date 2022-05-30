package engineer.skyouo.plugins.rankplugin.config;

import com.handy.playertitle.api.PlayerTitleApi;
import com.handy.playertitle.api.param.TitleListParam;
import com.handy.playertitle.constants.BuyTypeEnum;
import engineer.skyouo.plugins.rankplugin.data.Rank;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class RankStorage {
    private final YamlConfiguration configuration;

    public RankStorage() {
        new File("plugins/RankPlugin").mkdirs();

        File file = new File("plugins/RankPlugin/rank.yml");
        configuration = YamlConfiguration.loadConfiguration(file);

        if (!configuration.getBoolean("init", false)) {
            for (Rank rank : Rank.values()) {
                if (rank.equals(Rank.Zero)) continue;

                TitleListParam titleListParam = new TitleListParam();
                titleListParam.setTitleName(rank.getPrefix());
                titleListParam.setBuyType(BuyTypeEnum.NOT.getBuyType());
                titleListParam.setAmount(0);
                titleListParam.setDay(0);
                titleListParam.setItemStack(null);
                titleListParam.setIsHide(1);
                titleListParam.setDescription("擁有 " + rank.getExpReqiured() + " 點經驗後可解鎖該稱號.");

                titleListParam.setIsPrefixAndSuffix(true);

                titleListParam.setTitleBuffs(Arrays.asList());
                titleListParam.setTitleParticle(null);

                long id = PlayerTitleApi.getInstance().add(titleListParam);

                configuration.set(rank.name(), id);
            }

            configuration.set("init", true);
            try {
                configuration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public long get(Rank rank) {
        return configuration.getLong(rank.name());
    }
}
