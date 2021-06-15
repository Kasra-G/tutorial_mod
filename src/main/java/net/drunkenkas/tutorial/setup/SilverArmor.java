package net.drunkenkas.tutorial.setup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraft.world.World;

public class SilverArmor extends ArmorItem {
    public final EffectInstance SILVER_BOOT_EFFECT = new EffectInstance(Effects.MOVEMENT_SPEED, 1);
    public SilverArmor(EquipmentSlotType equipmentSlotType, Properties properties) {
        super(ModArmorMaterial.SILVER, equipmentSlotType, properties);
        SILVER_BOOT_EFFECT.setNoCounter(true);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (stack.getItem().equals(ModItems.SILVER_BOOTS.get())) {
            player.addEffect(SILVER_BOOT_EFFECT);
        }
    }
}
