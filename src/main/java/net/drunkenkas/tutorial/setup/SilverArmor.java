package net.drunkenkas.tutorial.setup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class SilverArmor extends ArmorItem {
    private static final EffectInstance SILVER_BOOTS_EFFECT_INSTANCE = new EffectInstance(ModEffects.MOVEMENT_SPEED, 1);
    private static final EffectInstance SILVER_HELMET_EFFECT_INSTANCE = new EffectInstance(Effects.NIGHT_VISION, 1);

    public SilverArmor(EquipmentSlotType equipmentSlotType, Properties properties) {
        super(ModArmorMaterial.SILVER, equipmentSlotType, properties);
    }

    /*
    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (stack.getItem().equals(ModItems.SILVER_HELMET.get())) {
            player.addEffect(SILVER_HELMET_EFFECT_INSTANCE);
        } else if (stack.getItem().equals(ModItems.SILVER_CHESTPLATE.get())) {

        } else if (stack.getItem().equals(ModItems.SILVER_LEGGINGS.get())) {

        } else if (stack.getItem().equals(ModItems.SILVER_BOOTS.get())) {
            player.addEffect(SILVER_BOOTS_EFFECT_INSTANCE);
        }
    }
    */
}
