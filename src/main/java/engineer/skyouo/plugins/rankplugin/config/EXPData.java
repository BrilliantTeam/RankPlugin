package engineer.skyouo.plugins.rankplugin.config;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SerializableAs("EXPData")
public class EXPData implements ConfigurationSerializable {
    private String player;
    private double exp;

    public EXPData(Player player, double exp) {
        this.player = player.getUniqueId().toString();
        this.exp = exp;
    }

    public EXPData(Map<String, Object> map) {
        this.player = (String) map.get("player");
        this.exp = (double) map.get("exp");
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("player", player);
        map.put("exp", exp);
        return map;
    }

    public double getExp() {
        return exp;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(UUID.fromString(player));
    }

    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(UUID.fromString(player));
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public void setPlayer(Player player) {
        this.player = player.getUniqueId().toString();
    }
}
