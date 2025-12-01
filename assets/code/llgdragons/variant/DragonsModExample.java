public class DragonsMod implements ModInitializer {
	public static final String MOD_ID = "dragonsmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final RegistryKey<Registry<DragonVariant>> DRAGON_VARIANT_KEY = RegistryKey.ofRegistry(Identifier.of(MOD_ID, "variant/dragon_variant"));

	@Override
	public void onInitialize() {
		DynamicRegistries.registerSynced(DRAGON_VARIANT_KEY, DragonVariant.CODEC);
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}