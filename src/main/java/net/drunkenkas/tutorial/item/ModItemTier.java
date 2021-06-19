package net.drunkenkas.tutorial.item;

import net.drunkenkas.tutorial.setup.ModTags;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * This enum defines all the ItemTiers in this mod.
 */
public enum ModItemTier implements IItemTier {

    /** Silver item tier */
    SILVER(2, 1215, 8F, 1F, 25, () -> Ingredient.of(ModTags.Items.INGOTS_SILVER));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    /**
     * Constructs a new ModItemTier with the given parameters.
     *
     * @param level  the harvest level of this ModItemTier
     * @param uses  the base number of uses of this ModItemTier
     * @param speed  the base speed of this ModItemTier
     * @param damage  the damage bonus of this ModItemTier
     * @param enchantmentValue  the enchantment value of this ModItemTier
     * @param repairIngredient  the repair ingredient of this ModItemTier given as a Supplier
     */
    ModItemTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    /**
     * Returns the number of uses of this ModItemTier.
     *
     * @return  the number of uses of this ModItemTier
     */
    public int getUses() {
        return this.uses;
    }

    /**
     * Returns the speed of this ModItemTier.
     *
     * @return  the speed of this ModItemTier
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Returns the damage bonus of this ModItemTier.
     *
     * @return  the damage bonus of this ModItemTier
     */
    public float getAttackDamageBonus() {
        return this.damage;
    }

    /**
     * Returns the harvest level of this ModItemTier.
     *
     * @return  the harvest level of this ModItemTier
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Returns the enchantment value of this ModItemTier.
     *
     * @return  the enchantment value of this ModItemTier
     */
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    /**
     * Returns the repair ingredient of this ModItemTier.
     *
     * @return the repair ingredient of this ModItemTier
     */
    @Nonnull
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
