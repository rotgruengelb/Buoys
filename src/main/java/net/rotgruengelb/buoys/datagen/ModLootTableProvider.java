package net.rotgruengelb.buoys.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
	protected ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {

		for (Block block : BuoyDataGenerator.combineLists(BuoyDataGenerator.BUOYS, BuoyDataGenerator.CHAIN_BUOYS)) {
			addDrop(block);
		}
	}
}
