package engineer.skyouo.plugins.rankplugin.listeners;

import com.handy.playertitle.api.PlayerTitleApi;
import engineer.skyouo.plugins.rankplugin.RankPlugin;
import engineer.skyouo.plugins.rankplugin.config.EXPData;
import engineer.skyouo.plugins.rankplugin.data.Rank;
import engineer.skyouo.plugins.rankplugin.events.ExpChangeEvent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExperienceUpdateEvent implements Listener {
    public static final ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
    public static final ConcurrentHashMap<UUID, Integer> limit = new ConcurrentHashMap<>();
    public ExperienceUpdateEvent() { }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onExperienceUpdate(ExpChangeEvent e) {
        if (e.getPlayer() == null) return;
        if (!exceedLimit(e.getPlayer(), e.getMethod())) return;

        EXPData expData = RankPlugin.storage.get(e.getPlayer());
        Rank previousRank = Rank.getRankByEXP(expData.getExp());

        expData.setExp(expData.getExp() + e.getExperience());

        Rank nowRank = Rank.getRankByEXP(expData.getExp());

        RankPlugin.storage.set(e.getPlayer(), expData);

        if (previousRank != nowRank) {
            BaseComponent[] components = new ComponentBuilder()
                    .append("[訊] ").color(net.md_5.bungee.api.ChatColor.GOLD)
                    .append("飯娘 : ").color(net.md_5.bungee.api.ChatColor.WHITE)
                    .append("你已晉升至").color(net.md_5.bungee.api.ChatColor.GOLD)
                    .event((ClickEvent) null)
                    .event((HoverEvent) null)
                    .append(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', nowRank.getPrefix()))
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/plt open"))
                    .event(
                            new HoverEvent(
                                    HoverEvent.Action.SHOW_TEXT,
                                    new ComponentBuilder()
                                            .append("獎勵金額: ").color(net.md_5.bungee.api.ChatColor.GRAY)
                                            .append(String.valueOf(nowRank.getMoneyReward())).color(net.md_5.bungee.api.ChatColor.GOLD)
                                            .create()
                            )
                    )
                    .append("！已給予稱號及遊戲幣獎勵 :D").color(net.md_5.bungee.api.ChatColor.GOLD)
                    .event((ClickEvent) null)
                    .event((HoverEvent) null)
                    .append(" (使用 /plt open 開啟稱號選單)").color(net.md_5.bungee.api.ChatColor.GRAY)
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/plt open"))
                    .create();

            e.getPlayer().spigot().sendMessage(
                     components
            );

            PlayerTitleApi.getInstance().set(e.getPlayer().getName(), RankPlugin.rankStorage.get(nowRank), 36500);

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + e.getPlayer().getName() + " " + nowRank.getMoneyReward());
        }
    }

    private boolean exceedLimit(Player player, ExpChangeEvent.Method method) {
        if (method.equals(ExpChangeEvent.Method.OTHER)) return true;

        Integer x = limit.get(player.getUniqueId());

        if (x == null) {
            limit.put(player.getUniqueId(), 1);
        } else {
            if (x == 500) return false;
            limit.put(player.getUniqueId(), x + 1);

            if (x + 1 == 500) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &6[訊] &f飯娘: &6你已達到稱號經驗收益上限, 請等待一小時後再賺取稱號經驗."));
                scheduleClear(player);
            }
        }
        return true;
    }

    private void scheduleClear(Player player) {
        GregorianCalendar cal = new GregorianCalendar();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int hourScheduled = hour+1;

        cal.set(year, month, date, hourScheduled, minute);
        long initialDelay = cal.getTimeInMillis() - System.currentTimeMillis();
        if (initialDelay < 0) {
            initialDelay = 0L;
        }
        // schedule each job for once per hour
        int period = 60*60*1000;

        executor.scheduleAtFixedRate(() -> limit.put(player.getUniqueId(), 0), initialDelay, period, TimeUnit.MILLISECONDS);
    }
}
