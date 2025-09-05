package net.jolene.ninetofiveessentials.item.custom;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CigaretteItem extends Item {
    public CigaretteItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            // Cooldown
            user.getItemCooldownManager().set(getDefaultStack(), 60);

            // Consume one item
            stack.decrement(1);

            }
        return ActionResult.SUCCESS;
    }
}
