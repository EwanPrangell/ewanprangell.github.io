public abstract class DragonEntity extends TameableEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final TrackedData<Boolean> SLEEPING = DataTracker.registerData(DragonEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected DragonEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("sleeping", this.isDragonSleeping());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setIsDragonSleeping(nbt.getBoolean("sleeping"));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SLEEPING, false);
    }

    public boolean isNocturnal() {
        return false;
    }

    public boolean isDragonSleeping() {
        return this.dataTracker.get(SLEEPING);
    }

    public void setIsDragonSleeping(boolean sleeping) {
        this.dataTracker.set(SLEEPING, sleeping);
    }

    protected void sleepMechanics() {
        if (!getWorld().isClient()) { //&& this.isTame()) {      -   causes wild dragons that were already sleeping to never wake up
            if (isSitting()) {
                setIsDragonSleeping(isNocturnal() ? getWorld().isDay() : !getWorld().isDay());
            } else {
                setIsDragonSleeping(false);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.age % 1200 == 0) {
            sleepMechanics();
            if (this.isDragonSleeping()) this.getNavigation().stop();
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<NightfuryEntity> dragonEntityAnimationState) {
        if (!hasControllingPassenger() && this.isDragonSleeping()) {
            dragonEntityAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.dragon.sleep", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}