package net.drunkenkas.tutorial.item;

import net.drunkenkas.tutorial.setup.ModEffects;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModArmorItem extends ArmorItem {
    public static final EffectInstance SILVER_BOOTS_EFFECT_INSTANCE = new EffectInstance(ModEffects.MOVEMENT_SPEED, 1, 5);
    public static final EffectInstance SILVER_HELMET_EFFECT_INSTANCE = new EffectInstance(Effects.NIGHT_VISION, 1);

    public ModArmorItem(IArmorMaterial armorMaterial, EquipmentSlotType equipmentSlotType, Properties properties) {
        super(armorMaterial, equipmentSlotType, properties);
    }
}
