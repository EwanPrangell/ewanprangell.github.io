public class DragonVariant implements Comparable<DragonVariant> {
    public static final Codec<Text> TEXT_CODEC = Codec.STRING.xmap(
            json -> Text.Serializer.fromJson(json),
            text -> Text.Serializer.toJson(text)
    );
    public static final Codec<EntityType<?>> ENTITY_CODEC = Registries.ENTITY_TYPE.getCodec();

    public static final Codec<DragonVariant> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    ENTITY_CODEC.fieldOf("entity").forGetter(dragon -> dragon.entity),
                    TEXT_CODEC.fieldOf("name").forGetter(dragon -> dragon.name),
                    Identifier.CODEC.fieldOf("texture").forGetter(dragon -> dragon.texture)
            ).apply(instance, DragonVariant::new)
    );

    public static final Codec<RegistryEntry<DragonVariant>> ENTRY_CODEC = RegistryElementCodec.of(DragonMod.DRAGON_VARIANT_KEY, CODEC);

    public final EntityType<?> entity;
    public final Text name;
    public final Identifier texture;

    public DragonVariant(EntityType<?> entity, Text name, Identifier texture) {
        this.entity = entity;
        this.name = name;
        this.texture = texture;
    }

    public static DragonVariant DRAGON = new DragonVariantBuilder(
            DragonModEntities.Dragon,
            Text.translatable("dragonmod.variant.dragon.dragon"),
            Identifier.of(DragonMod.MOD_ID, "textures/entity/dragon/dragon.png"))
            .buildDraon();

    public static DragonVariant fromId(DynamicRegistryManager registryManager, int id) {
        Optional<RegistryEntry.Reference<DragonVariant>> holder = registryManager.get(DragonMod.DRAGON_VARIANT_KEY).getEntry(id);
        return holder.map(RegistryEntry.Reference::value).orElseGet(() -> DRAGON);
    }

    public static int toId(DynamicRegistryManager registryManager, DragonVariant variant) {
        return registryManager.get(DragonMod.DRAGON_VARIANT_KEY).getRawId(variant);
    }

    public static DragonVariant fromNbt(DynamicRegistryManager registryManager, NbtCompound nbt) {
        AtomicReference<DragonVariant> returnVariant = new AtomicReference<>();
        Optional.ofNullable(Identifier.tryParse(nbt.getString("variant")))
                .map(id -> RegistryKey.of(DragonMod.DRAGON_VARIANT_KEY, id))
                .flatMap(variantKey -> registryManager.get(DragonMod.DRAGON_VARIANT_KEY).getEntry(variantKey))
                .ifPresent(dragonVariantReference -> returnVariant.set(dragonVariantReference.value()));
        return returnVariant.get();
    }

    public EntityType<?> getEntity() {
        return entity;
    }

    public Text getName() {
        return name;
    }

    public Identifier getTexture() {
        return texture;
    }

    @Override
    public int compareTo(DragonVariant other) {
        return this.texture.toString().compareTo(other.texture.toString());
    }
}