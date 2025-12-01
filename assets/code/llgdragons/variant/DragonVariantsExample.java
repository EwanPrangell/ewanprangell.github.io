public class DragonVariants {
    public static final RegistryKey<DragonVariant> DRAGON = of("dragon");
    public static final RegistryKey<DragonVariant> ALBINO_DRAGON = of("albino_dragon");
    public static final RegistryKey<DragonVariant> WHITE_DRAGON = of("white_dragon");

    public static void bootstrap(Registerable<DragonVariant> registerable) {
        registerable.register(DRAGON, DragonVariant.DRAGON);

        registerable.register(ALBINO_DRAGON, new DragonVariantBuilder(
                DragonModEntities.DRAGON,
                Text.translatable("dragonmod.variant.dragon.albino_dragon"),
                Identifier.of(DragonMod.MOD_ID, "textures/entity/dragon/albino_dragon.png"))
                .buildDragon()
        );

        registerable.register(WHITE_DRAGON, new DragonVariantBuilder(
                DragonModEntities.DRAGON,
                Text.translatable("dragonmod.variant.dragon.white_dragon"),
                Identifier.of(DragonMod.MOD_ID, "textures/entity/dragon/white_dragon.png"))
                .buildDragon()
        );
    }

    public static RegistryKey<DragonVariant> of(String path) {
        return RegistryKey.of(DragonMod.DRAGON_VARIANT_KEY, Identifier.of(DragonMod.MOD_ID, path));
    }
}