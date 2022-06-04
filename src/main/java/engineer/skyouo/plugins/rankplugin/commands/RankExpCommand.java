package engineer.skyouo.plugins.rankplugin.commands;

import engineer.skyouo.plugins.rankplugin.RankPlugin;
import engineer.skyouo.plugins.rankplugin.config.EXPData;
import engineer.skyouo.plugins.rankplugin.data.Rank;
import engineer.skyouo.plugins.rankplugin.events.ExpChangeEvent;
import engineer.skyouo.plugins.rankplugin.listeners.ExperienceUpdateEvent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RankExpCommand implements TabExecutor {
    static DecimalFormat formatter = new DecimalFormat("#0.00");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        if (args.length < 1) return true;

        switch (args[0]) {
            case "about": {
                BaseComponent[] components = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&6[訊] &f飯娘 : &6插件開發者是&a: "))
                        .append("- Track Lost - ").color(net.md_5.bungee.api.ChatColor.DARK_GRAY)
                        .event(
                                new HoverEvent(
                                        HoverEvent.Action.SHOW_TEXT,
                                        new ComponentBuilder()
                                                .append("願").color(ChatColor.WHITE)
                                                .append("星").color(ChatColor.GOLD)
                                                .append("願長存.").color(ChatColor.WHITE)
                                                .create()
                                )
                        )
                        .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/NCT-skyouo"))
                        .append("(").color(ChatColor.DARK_AQUA)
                        .append("Potential ").color(ChatColor.WHITE)
                        .append("-0.5").color(ChatColor.RED)
                        .append(")").color(ChatColor.DARK_AQUA)
                        .create();

                sender.spigot().sendMessage(components);
                break;
            }
            case "exp": {
                Player player = (Player) sender;
                String toCall = "您";
                if (sender.isOp() && args.length == 2) {
                    player = Bukkit.matchPlayer(args[1]).get(0);
                    toCall = player.getDisplayName() + " ";
                }

                BaseComponent[] components = new ComponentBuilder()
                        .append("[訊] ").color(net.md_5.bungee.api.ChatColor.GOLD)
                        .append("飯娘 : ").color(net.md_5.bungee.api.ChatColor.WHITE)
                        .append(toCall + "目前擁有的稱號經驗為: ").color(net.md_5.bungee.api.ChatColor.GOLD)

                        .append(formatter.format(RankPlugin.storage.get(player).getExp())).color(ChatColor.GREEN)
                        .event(
                                new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                        new ComponentBuilder()
                                                .append("經驗值 ").color(ChatColor.GREEN)
                                                .append("可以透過 ").color(ChatColor.WHITE)
                                                .append("採集礦物").color(ChatColor.GOLD)
                                                .append(", ").color(ChatColor.WHITE)
                                                .append("挖掘樹木").color(ChatColor.DARK_GREEN)
                                                .append(", ").color(ChatColor.WHITE)
                                                .append("擊殺怪物 ").color(ChatColor.RED)
                                                .append("等方式獲取.").color(ChatColor.WHITE)
                                                .append("\n")
                                                .append("當 ").color(ChatColor.WHITE)
                                                .append("經驗值 ").color(ChatColor.GREEN)
                                                .append("到達一定程度時").color(ChatColor.WHITE)
                                                .append(", 即可獲取 ").color(ChatColor.WHITE)
                                                .append("新的稱號").color(ChatColor.AQUA)
                                                .append(".").color(ChatColor.WHITE)
                                                .create()
                                )
                        )
                        .create();

                sender.spigot().sendMessage(components);
                break;
            }
            case "rank": {
                double exp = RankPlugin.storage.get((Player) sender).getExp();
                Rank nextLvl = Rank.getNextLevel(exp);
                BaseComponent[] components = new ComponentBuilder()
                        .append("[訊] ").color(net.md_5.bungee.api.ChatColor.GOLD)
                        .append("飯娘 : ").color(net.md_5.bungee.api.ChatColor.WHITE)
                        .append("你目前擁有的稱號有 ").color(net.md_5.bungee.api.ChatColor.GOLD)
                        .event((ClickEvent) null)
                        .event((HoverEvent) null)
                        .append(ChatColor.translateAlternateColorCodes('&', Rank.getAllRankUserHas(RankPlugin.storage.get((Player) sender).getExp()).stream().map(r -> ChatColor.translateAlternateColorCodes('&', r.getPrefix())).collect(Collectors.joining(ChatColor.RESET + "/"))))
                        .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/plt open"))
                        .event(
                                new HoverEvent(
                                        HoverEvent.Action.SHOW_TEXT,
                                        new ComponentBuilder()
                                                .append("距離 ").color(ChatColor.GRAY)
                                                .append(ChatColor.translateAlternateColorCodes('&', nextLvl.getPrefix()))
                                                .append(" 還差: ").color(ChatColor.GRAY)
                                                .append(formatter.format(nextLvl.getExpRequired() - exp)).color(ChatColor.GOLD)
                                                .create()
                                )
                        )

                        .create();

                sender.spigot().sendMessage(components);
                break;
            }
            case "addexp": {
                if (!sender.isOp()) return true;
                try {
                    Player player = Bukkit.matchPlayer(args[1]).get(0);
                    double toAdd = Double.valueOf(args[2]);
                    Bukkit.getPluginManager().callEvent(new ExpChangeEvent(player, toAdd, ExpChangeEvent.Method.OTHER));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[訊] &f飯娘 : &6已給予&a " + player.getName() + " &6共&a " + toAdd + " &6點稱號經驗"));
                } catch (Exception e) {
                    return true;
                }
                break;
            }
            case "debug": {
                if (!sender.isOp()) return true;
                sender.sendMessage(ExperienceUpdateEvent.limit.toString());
                break;
            }
            case "exptop":
                if (!sender.isOp()) return true;
                List<EXPData> expDatas = RankPlugin.storage.getSort();
                for (int i : Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)) sender.sendMessage((i+1) + ". " + expDatas.get(i).getOfflinePlayer().getName() + " " + formatter.format(expDatas.get(i).getExp()));
                break;
            default:
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[訊] &f飯娘 : 無效的用法."));
                break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (args.length == 1) {
                return Arrays.asList("about", "exp", "rank", "addexp");
            } else if (args.length == 2) {
                if (!args[0].equals("exp") && !args[0].equals("addexp")) return Collections.emptyList();
                return Bukkit.matchPlayer(args[1]).stream().map(Player::getName).collect(Collectors.toList());
            } else {
                return Collections.emptyList();
            }
        } else {
            return Arrays.asList("about", "exp", "rank");
        }
    }
}
