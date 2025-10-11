package net.jolene.ninetofiveessentials.block.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.jolene.ninetofiveessentials.particle.Coin;
import net.jolene.ninetofiveessentials.particle.ModParticles;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.x;
import static net.minecraft.block.SnowBlock.LAYERS;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.world.explosion.Explosion;

import java.util.Map;
import java.util.WeakHashMap;

public class CoinLayerBlock extends Block {
    public static final IntProperty LAYERS = Properties.LAYERS; // 1-8 layers

    private static final VoxelShape[] LAYER_SHAPES = new VoxelShape[] {
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    };

    public CoinLayerBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LAYERS, 1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LAYERS);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return LAYER_SHAPES[state.get(LAYERS) - 1];
    }

    public ActionResult use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int currentLayers = state.get(LAYERS);
        ItemStack held = player.getStackInHand(hand);

        if (!world.isClient() && held.isEmpty() && currentLayers < 8) {
            world.setBlockState(pos, state.with(LAYERS, currentLayers + 1), 3);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    public void onBlockDestroyedByPlayer(World world, BlockPos pos, BlockState state) {
        int layers = state.get(LAYERS);
        for (int i = 0; i < layers; i++) {
            dropStack(world, pos, new ItemStack(ModBlocks.COIN.asItem()));
        }

    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);

        if (world.isClient() && entity.getVelocity().lengthSquared() > 0.0001) {
            double x = entity.getX();
            double y = entity.getY() + 0.1;
            double z = entity.getZ();

            ClientWorld clientWorld = (ClientWorld) world;
            clientWorld.addParticleClient(
                    ModParticles.COIN,
                    x, y, z,
                    (world.random.nextDouble() - 0.5) * 0.1,
                    0.04,  // constant upward velocity
                    (world.random.nextDouble() - 0.5) * 0.1
            );
        }
    }
}