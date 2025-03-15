package net.rotgruengelb.buoys.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.stream.Stream;

public class BuoyBlock extends AbstractBuoyBlock {

	public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;
	private static final VoxelShape SHAPE = Stream.of(Block.createCuboidShape(2, 7, 2, 14, 19, 14), Block.createCuboidShape(6, 19, 6, 10, 32, 10)).reduce((voxelShape, voxelShape2) -> VoxelShapes.combineAndSimplify(voxelShape, voxelShape2, BooleanBiFunction.OR)).get();
	private static final VoxelShape UPPER_SHAPE = SHAPE.offset(0, -1, 0);

	public BuoyBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
	}

	@Override
	protected boolean isLower(BlockState state) {
		return state.get(HALF) == DoubleBlockHalf.LOWER;
	}

	@Override
	protected BlockState getDefaultOtherHalf(BlockState state) {
		return isLower(state) ? state.with(HALF, DoubleBlockHalf.UPPER) : state.with(HALF, DoubleBlockHalf.LOWER);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
		if (state.get(HALF) == DoubleBlockHalf.UPPER) {
			return UPPER_SHAPE;
		}
		return SHAPE;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(HALF);
	}
}

