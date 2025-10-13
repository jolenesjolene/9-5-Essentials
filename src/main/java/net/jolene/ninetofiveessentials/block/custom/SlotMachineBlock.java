package net.jolene.ninetofiveessentials.block.custom;

import net.jolene.ninetofiveessentials.particle.ModParticles;
import net.jolene.ninetofiveessentials.sound.ModSounds;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;



public class SlotMachineBlock extends Block {
    public static final Property<Direction> FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty RESULT = IntProperty.of("result", 0, 6);
    public static final BooleanProperty ROLLING = BooleanProperty.of("rolling");
    public static final BooleanProperty DELAY_PHASE = BooleanProperty.of("delay_phase");
    public static final BooleanProperty FLASHING_LIGHT = BooleanProperty.of("flashing_light");
    public static final IntProperty COUNTDOWN = IntProperty.of("countdown", 0, 100);
    public static final IntProperty POWER = Properties.POWER;

    private static final int DELAY_TICKS = 20;
    private static final int ROLL_TICKS = 80;
    private static final int PARTICLE_TICK_INTERVAL = 8;

    private static final VoxelShape SHAPE_NORTH = VoxelShapes.union(
            VoxelShapes.cuboid(0.0, 0.0, 0.25, 1.0, 1.0, 1.0),
            VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.25, 0.25)
    );

    private static final VoxelShape SHAPE_SOUTH = VoxelShapes.union(
            VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 1.0, 0.75),
            VoxelShapes.cuboid(0.0, 0.0, 0.75, 1.0, 0.25, 1.0)
    );

    private static final VoxelShape SHAPE_WEST = VoxelShapes.union(
            VoxelShapes.cuboid(0.25, 0.0, 0.0, 1.0, 1.0, 1.0),
            VoxelShapes.cuboid(0.0, 0.0, 0.0, 0.25, 0.25, 1.0)
    );

    private static final VoxelShape SHAPE_EAST = VoxelShapes.union(
            VoxelShapes.cuboid(0.0, 0.0, 0.0, 0.75, 1.0, 1.0),
            VoxelShapes.cuboid(0.75, 0.0, 0.0, 1.0, 0.25, 1.0)
    );

    public SlotMachineBlock(Settings settings) {
        super(settings.luminance(state -> state.get(FLASHING_LIGHT) ? 6 : 0));
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(RESULT, 0)
                .with(ROLLING, false)
                .with(DELAY_PHASE, false)
                .with(FLASHING_LIGHT, false)
                .with(COUNTDOWN, 0)
                .with(POWER, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, RESULT, ROLLING, DELAY_PHASE, FLASHING_LIGHT, COUNTDOWN, POWER);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = state.get(FACING);
        return switch (facing) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getOutlineShape(state, world, pos, context);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return !state.get(ROLLING) && state.get(RESULT) != 0;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(ROLLING) ? 0 : state.get(RESULT);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient) {
            ((ServerWorld) world).scheduleBlockTick(pos, this, 1);
        }
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            world.scheduleBlockTick(pos, this, 1);
        }
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean powered = world.getReceivedRedstonePower(pos) > 0;
        int prevPower = state.get(POWER);
        boolean rolling = state.get(ROLLING);
        boolean delayPhase = state.get(DELAY_PHASE);
        boolean flashing = state.get(FLASHING_LIGHT);
        int countdown = state.get(COUNTDOWN);
        int result = state.get(RESULT);

        int newPower = powered ? 15 : 0;

        if (newPower != prevPower) {
            state = state.with(POWER, newPower);
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
        }

        if (rolling || delayPhase) {
            if (countdown > 0) {
                countdown--;
                BlockState updatedState = state.with(COUNTDOWN, countdown);

                if (!delayPhase) {
                    boolean lightOn = countdown % (PARTICLE_TICK_INTERVAL * 2) < PARTICLE_TICK_INTERVAL;
                    updatedState = updatedState.with(FLASHING_LIGHT, lightOn);

                    if (countdown % PARTICLE_TICK_INTERVAL == 0) {
                        spawnNoteParticle(world, pos);
                    }
                }

                world.setBlockState(pos, updatedState, Block.NOTIFY_ALL);
                world.scheduleBlockTick(pos, this, 1);
            } else {
                if (delayPhase) {
                    BlockState rollingState = state
                            .with(DELAY_PHASE, false)
                            .with(ROLLING, true)
                            .with(FLASHING_LIGHT, false)
                            .with(COUNTDOWN, ROLL_TICKS);

                    world.setBlockState(pos, rollingState, Block.NOTIFY_ALL);
                    playSound(world, pos, ModSounds.WHEEL);
                    world.scheduleBlockTick(pos, this, 1);
                } else {
                    int roll = random.nextInt(6) + 1;
                    BlockState resultState = state
                            .with(RESULT, roll)
                            .with(ROLLING, false)
                            .with(FLASHING_LIGHT, true)
                            .with(COUNTDOWN, 0);

                    world.setBlockState(pos, resultState, Block.NOTIFY_ALL);
                    spawnNoteParticle(world, pos);
                    spawnCoinParticles(world, pos); // ✅ NEW: spawn coins
                    playSound(world, pos, ModSounds.RESULT);
                }
            }
        } else {
            if (powered && prevPower == 0) {
                BlockState newState = state
                        .with(DELAY_PHASE, true)
                        .with(ROLLING, false)
                        .with(FLASHING_LIGHT, false)
                        .with(COUNTDOWN, DELAY_TICKS)
                        .with(RESULT, 0)
                        .with(POWER, 15);

                world.setBlockState(pos, newState, Block.NOTIFY_ALL);
                world.scheduleBlockTick(pos, this, 1);
            } else {
                if (!powered && prevPower != 0) {
                    world.setBlockState(pos, state.with(POWER, 0), Block.NOTIFY_ALL);
                }
                world.scheduleBlockTick(pos, this, 1);
            }
        }
    }

    private void spawnNoteParticle(ServerWorld world, BlockPos pos) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 1.1;
        double z = pos.getZ() + 0.5;
        world.spawnParticles(ParticleTypes.NOTE, x, y, z, 1, 0, 0, 0, 0);
    }

    // ✅ NEW METHOD: spawn 10 COIN particles
    private void spawnCoinParticles(ServerWorld world, BlockPos pos) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;
        world.spawnParticles(ModParticles.COIN, x, y, z, 20, 0.5, 0.5, 0.5, 2.0);
    }

    private void playSound(World world, BlockPos pos, SoundEvent sound) {
        world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}

