package net.jolene.ninetofiveessentials.block.custom;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class DartBoardBlock extends Block {
    // Use the existing facing property
    public static final IntProperty POWER = Properties.POWER;  // 0–15
    // FACING is from HorizontalFacingBlock
    // Block states need to include both
    private static final VoxelShape SHAPE_NORTH = Block.createCuboidShape(0, 0, 0.875 * 16, 16, 16, 16);
    private static final VoxelShape SHAPE_SOUTH = Block.createCuboidShape(0, 0, 0, 16, 16, 0.125 * 16);
    private static final VoxelShape SHAPE_WEST  = Block.createCuboidShape(0.875 * 16, 0, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_EAST  = Block.createCuboidShape(0, 0, 0, 0.125 * 16, 16, 16);

    public DartBoardBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager()
                .getDefaultState()
                .with(HorizontalFacingBlock.FACING, Direction.NORTH)
                .with(POWER, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HorizontalFacingBlock.FACING, POWER);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(HorizontalFacingBlock.FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rot) {
        return state.with(HorizontalFacingBlock.FACING, rot.rotate(state.get(HorizontalFacingBlock.FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(HorizontalFacingBlock.FACING)));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(HorizontalFacingBlock.FACING);
        switch (dir) {
            case NORTH:
                return SHAPE_NORTH;
            case SOUTH:
                return SHAPE_SOUTH;
            case WEST:
                return SHAPE_WEST;
            case EAST:
                return SHAPE_EAST;
            default:
                return VoxelShapes.fullCube();
        }
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (!world.isClient) {
            BlockPos pos = hit.getBlockPos();
            // Hit position relative inside block space (0.0 to 1.0)
            double fx = hit.getPos().getX() - pos.getX();
            double fy = hit.getPos().getY() - pos.getY();
            double cz = hit.getPos().getZ() - pos.getZ();
            // Determine 2D distance in the plane of the dartboard surface:
            Direction face = state.get(HorizontalFacingBlock.FACING);
            double dx, dy;
            if (face == Direction.NORTH || face == Direction.SOUTH) {
                // board is vertical in Z plane, so distance in X & Y
                dx = fx - 0.5;
                dy = fy - 0.5;
            } else {
                // EAST / WEST: board is along X plane, use Z & Y
                dx = cz - 0.5;
                dy = fy - 0.5;
            }
            double dist = Math.sqrt(dx * dx + dy * dy);
            // Max possible dist from center to corner ~ sqrt(0.5² + 0.5²) = ~0.707
            double max = Math.sqrt(0.5 * 0.5 + 0.5 * 0.5);
            double norm = Math.max(0.0, 1.0 - (dist / max));
            // Map to strength 1–8
            int strength = MathHelper.clamp((int)Math.ceil(norm * 7.0) + 1, 1, 8);

            // Convert your 1–8 scale to 0–15 for comparator; e.g. multiply by ~ ≈ (15/8)
            int comp = (int) MathHelper.clamp(Math.round(strength * (15f / 8f)), 0, 15);

            // Update block state power
            world.setBlockState(pos, state.with(POWER, comp), 3);
            // Notify neighbors for comparator update
            world.updateNeighbors(pos, this);
        }
        super.onProjectileHit(world, state, hit, projectile);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(POWER);
    }
}
