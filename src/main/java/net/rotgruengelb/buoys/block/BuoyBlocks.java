package net.rotgruengelb.buoys.block;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.rotgruengelb.buoys.Buoys;
import net.rotgruengelb.buoys.item.BuoyBlockItem;
import net.rotgruengelb.buoys.util.BuoySounds;

public class BuoyBlocks {

	// Buoy Blocks
	public static final Block WHITE_BUOY = registerBuoyBlock("white_buoy", Blocks.WHITE_WOOL);
	public static final Block LIGHT_GRAY_BUOY = registerBuoyBlock("light_gray_buoy", Blocks.LIGHT_GRAY_WOOL);
	public static final Block GRAY_BUOY = registerBuoyBlock("gray_buoy", Blocks.GRAY_WOOL);
	public static final Block BLACK_BUOY = registerBuoyBlock("black_buoy", Blocks.BLACK_WOOL);
	public static final Block BROWN_BUOY = registerBuoyBlock("brown_buoy", Blocks.BROWN_WOOL);
	public static final Block RED_BUOY = registerBuoyBlock("red_buoy", Blocks.RED_WOOL);
	public static final Block ORANGE_BUOY = registerBuoyBlock("orange_buoy", Blocks.ORANGE_WOOL);
	public static final Block YELLOW_BUOY = registerBuoyBlock("yellow_buoy", Blocks.YELLOW_WOOL);
	public static final Block LIME_BUOY = registerBuoyBlock("lime_buoy", Blocks.LIME_WOOL);
	public static final Block GREEN_BUOY = registerBuoyBlock("green_buoy", Blocks.GREEN_WOOL);
	public static final Block CYAN_BUOY = registerBuoyBlock("cyan_buoy", Blocks.CYAN_WOOL);
	public static final Block LIGHT_BLUE_BUOY = registerBuoyBlock("light_blue_buoy", Blocks.LIGHT_BLUE_WOOL);
	public static final Block BLUE_BUOY = registerBuoyBlock("blue_buoy", Blocks.BLUE_WOOL);
	public static final Block PURPLE_BUOY = registerBuoyBlock("purple_buoy", Blocks.PURPLE_WOOL);
	public static final Block MAGENTA_BUOY = registerBuoyBlock("magenta_buoy", Blocks.MAGENTA_WOOL);
	public static final Block PINK_BUOY = registerBuoyBlock("pink_buoy", Blocks.PINK_WOOL);

	// Chain Buoy Blocks
	public static final Block WHITE_CHAIN_BUOY = registerChainBuoyBlock("white_chain_buoy", Blocks.WHITE_WOOL);
	public static final Block LIGHT_GRAY_CHAIN_BUOY = registerChainBuoyBlock("light_gray_chain_buoy", Blocks.LIGHT_GRAY_WOOL);
	public static final Block GRAY_CHAIN_BUOY = registerChainBuoyBlock("gray_chain_buoy", Blocks.GRAY_WOOL);
	public static final Block BLACK_CHAIN_BUOY = registerChainBuoyBlock("black_chain_buoy", Blocks.BLACK_WOOL);
	public static final Block BROWN_CHAIN_BUOY = registerChainBuoyBlock("brown_chain_buoy", Blocks.BROWN_WOOL);
	public static final Block RED_CHAIN_BUOY = registerChainBuoyBlock("red_chain_buoy", Blocks.RED_WOOL);
	public static final Block ORANGE_CHAIN_BUOY = registerChainBuoyBlock("orange_chain_buoy", Blocks.ORANGE_WOOL);
	public static final Block YELLOW_CHAIN_BUOY = registerChainBuoyBlock("yellow_chain_buoy", Blocks.YELLOW_WOOL);
	public static final Block LIME_CHAIN_BUOY = registerChainBuoyBlock("lime_chain_buoy", Blocks.LIME_WOOL);
	public static final Block GREEN_CHAIN_BUOY = registerChainBuoyBlock("green_chain_buoy", Blocks.GREEN_WOOL);
	public static final Block CYAN_CHAIN_BUOY = registerChainBuoyBlock("cyan_chain_buoy", Blocks.CYAN_WOOL);
	public static final Block LIGHT_BLUE_CHAIN_BUOY = registerChainBuoyBlock("light_blue_chain_buoy", Blocks.LIGHT_BLUE_WOOL);
	public static final Block BLUE_CHAIN_BUOY = registerChainBuoyBlock("blue_chain_buoy", Blocks.BLUE_WOOL);
	public static final Block PURPLE_CHAIN_BUOY = registerChainBuoyBlock("purple_chain_buoy", Blocks.PURPLE_WOOL);
	public static final Block MAGENTA_CHAIN_BUOY = registerChainBuoyBlock("magenta_chain_buoy", Blocks.MAGENTA_WOOL);
	public static final Block PINK_CHAIN_BUOY = registerChainBuoyBlock("pink_chain_buoy", Blocks.PINK_WOOL);

	public static final Event<?>[] event = new Event[]{ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS), ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)};

	private static Block registerBuoyBlock(String name, Block likeBlock) {
		final Block block = new BuoyBlock(getBuoyBlockSettings(likeBlock));
		return registerBlock(name, block, new BuoyBlockItem(block, new Item.Settings()));
	}

	private static Block registerChainBuoyBlock(String name, Block likeBlock) {
		final Block block = new ChainBuoyBlock(getBuoyBlockSettings(likeBlock));
		return registerBlock(name, block, new BuoyBlockItem(block, new Item.Settings()));
	}

	private static Block registerBlock(String name, Block block, BlockItem blockItem) {
		registerBlockItem(name, blockItem);
		return Registry.register(Registries.BLOCK, Buoys.id(name), block);
	}

	private static AbstractBlock.Settings getBuoyBlockSettings(Block likeBlock) {
		return AbstractBlock.Settings.copy(likeBlock)
				.noBlockBreakParticles()
				.nonOpaque()
				.sounds(BuoySounds.ModSoundGroups.BUOY_BLOCK_SOUNDS)
				.strength(.2f);
	}

	private static void registerBlockItem(String name, BlockItem blockItem) {
		Registry.register(Registries.ITEM, Buoys.id(name), blockItem);
	}

	public static void registerModBlocks() {
		Buoys.LOGGER.debug("Registering BuoyBlocks for " + Buoys.MOD_ID);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(BuoyBlocks::addItemsToItemGroup);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(BuoyBlocks::addItemsToItemGroup);
	}

	private static void addItemsToItemGroup(FabricItemGroupEntries entries) {
		itemGroupAdd(entries, WHITE_BUOY, LIGHT_GRAY_BUOY, GRAY_BUOY, BLACK_BUOY, BROWN_BUOY, RED_BUOY, ORANGE_BUOY, YELLOW_BUOY, LIME_BUOY, GREEN_BUOY, CYAN_BUOY, LIGHT_BLUE_BUOY, BLUE_BUOY, PURPLE_BUOY, MAGENTA_BUOY, PINK_BUOY);
		itemGroupAdd(entries, WHITE_CHAIN_BUOY, LIGHT_GRAY_CHAIN_BUOY, GRAY_CHAIN_BUOY, BLACK_CHAIN_BUOY, BROWN_CHAIN_BUOY, RED_CHAIN_BUOY, ORANGE_CHAIN_BUOY, YELLOW_CHAIN_BUOY, LIME_CHAIN_BUOY, GREEN_CHAIN_BUOY, CYAN_CHAIN_BUOY, LIGHT_BLUE_CHAIN_BUOY, BLUE_CHAIN_BUOY, PURPLE_CHAIN_BUOY, MAGENTA_CHAIN_BUOY, PINK_CHAIN_BUOY);
	}

	private static void itemGroupAdd(FabricItemGroupEntries entries, Block... blocks) {
		for (Block block : blocks) {
			entries.add(block);
		}
	}
}
