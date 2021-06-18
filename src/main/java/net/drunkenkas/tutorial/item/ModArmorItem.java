package net.drunkenkas.tutorial.item;

import net.drunkenkas.tutorial.setup.ModEffects;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

/**
 * This class defines how ModArmorItems should behave.
 *
 * It allows for finer control over armor pieces
 */
public class ModArmorItem extends ArmorItem {
//    public static final EffectInstance SILVER_BOOTS_EFFECT_INSTANCE = new EffectInstance(ModEffects.MOVEMENT_SPEED, 1, 5);
//    public static final EffectInstance SILVER_HELMET_EFFECT_INSTANCE = new EffectInstance(Effects.NIGHT_VISION, 1);

    /**
     * Constructs a new ModArmorItem.
     *
     * @param armorMaterial  the IArmorMaterial to use
     * @param equipmentSlotType  the part of the body this ModArmorItem is for
     * @param properties  the properties of this ModArmorItem
     */
    public ModArmorItem(IArmorMaterial armorMaterial, EquipmentSlotType equipmentSlotType, Properties properties) {
        super(armorMaterial, equipmentSlotType, properties);
    }
}
