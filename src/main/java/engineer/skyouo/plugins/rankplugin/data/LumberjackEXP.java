package engineer.skyouo.plugins.rankplugin.data;

import org.bukkit.Material;

public enum LumberjackEXP implements EXP<Material> {
    ACACIA_LOG(Material.ACACIA_LOG, 0.1),
    BIRCH_LOG(Material.BIRCH_LOG, 0.1),
    OAK_LOG(Material.OAK_LOG, 0.1),
    DARK_OAK_LOG(Material.DARK_OAK_LOG, 0.1),
    SPRUCE_LOG(Material.SPRUCE_LOG, 0.1),
    JUNGLE_LOG(Material.JUNGLE_LOG, 0.1),

    CRIMSON_FUNGUS(Material.CRIMSON_FUNGUS, 0.5),
    WARPED_FUNGUS(Material.CRIMSON_FUNGUS, 0.5),

    CHORUS_PLANT(Material.CHORUS_PLANT, 0.5);

    double exp;
    Material material;

    LumberjackEXP(Material material, double exp) {
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
