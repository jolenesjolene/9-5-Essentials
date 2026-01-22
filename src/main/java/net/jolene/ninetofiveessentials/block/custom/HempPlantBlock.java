package net.jolene.ninetofiveessentials.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;


public class HempPlantBlock extends PlantBlock implements Fertilizable {


    public static final int MAX_AGE = 5;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);
    public static final BooleanProperty TOP = BooleanProperty.of("top");

    public HempPlantBlock(Settings settings) {
        super(settings.ticksRandomly());
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(AGE, 0)
                .with(TOP, false));
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return createCodec(HempPlantBlock::new);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE, TOP);
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, net.minecraft.block.ShapeContext context) {
        int age = state.get(AGE);
        if (age >= 3) {
            return VoxelShapes.cuboid(0, 0, 0, 1, 1 + ((age - 1) * 0.25f), 1);
        } else {
            return VoxelShapes.fullCube();
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return !state.get(TOP) && state.get(AGE) < MAX_AGE;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return !state.get(TOP) && state.get(AGE) < MAX_AGE;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (state.get(AGE) < MAX_AGE) {
            world.setBlockState(pos, state.with(AGE, state.get(AGE) + 1));
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int age = state.get(AGE);
        if (age >= MAX_AGE) return;

        int skyLight = world.getLightLevel(LightType.SKY, pos);
        int blockLight = world.getLightLevel(LightType.BLOCK, pos);

        boolean artificialLightStrong = blockLight > skyLight && blockLight >= 8;

        if (artificialLightStrong) {
            if (random.nextFloat() < 0.5f) {
                world.setBlockState(pos, state.with(AGE, age + 1));
            }
        }
        else {
            if (skyLight >= 9 && random.nextFloat() < 0.05f) {
                world.setBlockState(pos, state.with(AGE, age + 1));
            }
        }
    }
}
