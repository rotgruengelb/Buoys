package net.rotgruengelb.buoys.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.rotgruengelb.buoys.util.BuoyProperties;
import net.rotgruengelb.buoys.util.BuoyTags;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class ChainBuoyBlock extends AbstractBuoyBlock {
	public static final EnumProperty<BuoyProperties.ChainBuoyState> STATE = BuoyProperties.CHAIN_BUOY_STATE;
	private static final VoxelShape SHAPE = Block.createCuboidShape(4, 10, 4, 12, 18, 12);
	private static final VoxelShape UPPER_SHAPE = SHAPE.offset(0, -1, 0);

	public ChainBuoyBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState()
				.with(STATE, BuoyProperties.ChainBuoyState.LOWER));
	}

	@Override
	protected BlockState getDefaultOtherHalf(BlockState state) {
		return isLower(state) ? state.with(STATE, BuoyProperties.ChainBuoyState.UPPER) : state.with(STATE, BuoyProperties.ChainBuoyState.LOWER);
	}

	@Override
	public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockPos pos = ctx.getBlockPos();
		BlockView world = ctx.getWorld();
		AtomicReference<BlockState> blockState = new AtomicReference<>(getDefaultState());
		if (world.getBlockState(pos.add(ConnectionDirections.FORCE_NONE))
				.isAir()) {
			ConnectionDirections.DIRECTIONS.entrySet()
					.stream()
					.filter(entry -> canConnect(world, pos, entry.getKey()))
					.findFirst()
					.ifPresent(entry -> blockState.set(getDefaultState().with(STATE, entry.getValue())));
		}
		return blockState.get();
	}

	private boolean canConnect(BlockView world, BlockPos pos, Pair<Vec3i, Vec3i> direction) {
		return canConnect(world, pos, direction.getLeft()) || canConnect(world, pos, direction.getRight());
	}

	private boolean canConnect(BlockView world, BlockPos pos, Vec3i direction) {
		return world.getBlockState(pos.add(direction))
				.isIn(BuoyTags.Blocks.LIKE_BUOY);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
		if (!state.get(STATE)
				.isLower()) {
			return UPPER_SHAPE;
		}
		return SHAPE;
	}

	@Override
	protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	protected boolean isLower(BlockState state) {
		return state.get(STATE)
				.isLower();
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(STATE);
	}

	private static class ConnectionDirections {
		public static final Vec3i FORCE_NONE = new Vec3i(0, 2, 0);

		// @formatter:off
		public static final Map<Pair<Vec3i, Vec3i>, BuoyProperties.ChainBuoyState> DIRECTIONS = new LinkedHashMap<>() {{
			put(offsetPair(0, 0, 1), BuoyProperties.ChainBuoyState.LOWER_CHAIN_N);
			put(offsetPair(1, 0, 0), BuoyProperties.ChainBuoyState.LOWER_CHAIN_E);
			put(offsetPair(1, 0, 1), BuoyProperties.ChainBuoyState.LOWER_CHAIN_SE);
			put(offsetPair(-1, 0, 1), BuoyProperties.ChainBuoyState.LOWER_CHAIN_SW);
		}};
		// @formatter:on

		public static Pair<Vec3i, Vec3i> offsetPair(int x, int y, int z) {
			return new Pair<>(new Vec3i(x, y, z), new Vec3i(-x, -y, -z));
		}
	}
}
