package net.drunkenkas.tutorial.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class represents a SwordItem but allows for more control
 */
public class ModSwordItem extends SwordItem {

    /** The damage addition to undead mobs */
    public static final float UNDEAD_DAMAGE_ADDITION = 18F;

    /**
     * Constructs a new ModSwordItem with the given parameters.
     *
     * @param iItemTier  the ItemTier of this sword
     * @param baseAttackDamage  the base attack damage of this sword
     * @param baseAttackSpeed  the base attack speed of this sword
     * @param properties  the Properties of this sword
     */
    public ModSwordItem(IItemTier iItemTier, int baseAttackDamage, float baseAttackSpeed, Properties properties) {
        super(iItemTier, baseAttackDamage, baseAttackSpeed, properties);
    }

    //TODO: properly calculate the damage to do to an entity and modify it.
    /**
     * This method increases the damage done to Undead mobs with a Silver sword.
     *
     * @param itemStack  the item used to attack
     * @param damageReceiver  the LivingEntity receiving the damage
     * @param damageDealer  the LivingEntity dealing the damage
     * @return  true
     */
    @Override
    @ParametersAreNonnullByDefault
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity damageReceiver, LivingEntity damageDealer) {
        if (this.getTier().equals(ModItemTier.SILVER)) {
            if (damageReceiver instanceof ZombieEntity || damageReceiver instanceof SkeletonEntity) {
                if (damageDealer instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) damageDealer;
                    damageReceiver.hurt(DamageSource.playerAttack(player), UNDEAD_DAMAGE_ADDITION);
                }
            }
        }
        return super.hurtEnemy(itemStack, damageDealer, damageReceiver);
    }
}
