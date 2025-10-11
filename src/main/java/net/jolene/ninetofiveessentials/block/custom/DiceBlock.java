package net.jolene.ninetofiveessentials.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class DiceBlock extends FallingBlock {
    public static final EnumProperty<Direction> FACING = EnumProperty.of("facing", Direction.class);

    public DiceBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.UP));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {

        return this.getDefaultState();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallState, BlockState hitState, FallingBlockEntity entity) {
        super.onLanding(world, pos, fallState, hitState, entity);

        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            Direction randomFacing = Direction.random(world.getRandom());
            BlockState newState = fallState.with(FACING, randomFacing);


            world.setBlockState(pos, newState, 3);
            world.updateNeighbors(pos, this);

            int roll = getRollFromFacing(randomFacing);
            Text message = Text.literal("Rolled a " + roll + "!");
            serverWorld.getPlayers(p -> p.squaredDistanceTo(pos.toCenterPos()) <= 16 * 16)
                    .forEach(p -> p.sendMessage(message));


            serverWorld.spawnParticles(
                    new BlockStateParticleEffect(ParticleTypes.BLOCK, newState),
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    20,
                    0.3, 0.3, 0.3,
                    0.1
            );
        }
    }

    private int getRollFromFacing(Direction direction) {
        return switch (direction) {
            case UP -> 1;
            case DOWN -> 2;
            case NORTH -> 3;
            case SOUTH -> 4;
            case WEST -> 5;
            case EAST -> 6;
        };
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return getRollFromFacing(state.get(FACING));
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return false;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return 0;
    }

    @Override
    protected MapCodec<? extends FallingBlock> getCodec() {
        return null;
    }

    @Override
    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return 0;
    }
}
