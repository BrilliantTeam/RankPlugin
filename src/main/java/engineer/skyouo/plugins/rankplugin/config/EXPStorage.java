package engineer.skyouo.plugins.rankplugin.config;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EXPStorage {
    private YamlConfiguration configuration;

    public EXPStorage() {
        new File("plugins/RankPlugin").mkdirs();

        configuration = YamlConfiguration.loadConfiguration(new File("plugins/RankPlugin/data.yml"));
    }

    public EXPData get(OfflinePlayer player) {
        return (EXPData) configuration.get(String.valueOf(player.getUniqueId()), new EXPData(player, 0.0));
    }

    public void set(OfflinePlayer player, EXPData data) {
        configuration.set(String.valueOf(player.getUniqueId()), data);
    }

    public void save() throws IOException {
        Objects.requireNonNull(configuration).save(new File("plugins/RankPlugin/data.yml"));
    }

    public void shutdown() throws IOException {
        this.save();
        configuration = null;
    }

    public List<EXPData> getSort() {
        List<EXPData> result = new ArrayList<>();
        for (String key : configuration.getKeys(true)) {
            result.add((EXPData) configuration.get(key));
        }

        result.sort((a, b) -> (int) (b.getExp() - a.getExp()));
        return result;
    }
}
