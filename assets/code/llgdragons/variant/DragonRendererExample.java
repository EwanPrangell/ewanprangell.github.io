public class DragonRenderer extends GeoEntityRenderer<DragonEntity> {
    public DragonRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new DragonModel());
    }

    @Override
    public Identifier getTextureLocation(DragonEntity animatable) {
        DragonVariant variant = animatable.getVariant();
        return variant.getTexture();
    }
}