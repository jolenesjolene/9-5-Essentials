package net.jolene.ninetofiveessentials.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent BAGUETTE = new FoodComponent.Builder().nutrition(18).saturationModifier(0.6F).build();
    public static final FoodComponent COFFEE_CHERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodComponent COFFEE_GUM = new FoodComponent.Builder().nutrition(0).saturationModifier(0F).alwaysEdible().build();
    public static final FoodComponent VODKA = new FoodComponent.Builder().nutrition(6).saturationModifier(0.1F).alwaysEdible().build();
    public static final FoodComponent BEER = new FoodComponent.Builder().nutrition(6).saturationModifier(0.1F).alwaysEdible().build();
    public static final FoodComponent BROWNIE = new FoodComponent.Builder().nutrition(5).saturationModifier(0.6F).build();
    public static final FoodComponent FUNKY_BROWNIE = new FoodComponent.Builder().nutrition(5).saturationModifier(0.6F).build();
    public static final FoodComponent CHOCOLATE_FISH = new FoodComponent.Builder().nutrition(5).saturationModifier(0.6F).build();

}
