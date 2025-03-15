package net.rotgruengelb.buoys.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.rotgruengelb.buoys.Buoys;

public class BuoyTags {
	public static class Blocks {
		public static final TagKey<Block> BUOYS = createTagKey(RegistryKeys.BLOCK, "buoys");
		public static final TagKey<Block> CHAIN_BUOYS = createTagKey(RegistryKeys.BLOCK, "chain_buoys");
		public static final TagKey<Block> LIKE_BUOY = createTagKey(RegistryKeys.BLOCK, "like_buoy");
	}
	public static class Items {
		public static final TagKey<Item> BUOYS = createTagKey(RegistryKeys.ITEM , "buoys");
		public static final TagKey<Item> CHAIN_BUOYS = createTagKey(RegistryKeys.ITEM, "chain_buoys");
		public static final TagKey<Item> LIKE_BUOY = createTagKey(RegistryKeys.ITEM, "like_buoy");
	}

	private static <Y> TagKey<Y> createTagKey(RegistryKey<? extends Registry<Y>> registryKey, String path) {
		Identifier id = Buoys.id( path);
		return TagKey.of(registryKey, id);
	}

	public static void registerModTags() {
		Buoys.LOGGER.debug("Registering BuoyTags for " + Buoys.MOD_ID);
	}
}
