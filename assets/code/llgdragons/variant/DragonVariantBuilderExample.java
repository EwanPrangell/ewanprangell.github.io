public class DragonVariantBuilder {
    private final EntityType<?> entity;
    private final Text name;
    private final Identifier texture;

    public DragonVariantBuilder(EntityType<?> entity, String name, Identifier texture) {
        this(entity, Text.of(name), texture);
    }

    public DragonVariantBuilder(EntityType<?> entity, Text name, Identifier texture) {
        this.entity = entity;
        this.name = name;
        this.texture = texture;
    }

    public DragonVariant buildDragon() {
        return new DragonVariant(entity, name, texture);
    }

    private static<T> Optional<T> empty() { //easier to type faster
        return Optional.empty();
    }

    private static<T> Optional<T> of(T value) { //easier to type faster
        return Optional.of(value);
    }
}