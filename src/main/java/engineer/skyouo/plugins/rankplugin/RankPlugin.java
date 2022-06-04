package engineer.skyouo.plugins.rankplugin;

import com.handy.playertitle.api.PlayerTitleApi;
import engineer.skyouo.plugins.rankplugin.commands.RankExpCommand;
import engineer.skyouo.plugins.rankplugin.config.EXPData;
import engineer.skyouo.plugins.rankplugin.config.EXPStorage;
import engineer.skyouo.plugins.rankplugin.config.RankStorage;
import engineer.skyouo.plugins.rankplugin.listeners.BlockBreakByPlayerEvent;
import engineer.skyouo.plugins.rankplugin.listeners.EntityKilledEvent;
import engineer.skyouo.plugins.rankplugin.listeners.ExperienceUpdateEvent;
import engineer.skyouo.plugins.rankplugin.listeners.HarvestBlockEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class RankPlugin extends JavaPlugin {
    public static EXPStorage storage;
    public static RankStorage rankStorage;

    static {
        ConfigurationSerialization.registerClass(EXPData.class);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        storage = new EXPStorage();
        rankStorage = new RankStorage();

        Bukkit.getPluginManager().registerEvents(new BlockBreakByPlayerEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EntityKilledEvent(), this);
        Bukkit.getPluginManager().registerEvents(new HarvestBlockEvent(), this);

        Bukkit.getPluginManager().registerEvents(new ExperienceUpdateEvent(), this);

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            try {
                storage.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 20 * 60, 20 * 60);

        getCommand("rankexp").setExecutor(new RankExpCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            storage.shutdown();
            ExperienceUpdateEvent.executor.shutdown();
            ExperienceUpdateEvent.limit.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
