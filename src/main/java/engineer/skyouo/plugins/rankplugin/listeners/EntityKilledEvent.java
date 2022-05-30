package engineer.skyouo.plugins.rankplugin.listeners;

import engineer.skyouo.plugins.rankplugin.data.HunterEXP;
import engineer.skyouo.plugins.rankplugin.events.ExpChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityKilledEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDeathEvent(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity().getKiller();

        if (entity == null) return;

        for (HunterEXP hunterEXP : HunterEXP.values()) {
            if (hunterEXP.getElement().equals(e.getEntityType())) {
                Bukkit.getPluginManager().callEvent(new ExpChangeEvent((Player) entity, hunterEXP.getEXP(), ExpChangeEvent.Method.HUNTER));
              break;
            } else continue;
        }
    }
}
