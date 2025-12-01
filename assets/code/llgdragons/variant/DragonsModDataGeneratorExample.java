public class DragonsModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(DragonsModBuilderProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(DragonsMod.DRAGON_VARIANT_KEY, DragonVariants::bootstrap);
	}

	@Override
	public void addJsonKeySortOrders(JsonKeySortOrderCallback callback) {
		// For variants
		callback.add("entity", 1);
		callback.add("name", 2);
		callback.add("texture", 3);
	}

	public static class DragonsModBuilderProvider extends FabricDynamicRegistryProvider {
		public DragonsModBuilderProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
			entries.addAll(registries.getWrapperOrThrow(DragonsMod.DRAGON_VARIANT_KEY));
		}

		@Override
		public String getName() {
			return "DragonsMod Variants";
		}
	}
}