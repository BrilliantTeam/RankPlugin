package engineer.skyouo.plugins.rankplugin.data;

import org.bukkit.Material;

public enum FarmerEXP implements EXP<Material> {
    WHEAT(Material.WHEAT, 0.2),
    WATERMELON(Material.MELON, 0.2),
    PUMPKIN(Material.PUMPKIN, 0.2),
    CARROT(Material.CARROTS, 0.2),
    BAMBOO(Material.BAMBOO, 0.2),
    POTATO(Material.POTATOES, 0.2),
    POISONED_POTATO(Material.POISONOUS_POTATO, 0.5),
    CACTUS(Material.CACTUS, 0.2),
    BERRIES(Material.SWEET_BERRY_BUSH, 0.2),
    BROWN_MUSHROOM(Material.BROWN_MUSHROOM, 0.2),
    RED_MUSHROOM(Material.RED_MUSHROOM, 0.2),
    GLOW_BERRIES(Material.GLOW_BERRIES, 0.2),
    KELP(Material.KELP, 0.2),
    SEA_PICKLE(Material.SEA_PICKLE, 0.2),
    COCOA(Material.COCOA, 0.2),
    BEETROOTS(Material.BEETROOTS, 0.2),
    NETHER_WART(Material.NETHER_WART, 0.2);

    double exp;
    Material material;

    FarmerEXP(Material material, double exp) {
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
