package engineer.skyouo.plugins.rankplugin.data;

import org.bukkit.entity.EntityType;

public enum HunterEXP implements EXP<EntityType> {
    ZOMBIE(EntityType.ZOMBIE, 0.05),
    DROWNED(EntityType.DROWNED, 0.07),
    HUSK(EntityType.HUSK, 0.07),
    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, 0.1),
    SKELETON(EntityType.SKELETON, 0.05),
    STRAY(EntityType.STRAY, 0.07),
    WITHER_SKELETON(EntityType.WITHER_SKELETON, 0.1),
    CREEPER(EntityType.CREEPER, 0.05),
    SPIDER(EntityType.SPIDER, 0.05),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, 0.07),
    SLIME(EntityType.SLIME, 0.05),
    WITCH(EntityType.WITCH, 0.1),
    ENDERMAN(EntityType.ENDERMAN, 0.01),
    EVOKER(EntityType.EVOKER, 0.1),
    VINDICATOR(EntityType.VINDICATOR, 0.1),
    VEX(EntityType.VEX, 0.1),
    PILLAGER(EntityType.PILLAGER, 0.1),
    RAVAGER(EntityType.RAVAGER, 0.05),
    SILVERFISH(EntityType.SILVERFISH, 0.05),
    GUARDIAN(EntityType.GUARDIAN, 0.05),
    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, 0.1),
    BLAZE(EntityType.BLAZE, 0.01),
    GHAST(EntityType.GHAST, 0.1),
    MAGMA_CUBE(EntityType.MAGMA_CUBE, 0.05),
    PIGLIN(EntityType.PIGLIN, 0.05),
    ZOMBIFIED_PIGLIN(EntityType.ZOMBIFIED_PIGLIN, 0.07),
    PIGLIN_BRUTE(EntityType.PIGLIN_BRUTE, 0.1),
    HOGLIN(EntityType.HOGLIN, 1),
    ZOGLIN(EntityType.ZOGLIN, 1.05),
    SHULKER(EntityType.SHULKER, 0.05),
    ENDERMITE(EntityType.ENDERMITE, 0.05),
    ENDER_DRAGON(EntityType.ENDER_DRAGON, 100),
    WITHER(EntityType.WITHER, 50);

    double exp;
    EntityType entity;

    HunterEXP(EntityType entity, double exp) {
        this.exp = exp;
        this.entity = entity;
    }

    @Override
    public double getEXP() {
        return this.exp;
    }

    @Override
    public EntityType getElement() {
        return this.entity;
    }
}
