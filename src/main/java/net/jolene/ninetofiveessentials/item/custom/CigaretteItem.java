package net.jolene.ninetofiveessentials.item.custom;


import net.jolene.ninetofiveessentials.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CigaretteItem extends Item {

    public CigaretteItem(Settings settings) {
        super(settings);
    }

    @Override

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            Hand offHand = hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;
            ItemStack offHandStack = user.getStackInHand(offHand);

            if (offHandStack.getItem() == Items.FLINT_AND_STEEL) {
                // Damage the flint and steel by 1
                offHandStack.damage(1, user);
                world.playSound(
                        null,
                        user.getX(),
                        user.getY(),
                        user.getZ(),
                        SoundEvents.ITEM_FLINTANDSTEEL_USE,
                        SoundCategory.MASTER,
                        0.5F,
                        1.0F / (world.getRandom().nextFloat() * 0.9F + 1.1F));

                Vec3d lookVec = user.getRotationVec(1.0F);
                double x = user.getX() + lookVec.x * 0.5;
                double y = user.getY() + 1.6;
                double z = user.getZ() + lookVec.z * 0.5;

                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.LAVA,
                        x, y, z,
                        5, 0, 0, 0, 0
                );

                // Replace the current item with a different item (example: DIAMOND)
                ItemStack newItem = new ItemStack(ModItems.LIT_CIGARETTE); // replace with your desired item
                user.setStackInHand(hand, newItem);

                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
