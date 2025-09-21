package net.jolene.ninetofiveessentials.block.custom;

import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.jolene.ninetofiveessentials.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.apache.logging.log4j.Level;


public class NicotianaPlantBlock extends CropBlock {
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }
    public static final int MAX_AGE = 5;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);

    public NicotianaPlantBlock(Settings settings) {
        super(settings);
    }
    public OffsetType getOffsetType() {
        return OffsetType.XZ; // or OffsetType.XYZ
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.NICOTIANA_SEEDS;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int age = getAge(state);
        if (age < getMaxAge() && world.getBaseLightLevel(pos, 0) >= 9) {
            float chance = getAvailableMoisture(this, world, pos);
            if (random.nextInt((int)(25.0F / chance) + 1) == 0) {
                grow(world, state, pos, 1);
            }
        }
    }

    public void grow(ServerWorld world, BlockState state, BlockPos pos, int amount) {
        int newAge = Math.min(getAge(state) + amount, getMaxAge());
        world.setBlockState(pos, withAge(newAge), 2);

        if (newAge >= 3) {
            BlockPos above = pos.up();
            int topAge = Math.min(newAge - 3, 2);
            BlockState topState = ModBlocks.NICOTIANA_TOP.getDefaultState().with(NicotianaTopBlock.AGE, topAge);

            if (world.isAir(above) || world.getBlockState(above).getBlock() instanceof NicotianaTopBlock) {
                world.setBlockState(above, topState, 2);
            }
        }
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state,
                           BlockEntity blockEntity, ItemStack stack) {
        BlockPos above = pos.up();
        BlockState aboveState = world.getBlockState(above);
        if (aboveState.getBlock() instanceof NicotianaTopBlock) {
            world.breakBlock(above, true, player);
        }
        super.afterBreak(world, player, pos, state, blockEntity, stack);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        BlockPos above = pos.up();
        BlockState aboveState = world.getBlockState(above);
        if (aboveState.getBlock() instanceof NicotianaTopBlock) {
            world.breakBlock(above, false);
        }
        super.onBroken(world, pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int age = state.get(AGE);
        if (age < 3) {
            return VoxelShapes.cuboid(0, 0, 0, 1, (age + 1) * 0.25f, 1);
        } else {
            return VoxelShapes.fullCube();
        }
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        if (!(world instanceof ServerWorld serverWorld)) return;

        int currentAge = getAge(state);
        int maxAge = getMaxAge();

        if (currentAge < maxAge) {
            int growth = 1 + serverWorld.getRandom().nextInt(3); // 1–3 stages
            int newAge = Math.min(currentAge + growth, maxAge);
            grow(serverWorld, state, pos, newAge - currentAge);
        }
    }
}
