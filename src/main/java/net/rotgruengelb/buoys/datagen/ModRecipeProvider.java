package net.rotgruengelb.buoys.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
	public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	public static void offerBuoyRecipes(RecipeExporter exporter, List<Item> dyes, List<Item> buoys) {
		for (int i = 0; i < dyes.size(); i++) {
			ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, buoys.get(i), 2)
					.input('_', ItemTags.WOODEN_SLABS)
					.input('O', dyes.get(i))
					.input('.', Items.IRON_NUGGET)
					.pattern(" . ")
					.pattern("__O")
					.pattern("__O")
					.group("buoy")
					.criterion("in_water", requireEnteringFluid(Blocks.WATER))
					.offerTo(exporter);
		}
	}

	public static void offerChainBuoyRecipes(RecipeExporter exporter, List<Item> dyes, List<Item> chainBuoys) {
		for (int i = 0; i < dyes.size(); i++) {
			ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, chainBuoys.get(i), 4)
					.input('_', ItemTags.WOODEN_SLABS)
					.input('O', dyes.get(i))
					.input('-', ConventionalItemTags.CHAINS)
					.pattern("-_O")
					.pattern(" _O")
					.group("chain_buoy")
					.criterion("in_water", requireEnteringFluid(Blocks.WATER))
					.offerTo(exporter);
		}
	}

	@Override
	public void generate(RecipeExporter exporter) {

		offerDyeableRecipes(exporter, BuoyDataGenerator.DYES, BuoyDataGenerator.BUOYS.stream()
				.map(Block::asItem)
				.toList(), "buoy");

		offerDyeableRecipes(exporter, BuoyDataGenerator.DYES, BuoyDataGenerator.CHAIN_BUOYS.stream()
				.map(Block::asItem)
				.toList(), "chain_buoy");

		offerBuoyRecipes(exporter, BuoyDataGenerator.DYES, BuoyDataGenerator.BUOYS.stream()
				.map(Block::asItem)
				.toList());

		offerChainBuoyRecipes(exporter, BuoyDataGenerator.DYES, BuoyDataGenerator.CHAIN_BUOYS.stream()
				.map(Block::asItem)
				.toList());
	}
}
