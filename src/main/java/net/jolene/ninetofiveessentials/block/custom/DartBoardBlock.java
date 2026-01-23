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
    public static final IntProperty POWER = Properties.POWER;  // 0–15
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
        if (!world.isClient()) {
            BlockPos pos = hit.getBlockPos();
            double fx = hit.getPos().getX() - pos.getX();
            double fy = hit.getPos().getY() - pos.getY();
            double cz = hit.getPos().getZ() - pos.getZ();
            Direction face = state.get(HorizontalFacingBlock.FACING);
            double dx, dy;
            if (face == Direction.NORTH || face == Direction.SOUTH) {
                dx = fx - 0.5;
                dy = fy - 0.5;
            } else {
                dx = cz - 0.5;
                dy = fy - 0.5;
            }
            double dist = Math.sqrt(dx * dx + dy * dy);
            double max = Math.sqrt(0.5 * 0.5 + 0.5 * 0.5);
            double norm = Math.max(0.0, 1.0 - (dist / max));
            int strength = MathHelper.clamp((int)Math.ceil(norm * 7.0) + 1, 1, 8);

            int comp = (int) MathHelper.clamp(Math.round(strength * (15f / 8f)), 0, 15);

            world.setBlockState(pos, state.with(POWER, comp), 3);
            world.updateNeighbors(pos, this);
        }
        super.onProjectileHit(world, state, hit, projectile);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos, Direction direction) {
        return state.get(POWER);
    }
}
