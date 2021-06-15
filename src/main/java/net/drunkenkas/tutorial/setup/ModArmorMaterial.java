package net.drunkenkas.tutorial.setup;

import net.drunkenkas.tutorial.TutorialMod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {
    SILVER(TutorialMod.MOD_ID + ":silver", 25, new int[]{3, 6, 7, 3}, 25, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F,
            () -> Ingredient.of(ModTags.Items.INGOTS_SILVER));
    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    ModArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound,
                     float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlotType equipmentSlotType) {
        return HEALTH_PER_SLOT[equipmentSlotType.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlotType equipmentSlotType) {
        return this.slotProtections[equipmentSlotType.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Nonnull
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Nonnull
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Nonnull
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
