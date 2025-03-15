package net.rotgruengelb.buoys.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.rotgruengelb.buoys.Buoys;
import net.rotgruengelb.buoys.util.BuoyProperties;
import net.rotgruengelb.buoys.util.BuoyProperties.ChainBuoyState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.minecraft.data.client.TextureMap.getId;

public class ModModelProvider extends FabricModelProvider {
	public static final TexturedModel.Factory BUOY = TexturedModel.makeFactory(TextureMap::texture, block("buoy", TextureKey.TEXTURE));
	public static final TexturedModel.Factory CHAIN_BUOY = TexturedModel.makeFactory(ModModelProvider::buoyTexture, block("chain_buoy", TextureKey.TEXTURE));
	public static final TexturedModel.Factory CHAIN_BUOY_STRAIGHT_CHAIN = TexturedModel.makeFactory(TextureMap::texture, block("chain_buoy_straight_chain", "_straight_chain"));
	public static final TexturedModel.Factory CHAIN_BUOY_DIAGONAL_CHAIN = TexturedModel.makeFactory(TextureMap::texture, block("chain_buoy_diagonal_chain", "_diagonal_chain"));

	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	private static Model block(String parent, TextureKey... requiredTextureKeys) {
		return new Model(Optional.of(Buoys.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
	}

	private static Model block(String parent, String variant, TextureKey... requiredTextureKeys) {
		return new Model(Optional.of(Buoys.id("block/" + parent)), Optional.of(variant), requiredTextureKeys);
	}

	public static TextureMap buoyTexture(Block block) {
		Identifier identifier = getId(block);
		identifier = Identifier.of(identifier.getNamespace(), identifier.getPath()
				.replace("chain_", ""));
		Buoys.LOGGER.info("Chain Buoy texture: {}", identifier);
		return TextureMap.texture(identifier);
	}

	public static BlockStateSupplier createChainBuoyBlockState(Block block, Identifier baseModelId, Identifier chainStraightModelId, Identifier chainDiagonalModelId, @Nullable Identifier buoyTopModelId) {
		return MultipartBlockStateSupplier.create(block)
				.with(When.create()
						.set(BuoyProperties.CHAIN_BUOY_STATE, ChainBuoyState.UPPER), BlockStateVariant.create()
						.put(VariantSettings.MODEL, getOrAir(buoyTopModelId)))
				.with(When.create()
						.setNegated(BuoyProperties.CHAIN_BUOY_STATE, ChainBuoyState.UPPER), BlockStateVariant.create()
						.put(VariantSettings.MODEL, baseModelId))
				.with(When.create()
						.set(BuoyProperties.CHAIN_BUOY_STATE, ChainBuoyState.LOWER_CHAIN_N), BlockStateVariant.create()
						.put(VariantSettings.MODEL, chainStraightModelId))
				.with(When.create()
						.set(BuoyProperties.CHAIN_BUOY_STATE, ChainBuoyState.LOWER_CHAIN_E), BlockStateVariant.create()
						.put(VariantSettings.MODEL, chainStraightModelId)
						.put(VariantSettings.Y, VariantSettings.Rotation.R90))
				.with(When.create()
						.set(BuoyProperties.CHAIN_BUOY_STATE, ChainBuoyState.LOWER_CHAIN_SW), BlockStateVariant.create()
						.put(VariantSettings.MODEL, chainDiagonalModelId))
				.with(When.create()
						.set(BuoyProperties.CHAIN_BUOY_STATE, ChainBuoyState.LOWER_CHAIN_SE), BlockStateVariant.create()
						.put(VariantSettings.MODEL, chainDiagonalModelId)
						.put(VariantSettings.Y, VariantSettings.Rotation.R90));

	}

	public static BlockStateSupplier createBuoyBlockState(Block block, Identifier buoyModelId, @Nullable Identifier buoyTopModelId) {
		return VariantsBlockStateSupplier.create(block)
				.coordinate(BlockStateVariantMap.create(Properties.DOUBLE_BLOCK_HALF)
						.register(DoubleBlockHalf.LOWER, BlockStateVariant.create()
								.put(VariantSettings.MODEL, buoyModelId))
						.register(DoubleBlockHalf.UPPER, BlockStateVariant.create()
								.put(VariantSettings.MODEL, getOrAir(buoyTopModelId))));
	}

	public static Identifier getOrAir(Identifier id) {
		return id == null ? Identifier.of("minecraft", "block/air") : id;
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator generator) {
		for (Block block : BuoyDataGenerator.BUOYS) {
			generator.blockStateCollector.accept(createBuoyBlockState(block, BUOY.upload(block, generator.modelCollector), null));
		}
		for (Block block : BuoyDataGenerator.CHAIN_BUOYS) {
			generator.blockStateCollector.accept(createChainBuoyBlockState(block, CHAIN_BUOY.upload(block, generator.modelCollector), CHAIN_BUOY_STRAIGHT_CHAIN.upload(block, generator.modelCollector), CHAIN_BUOY_DIAGONAL_CHAIN.upload(block, generator.modelCollector), null));
		}
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {

	}
}
