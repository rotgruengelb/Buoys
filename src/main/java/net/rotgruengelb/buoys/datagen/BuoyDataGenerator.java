package net.rotgruengelb.buoys.datagen;

import com.google.gson.TypeAdapterFactory;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.tag.FabricTagKey;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.TagKey;
import net.rotgruengelb.buoys.block.BuoyBlocks;
import net.rotgruengelb.buoys.util.BuoyTags;

import java.util.ArrayList;
import java.util.List;

public class BuoyDataGenerator implements DataGeneratorEntrypoint {
	public static List<Block> BUOYS = null;
	public static List<Block> CHAIN_BUOYS = null;
	public static List<Item> DYES = null;
	public static List<TagKey<?>> TAGS = null;

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		createDatagenLists();
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModLanguageProvider::new);
	}

	public static void createDatagenLists() {
		TAGS = List.of(
				BuoyTags.Items.BUOYS,
				BuoyTags.Items.CHAIN_BUOYS,
				BuoyTags.Items.LIKE_BUOY,
				BuoyTags.Blocks.BUOYS,
				BuoyTags.Blocks.CHAIN_BUOYS,
				BuoyTags.Blocks.LIKE_BUOY
		);
		BUOYS = List.of(
				BuoyBlocks.BLACK_BUOY,
				BuoyBlocks.BLUE_BUOY,
				BuoyBlocks.BROWN_BUOY,
				BuoyBlocks.CYAN_BUOY,
				BuoyBlocks.GRAY_BUOY,
				BuoyBlocks.GREEN_BUOY,
				BuoyBlocks.LIGHT_BLUE_BUOY,
				BuoyBlocks.LIGHT_GRAY_BUOY,
				BuoyBlocks.LIME_BUOY,
				BuoyBlocks.MAGENTA_BUOY,
				BuoyBlocks.ORANGE_BUOY,
				BuoyBlocks.PINK_BUOY,
				BuoyBlocks.PURPLE_BUOY,
				BuoyBlocks.RED_BUOY,
				BuoyBlocks.YELLOW_BUOY,
				BuoyBlocks.WHITE_BUOY
		);
		CHAIN_BUOYS = List.of(
				BuoyBlocks.BLACK_CHAIN_BUOY,
				BuoyBlocks.BLUE_CHAIN_BUOY,
				BuoyBlocks.BROWN_CHAIN_BUOY,
				BuoyBlocks.CYAN_CHAIN_BUOY,
				BuoyBlocks.GRAY_CHAIN_BUOY,
				BuoyBlocks.GREEN_CHAIN_BUOY,
				BuoyBlocks.LIGHT_BLUE_CHAIN_BUOY,
				BuoyBlocks.LIGHT_GRAY_CHAIN_BUOY,
				BuoyBlocks.LIME_CHAIN_BUOY,
				BuoyBlocks.MAGENTA_CHAIN_BUOY,
				BuoyBlocks.ORANGE_CHAIN_BUOY,
				BuoyBlocks.PINK_CHAIN_BUOY,
				BuoyBlocks.PURPLE_CHAIN_BUOY,
				BuoyBlocks.RED_CHAIN_BUOY,
				BuoyBlocks.YELLOW_CHAIN_BUOY,
				BuoyBlocks.WHITE_CHAIN_BUOY
		);
		DYES = List.of(
				Items.BLACK_DYE,
				Items.BLUE_DYE,
				Items.BROWN_DYE,
				Items.CYAN_DYE,
				Items.GRAY_DYE,
				Items.GREEN_DYE,
				Items.LIGHT_BLUE_DYE,
				Items.LIGHT_GRAY_DYE,
				Items.LIME_DYE,
				Items.MAGENTA_DYE,
				Items.ORANGE_DYE,
				Items.PINK_DYE,
				Items.PURPLE_DYE,
				Items.RED_DYE,
				Items.YELLOW_DYE,
				Items.WHITE_DYE
		);
	}

	@SafeVarargs
	protected static <T> List<T> combineLists(List<T>... lists) {
		List<T> combined = new ArrayList<>();
		for (List<T> list : lists) {
			combined.addAll(list);
		}
		return combined;
	}
}
