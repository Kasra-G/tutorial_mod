package net.drunkenkas.tutorial.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import javax.annotation.ParametersAreNonnullByDefault;

public class ModAxeItem extends AxeItem {
    public ModAxeItem(IItemTier iItemTier, float baseAttackDamage, float baseAttackSpeed, Properties properties) {
        super(iItemTier, baseAttackDamage, baseAttackSpeed, properties);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity damageReceiver, LivingEntity damageDealer) {
        if (this.getTier().equals(ModItemTier.SILVER)) {
            if (damageReceiver instanceof ZombieEntity) {
                if (damageDealer instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) damageDealer;
                    damageReceiver.hurt(DamageSource.playerAttack(player), ModSwordItem.ZOMBIE_DAMAGE_ADDITION + 2F);
                }
            }
        }
        return super.hurtEnemy(itemStack, damageDealer, damageReceiver);
    }
}
