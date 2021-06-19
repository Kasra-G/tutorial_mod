package net.drunkenkas.tutorial.entity;

import net.drunkenkas.tutorial.setup.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * This class contains a few event listeners relating to entities.
 */
public class LivingListener {

    /** The range at which the modified silverfish behavior begins working. */
    public static final double silverfish_range = 24;

    /**
     * Alerts nearby silverfish that have the effect of a nearby player wearing full silver armor.
     *
     * @param event  the LivingDamageEvent that was fired
     */
    public static void onDamageTakenEvent(@Nonnull LivingDamageEvent event) {
        if (event.getEntity().getCommandSenderWorld().isClientSide()) {
            return;
        }

        //Do nothing if the silverfish was attacked
        LivingEntity damageReceiver = event.getEntityLiving();
        if (damageReceiver == null || damageReceiver instanceof SilverfishEntity) {
            return;
        }

        Entity sourceEntity = event.getSource().getEntity();
        if (!(sourceEntity instanceof LivingEntity)) {
            return;
        }
        LivingEntity damageSource = (LivingEntity) sourceEntity;

        //Figure out if the player was damaged or if the player caused the damage to an entity
        LivingEntity target = null;
        if (damageSource instanceof PlayerEntity || damageSource instanceof SilverfishEntity) {
            target = damageReceiver;
        } else if (damageReceiver instanceof PlayerEntity) {
            target = damageSource;
        }
        if (target == null) {
            return;
        }

        //alert nearby silverfish
        AxisAlignedBB bb = Util.newBoxCenteredAt(target.getX(), target.getY(), target.getZ());
        List<SilverfishEntity> nearbySilverfish = target.getCommandSenderWorld()
                .getLoadedEntitiesOfClass(SilverfishEntity.class, bb);
        for (SilverfishEntity s : nearbySilverfish) {
            if (s.hasEffect(ModEffects.MOVEMENT_SPEED) && !(target.equals(s.getTarget()))) {
                s.setTarget(target);
            }
        }
    }

    /**
     * Ensures silverfish cannot target players that are wearing a full set of silver armor.
     * Also Creepers will not target silverfish to keep them from blowing up
     *
     * @param event  the LivingSetAttackTargetEvent that is fired
     */
    public static void onEntityTargetedEvent(@Nonnull LivingSetAttackTargetEvent event) {
        if (event.getEntity().getCommandSenderWorld().isClientSide()) {
            return;
        }

        LivingEntity entity = event.getEntityLiving();
        if (entity instanceof SilverfishEntity) {
            SilverfishEntity silverfishEntity = (SilverfishEntity) entity;
            if (!(event.getTarget() instanceof PlayerEntity)) {
                return;
            }
            PlayerEntity player = (PlayerEntity) event.getTarget();

            //keep silverfish from targeting if its target is wearing a full set of silver armor
            if (Util.isWearingFullSilverSet(player)) {
                Util.resetTargeting(silverfishEntity);
            }
        //ensure Creepers cannot attack silverfish to keep them from blowing up
        } else if (entity instanceof CreeperEntity) {
            if (event.getTarget() instanceof SilverfishEntity) {
                ((CreeperEntity) entity).setTarget(null);
            }
        }
    }

    /**
     * Applies the effects on silverfish when a player with a full set of silver armor is nearby.
     *
     * @param event  the LivingUpdateEvent that is fired
     */
    public static void onLivingUpdateEvent(@Nonnull LivingEvent.LivingUpdateEvent event) {
        if (event.getEntity().getCommandSenderWorld().isClientSide()) {
            return;
        }

        LivingEntity entity = event.getEntityLiving();
        if (entity.getCommandSenderWorld().isClientSide()) {
            return;
        }
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (!Util.isWearingFullSilverSet(player)) {
                return;
            }

            AxisAlignedBB bb = Util.newBoxCenteredAt(entity.getX(), entity.getY(), entity.getZ());
            List<SilverfishEntity> nearbySilverfish = player.getCommandSenderWorld()
                    .getLoadedEntitiesOfClass(SilverfishEntity.class, bb);
            //apply the effects to all nearby silverfish
            for (SilverfishEntity silverfishEntity : nearbySilverfish) {
                silverfishEntity.addEffect(new EffectInstance(ModEffects.MOVEMENT_SPEED, 10, 1, true, false, false));
                silverfishEntity.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 10, 0, true, false, false));
                silverfishEntity.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 10, 0, true, false, false));
                silverfishEntity.addEffect(new EffectInstance(Effects.REGENERATION, 10, 0, true, false, false));
            }

            //teleport silverfish who are just outside of the player's range
            List<SilverfishEntity> allSilverFish = player.getCommandSenderWorld().getLoadedEntitiesOfClass(SilverfishEntity.class,
                    Util.newBoxCenteredAt(player.getX(), player.getY(), player.getZ(), silverfish_range * 2));
            for (SilverfishEntity silveryboi : allSilverFish) {
                if (silveryboi.hasEffect(ModEffects.MOVEMENT_SPEED)) {
                    if (player.distanceTo(silveryboi) >= silverfish_range) {
                        silveryboi.teleportTo(player.getX(), player.getY(), player.getZ());
                    }
                }
            }
        //ensures silverfish don't attack players with full sets of silver armor
        } else if (entity instanceof SilverfishEntity) {
            SilverfishEntity se = (SilverfishEntity) entity;
            if (se.hasEffect(ModEffects.MOVEMENT_SPEED)) {
                if (se.getTarget() != null && se.getTarget().getEntity() instanceof PlayerEntity) {
                    if (Util.isWearingFullSilverSet((PlayerEntity) se.getTarget().getEntity())) {
                        Util.resetTargeting(se);
                    }
                }
            }
        }
    }
}
