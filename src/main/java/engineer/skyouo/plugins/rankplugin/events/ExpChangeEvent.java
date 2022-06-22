package engineer.skyouo.plugins.rankplugin.events;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ExpChangeEvent extends Event {
    private Player player;
    private double experience;
    private Method method;

    private static final HandlerList handlers = new HandlerList();

    public ExpChangeEvent(Player who, double exp, Method way) {
        super();

        player = who;
        experience = exp;
        method = way;
    }

    public Player getPlayer() {
        return player;
    }

    public double getExperience() {
        return experience;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public Method getMethod() {
        return method;
    }

    public static HandlerList getHandlerList() { return handlers; }

    public enum Method {
        FARMER, HUNTER, LUMBERJACK, MINING, OTHER
    }
}
