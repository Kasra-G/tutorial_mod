package net.drunkenkas.tutorial.setup;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {
    public static final RegistryObject<Item> SILVER_INGOT = Registration.ITEMS.register("silver_ingot", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> SILVER_NUGGET = Registration.ITEMS.register("silver_nugget", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> SILVER_DUST = Registration.ITEMS.register("silver_dust", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public static final RegistryObject<Item> SILVER_SHOVEL = Registration.ITEMS.register("silver_shovel", () ->
            new ShovelItem(ModItemTier.SILVER, 1.5F, -2.0F, new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<Item> SILVER_PICKAXE = Registration.ITEMS.register("silver_pickaxe", () ->
            new PickaxeItem(ModItemTier.SILVER, 1, -1.8F, new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<Item> SILVER_AXE = Registration.ITEMS.register("silver_axe", () ->
            new AxeItem(ModItemTier.SILVER, 5, -2.0F, new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<Item> SILVER_HOE = Registration.ITEMS.register("silver_hoe", () ->
            new HoeItem(ModItemTier.SILVER, 0, 0F, new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    public static final RegistryObject<Item> SILVER_SWORD = Registration.ITEMS.register("silver_sword", () ->
            new SwordItem(ModItemTier.SILVER, 3, -1.4F, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> SILVER_BOOTS = Registration.ITEMS.register("silver_boots", () ->
            new ArmorItem(ModArmorMaterial.SILVER, EquipmentSlotType.FEET, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> SILVER_LEGGINGS = Registration.ITEMS.register("silver_leggings", () ->
            new ArmorItem(ModArmorMaterial.SILVER, EquipmentSlotType.FEET, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> SILVER_CHESTPLATE = Registration.ITEMS.register("silver_chestplate", () ->
            new ArmorItem(ModArmorMaterial.SILVER, EquipmentSlotType.FEET, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> SILVER_HELMET = Registration.ITEMS.register("silver_helmet", () ->
            new ArmorItem(ModArmorMaterial.SILVER, EquipmentSlotType.FEET, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    public static void register() {}
}
