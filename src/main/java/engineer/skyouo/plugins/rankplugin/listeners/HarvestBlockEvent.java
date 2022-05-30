package engineer.skyouo.plugins.rankplugin.listeners;

import engineer.skyouo.plugins.rankplugin.data.FarmerEXP;
import engineer.skyouo.plugins.rankplugin.events.ExpChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerHarvestBlockEvent;

public class HarvestBlockEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerHarvestBlockEvent(PlayerHarvestBlockEvent e) {
        for (FarmerEXP item : FarmerEXP.values()) {
            if (item.getElement().equals(e.getHarvestedBlock().getType())) {
                Bukkit.getPluginManager().callEvent(new ExpChangeEvent(e.getPlayer(), item.getEXP(), ExpChangeEvent.Method.FARMER));
            } else continue;
        }
    }
}
