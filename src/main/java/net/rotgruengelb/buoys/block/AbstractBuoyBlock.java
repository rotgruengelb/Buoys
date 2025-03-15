package net.rotgruengelb.buoys.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractBuoyBlock extends Block implements FluidFillable {

	public AbstractBuoyBlock(Settings settings) {
		super(settings);
	}

	protected void breakDoubleBlock(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos otherHalfPos = isLower(state) ? pos.up() : pos.down();
		BlockState otherHalfState = world.getBlockState(otherHalfPos);

		if (otherHalfState.isOf(this)) {
			var flags = Block.NOTIFY_ALL;
			if (player.isCreative()) {
				flags = Block.NOTIFY_ALL | Block.SKIP_DROPS;
			}

			BlockState blockState2 = isLower(otherHalfState) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
			world.setBlockState(otherHalfPos, blockState2, flags);
			world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, otherHalfPos, Block.getRawIdFromState(otherHalfState));
		}
	}

	abstract protected boolean isLower(BlockState state);

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		if (!world.isClient) {
			world.setBlockState(pos.up(), getDefaultOtherHalf(state), Block.NOTIFY_ALL);
		}
	}

	abstract protected BlockState getDefaultOtherHalf(BlockState state);

	@Override
	public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isClient) {
			breakDoubleBlock(world, pos, state, player);
		}

		super.onBreak(world, pos, state, player);
		return state;
	}

	@Override
	abstract public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context);

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		if (world.getBlockState(pos)
				.isOf(Blocks.WATER)) {
			return world.getBlockState(pos.up())
					.isAir();
		}
		return false;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		if (isLower(state)) {
			return Fluids.WATER.getStill(true);
		}
		return Fluids.EMPTY.getDefaultState();
	}

	@Override
	public boolean canFillWithFluid(@Nullable PlayerEntity player, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
		return false;
	}

	@Override
	public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
		return false;
	}

	@Override
	abstract protected void appendProperties(StateManager.Builder<Block, BlockState> builder);
}

