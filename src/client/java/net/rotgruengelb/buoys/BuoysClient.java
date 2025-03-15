package net.rotgruengelb.buoys;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.rotgruengelb.buoys.datagen.BuoyDataGenerator;

public class BuoysClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BuoyDataGenerator.createDatagenLists();
		for (Block block : BuoyDataGenerator.CHAIN_BUOYS) {
			BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
		}

	}
}