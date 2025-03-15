package net.rotgruengelb.buoys.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.rotgruengelb.buoys.util.BuoyTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		var buysTagBuilder = getOrCreateTagBuilder(BuoyTags.Blocks.BUOYS);
		for (Block block : BuoyDataGenerator.BUOYS) {
			buysTagBuilder.add(block);
		}

		var chainBuysTagBuilder = getOrCreateTagBuilder(BuoyTags.Blocks.CHAIN_BUOYS);
		for (Block block : BuoyDataGenerator.CHAIN_BUOYS) {
			chainBuysTagBuilder.add(block);
		}

		getOrCreateTagBuilder(BuoyTags.Blocks.LIKE_BUOY) //
				.addTag(BuoyTags.Blocks.BUOYS)
				.addTag(BuoyTags.Blocks.CHAIN_BUOYS);
	}
}
