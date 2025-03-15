package net.rotgruengelb.buoys.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.rotgruengelb.buoys.Buoys;

public class BuoyBlockItem extends BlockItem {

	public BuoyBlockItem(Block block, Item.Settings settings) {
		super(block, settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

		Vec3d vec = new Vec3d(0, 0, 1).rotateX(-player.getPitch() * MathHelper.RADIANS_PER_DEGREE)
				.rotateY(-player.getHeadYaw() * MathHelper.RADIANS_PER_DEGREE);


		BlockHitResult hit = world.raycast(new RaycastContext(
				player.getEyePos(),
				player.getEyePos().add(vec.multiply(player.getBlockInteractionRange())),
				RaycastContext.ShapeType.COLLIDER,
				RaycastContext.FluidHandling.WATER,
				ShapeContext.absent())
		);

		ItemStack stack = player.getStackInHand(hand);

		boolean placed = false;
		if (world.getBlockState(hit.getBlockPos()).isOf(Blocks.WATER)) {
			if (player.canModifyAt(world, hit.getBlockPos()) && player.canModifyBlocks()) {
				placed = this.place(new ItemPlacementContext(player, hand, stack, hit)).isAccepted();
			}
		}

		return placed ? TypedActionResult.success(stack) : TypedActionResult.pass(stack);
	}
}