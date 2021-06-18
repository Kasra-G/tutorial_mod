package net.drunkenkas.tutorial.item;

import net.drunkenkas.tutorial.TutorialMod;
import net.drunkenkas.tutorial.setup.ModTags;
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

/**
 * This enum defines all the ArmorMaterials in this mod.
 */
public enum ModArmorMaterial implements IArmorMaterial {

    /** Silver ModArmorMaterial. */
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

    /**
     * Creates an instance of ModArmorMaterial with the given parameters.
     * Since this constructor is for an enum, it is private by default.
     *
     * @param name  the name of this ArmorMaterial
     * @param durabilityMultiplier  the durability multiplier to be used against each armor piece
     * @param slotProtections  the protection values for each armor slot
     * @param enchantmentValue  the enchantment value of this ArmorMaterial
     * @param sound  the equip sound of this ArmorMaterial
     * @param toughness  the toughness of this ArmorMaterial
     * @param knockbackResistance  the knockback resistance of this ArmorMaterial
     * @param repairIngredient  the repair Ingredient of this ArmorMaterial in the form of a Supplier
     */
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

    /**
     * Returns the durability for the given armor slot of this ArmorMaterial.
     *
     * @param equipmentSlotType  the armor slot type
     * @return  the durability for the given armor slot
     */
    public int getDurabilityForSlot(EquipmentSlotType equipmentSlotType) {
        return HEALTH_PER_SLOT[equipmentSlotType.getIndex()] * this.durabilityMultiplier;
    }

    /**
     * Returns the protection for the given armor slot of this ArmorMaterial.
     *
     * @param equipmentSlotType  the armor slot type
     * @return  the protection for the given armor slot.
     */
    public int getDefenseForSlot(EquipmentSlotType equipmentSlotType) {
        return this.slotProtections[equipmentSlotType.getIndex()];
    }

    /**
     * Returns the enchantment value of this ArmorMaterial.
     *
     * @return  the enchantment value of this ArmorMaterial
     */
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    /**
     * Returns the equip sound of this ArmorMaterial.
     *
     * @return  the equip sound of this ArmorMaterial
     */
    @Nonnull
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    /**
     * Returns the repair ingredient of this ArmorMaterial.
     *
     * @return  the repair ingredient of this ArmorMaterial
     */
    @Nonnull
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    /**
     * Returns the name of this ArmorMaterial.
     * Only runs in the physical client side
     *
     * @return  the name of this ArmorMaterial
     */
    @Nonnull
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    /**
     * Returns the toughness of this ArmorMaterial.
     *
     * @return  the toughness of this ArmorMaterial
     */
    public float getToughness() {
        return this.toughness;
    }

    /**
     * Returns the knockback resistance of this ArmorMaterial.
     *
     * @return  the knockback resistance of this ArmorMaterial
     */
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
