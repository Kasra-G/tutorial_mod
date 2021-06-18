package net.drunkenkas.tutorial.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class represents an AxeItem but allows for finer control.
 */
public class ModAxeItem extends AxeItem {

    /**
     * Constructs a new ModAxeItem with the given parameters.
     *
     * @param iItemTier  the ItemTier of this axe
     * @param baseAttackDamage  the base attack damage of this axe
     * @param baseAttackSpeed  the base attack speed of this axe
     * @param properties  the Properties of this axe
     */
    public ModAxeItem(IItemTier iItemTier, float baseAttackDamage, float baseAttackSpeed, Properties properties) {
        super(iItemTier, baseAttackDamage, baseAttackSpeed, properties);
    }

    /**
     * This method increases the damage done to Undead mobs with a Silver axe.
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
                    damageReceiver.hurt(DamageSource.playerAttack(player), ModSwordItem.UNDEAD_DAMAGE_ADDITION + 2F);
                }
            }
        }
        return super.hurtEnemy(itemStack, damageDealer, damageReceiver);
    }
}
