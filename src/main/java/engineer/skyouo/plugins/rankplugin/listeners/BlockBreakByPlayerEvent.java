package engineer.skyouo.plugins.rankplugin.listeners;

import engineer.skyouo.plugins.rankplugin.data.FarmerEXP;
import engineer.skyouo.plugins.rankplugin.data.LumberjackEXP;
import engineer.skyouo.plugins.rankplugin.data.MiningEXP;
import engineer.skyouo.plugins.rankplugin.events.ExpChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakByPlayerEvent implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreakEvent(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) return;

        boolean flag1 = false; // need next check or not.
        for (LumberjackEXP item : LumberjackEXP.values()) {
            if (item.getElement().equals(e.getBlock().getType())) {
                flag1 = true;
                Bukkit.getPluginManager().callEvent(new ExpChangeEvent(p, item.getEXP(), ExpChangeEvent.Method.LUMBERJACK));
            } else continue;
        }

        if (flag1) return;

        for (MiningEXP item : MiningEXP.values()) {
            if (item.getElement().equals(e.getBlock().getType())) {
                flag1 = true;
                Bukkit.getPluginManager().callEvent(new ExpChangeEvent(p, item.getEXP(), ExpChangeEvent.Method.MINING));
            } else continue;
        }

        if (flag1) return;

        for (FarmerEXP item : FarmerEXP.values()) {
            if (item.getElement().equals(e.getBlock().getType())) {
                Bukkit.getPluginManager().callEvent(new ExpChangeEvent(p, item.getEXP(), ExpChangeEvent.Method.FARMER));
            } else continue;
        }
    }
}
