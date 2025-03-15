package net.rotgruengelb.buoys.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.rotgruengelb.buoys.util.BuoyTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		var buysTagBuilder = getOrCreateTagBuilder(BuoyTags.Items.BUOYS);
		for (Block block : BuoyDataGenerator.BUOYS) {
			buysTagBuilder.add(block.asItem());
		}

		var chainBuysTagBuilder = getOrCreateTagBuilder(BuoyTags.Items.CHAIN_BUOYS);
		for (Block block : BuoyDataGenerator.CHAIN_BUOYS) {
			chainBuysTagBuilder.add(block.asItem());
		}
	}
}
