package engineer.skyouo.plugins.rankplugin.data;

import org.bukkit.Material;

public enum MiningEXP implements EXP<Material> {
    STONE(Material.STONE, 0.02),
    DEEPSLATE(Material.DEEPSLATE, 0.03),

    NETHERRACK(Material.NETHERRACK, 0.03),
    END_STONE(Material.END_STONE, 0.03),

    COAL_ORE(Material.COAL_ORE, 0.1),
    DEEPSLATE_COAL_ORE(Material.DEEPSLATE_COAL_ORE, 0.2),

    IRON_ORE(Material.IRON_ORE, 0.2),
    DEEPSLATE_IRON_ORE(Material.DEEPSLATE_IRON_ORE, 0.3),

    COPPER_ORE(Material.COPPER_ORE, 0.2),
    DEEPSLATE_COPPER_ORE(Material.DEEPSLATE_COPPER_ORE, 0.3),

    LAPIS_ORE(Material.LAPIS_ORE, 0.5),
    DEEPSLATE_LAPIS_ORE(Material.DEEPSLATE_LAPIS_ORE, 0.6),

    REDSTONE_ORE(Material.REDSTONE_ORE, 0.5),
    DEEPSLATE_REDSTONE_ORE(Material.DEEPSLATE_REDSTONE_ORE, 0.6),

    GOLD_ORE(Material.GOLD_ORE, 0.5),
    DEEPSLATE_GOLD_ORE(Material.DEEPSLATE_GOLD_ORE, 0.6),

    DIAMOND_ORE(Material.DIAMOND_ORE, 1),
    DEEPSLATE_DIAMOND_ORE(Material.DEEPSLATE_DIAMOND_ORE, 1.5),

    EMERALD(Material.EMERALD_ORE, 5),
    DEEPSLATE_EMERALD_ORE(Material.DEEPSLATE_EMERALD_ORE, 6),

    AMETHYST(Material.AMETHYST_CLUSTER, 0.5),
    NETHER_QUARTZ(Material.NETHER_QUARTZ_ORE, 0.5),
    NETHER_GOLD_ORE(Material.NETHER_GOLD_ORE, 0.5);

    // ANCIENT_DEBRIS(Material.ANCIENT_DEBRIS, 20);

    double exp;
    Material material;

    MiningEXP(Material material, double exp) {
        this.exp = exp;
        this.material = material;
    }

    @Override
    public double getEXP() {
        return this.exp;
    }

    @Override
    public Material getElement() {
        return this.material;
    }
}
