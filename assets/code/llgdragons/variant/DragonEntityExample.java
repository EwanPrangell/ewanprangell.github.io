public abstract class DragonEntity extends TameableEntity implements VariantHolder<DragonVariant>, GeoEntity {
    private static final TrackedData<Integer> VARIANT_ID = DataTracker.registerData(DragonEntity.class, TrackedDataHandlerRegistry.INTEGER);

    protected DragonEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        this.getWorld().getRegistryManager().get(DragonsMod.DRAGON_VARIANT_KEY)
                .getEntry(DragonVariant.toId(this.getWorld().getRegistryManager(), this.getVariant()))
                .flatMap(RegistryEntry.Reference::getKey)
                .ifPresent(key -> nbt.putString("variant", key.getValue().toString()));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        DragonVariant variant = DragonVariant.fromNbt(this.getWorld().getRegistryManager(), nbt);
        if(variant != null) this.setVariant(variant);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(VARIANT_ID, DragonVariant.toId(this.getWorld().getRegistryManager(), DragonVariant.DRAGON));
    }

    @Override
    public DragonVariant getVariant() {
        return DragonVariant.fromId(this.getWorld().getRegistryManager(), this.dataTracker.get(VARIANT_ID));
    }

    @Override
    public void setVariant(DragonVariant variant) {
        int id = DragonVariant.toId(this.getWorld().getRegistryManager(), variant);
        this.dataTracker.set(VARIANT_ID, id);
    }
}