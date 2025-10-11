package net.jolene.ninetofiveessentials.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CanBlock extends Block {
    public static final BooleanProperty CRUMPLED = BooleanProperty.of("crumpled");

    public CanBlock(Settings settings) {
        super(settings
                .offset(AbstractBlock.OffsetType.XZ)
                .dynamicBounds()
        );
        setDefaultState(getStateManager().getDefaultState().with(CRUMPLED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CRUMPLED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(CRUMPLED)) {
            return Block.createCuboidShape(4, 0, 4, 12, 6, 12); // 8x6x8
        }
        return Block.createCuboidShape(4, 0, 4, 12, 12, 12); // 8x12x8
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        if (!world.isClient && entity instanceof PlayerEntity && !state.get(CRUMPLED)) {
            world.setBlockState(pos, state.with(CRUMPLED, true), 3);
            world.playSound(null, pos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
    }
}
