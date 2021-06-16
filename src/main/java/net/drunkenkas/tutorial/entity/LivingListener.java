package net.drunkenkas.tutorial.entity;

import net.drunkenkas.tutorial.setup.ModEffects;
import net.drunkenkas.tutorial.setup.SilverArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

import java.util.List;
import java.util.Optional;

public class LivingListener {
    public static final double silverfish_range = 32;
    public static final EffectInstance FRIENDLY_SILVER_EFFECT_INSTANCE = new EffectInstance(ModEffects.MOVEMENT_SPEED, 5, 2, true, false, false);
    public static void registerEvents() {
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onEntityTargetedEvent);
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onLivingUpdateEvent);
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onDamageTakenEvent);
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onEntitySpawn);
    }

    public static void onEntitySpawn(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof PhantomEntity) {
            event.getEntity().remove();
        }
    }

    public static void onDamageTakenEvent(LivingDamageEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity == null || entity instanceof SilverfishEntity || entity.getCommandSenderWorld().isClientSide) {
            return;
        }

        Entity sourceEntity = event.getSource().getEntity();
        if (!(sourceEntity instanceof LivingEntity)) {
            return;
        }
        LivingEntity livingSourceEnt = (LivingEntity) sourceEntity;

        //Figure out if the player was damaged or if the player caused the damage to an entity
        LivingEntity target = null;
        LivingEntity other = null;
        if (livingSourceEnt instanceof PlayerEntity || livingSourceEnt instanceof SilverfishEntity) {
            target = entity;
            other = livingSourceEnt;
        } else if (entity instanceof PlayerEntity) {
            target = livingSourceEnt;
            other = entity;
        }
        if (target == null) {
            return;
        }

        AxisAlignedBB bb = centeredCubeOfSize(target.getX(), target.getY(), target.getZ());
        List<SilverfishEntity> nearbySilverfish = target.getCommandSenderWorld()
                .getNearbyEntities(SilverfishEntity.class,
                        EntityPredicate.DEFAULT.allowUnseeable().range(silverfish_range), target, bb);
        for (SilverfishEntity s : nearbySilverfish) {
            if (s.hasEffect(ModEffects.MOVEMENT_SPEED) && !(target.equals(s.getTarget()))) {
                s.setTarget(target);
            }
        }
        if (other instanceof SilverfishEntity) {
            SilverfishEntity s = (SilverfishEntity) other;
            if (s.hasEffect(ModEffects.MOVEMENT_SPEED) && !(target.equals(s.getTarget()))) {
                s.setTarget(target);
            }
        }

    }

    public static void onEntityTargetedEvent(LivingSetAttackTargetEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (!(entity instanceof SilverfishEntity)) {
            return;
        }
        SilverfishEntity silverfishEntity = (SilverfishEntity) entity;
        if (!(event.getTarget() instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) event.getTarget();

        if (playerWearingFullSilverSet(player)) {
            resetSilverfishTargeting(silverfishEntity);
        }
    }

    public static void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity.getCommandSenderWorld().isClientSide()) {
            return;
        }
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (!playerWearingFullSilverSet(player)) {
                return;
            }

            AxisAlignedBB bb = centeredCubeOfSize(entity.getX(), entity.getY(), entity.getZ());
            List<SilverfishEntity> nearbySilverfish = player.getCommandSenderWorld()
                    .getNearbyEntities(SilverfishEntity.class,
                            EntityPredicate.DEFAULT.allowUnseeable(), player, bb);
            for (SilverfishEntity silverfishEntity : nearbySilverfish) {
                silverfishEntity.addEffect(new EffectInstance(ModEffects.MOVEMENT_SPEED, 10, 2, true, false, false));
                silverfishEntity.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 10, 0, true, false, false));
            }
            List<SilverfishEntity> allSilverFish = player.getCommandSenderWorld().getLoadedEntitiesOfClass(SilverfishEntity.class,
                    centeredCubeOfSize(player.getX(), player.getY(), player.getZ(), silverfish_range * 4));
            for (SilverfishEntity silveryboi : allSilverFish) {
                if (silveryboi.hasEffect(ModEffects.MOVEMENT_SPEED)) {
                    if (player.distanceTo(silveryboi) >= silverfish_range) {
                        silveryboi.teleportTo(player.getX(), player.getY(), player.getZ());
                    }
                }
            }
        } else if (entity instanceof SilverfishEntity) {
            SilverfishEntity se = (SilverfishEntity) entity;
            if (se.hasEffect(ModEffects.MOVEMENT_SPEED)) {
                if (se.getTarget() != null && se.getTarget().getEntity() instanceof PlayerEntity) {
                    if (playerWearingFullSilverSet((PlayerEntity) se.getTarget().getEntity())) {
                        resetSilverfishTargeting(se);
                    }
                }
            }
        }
    }

    private static AxisAlignedBB centeredCubeOfSize(double x, double y, double z) {
        return centeredCubeOfSize(x, y, z, silverfish_range * 2);
    }

    private static AxisAlignedBB centeredCubeOfSize(double x, double y, double z, double size) {
        return new AxisAlignedBB(x - size / 2, y - size / 2, z - size / 2,
                x + size / 2, y + size / 2, z + size / 2);
    }

    public static void resetSilverfishTargeting(SilverfishEntity entity) {
        Brain<?> brain = entity.getBrain();
        brain.setMemory(MemoryModuleType.ATTACK_TARGET, Optional.empty());
        brain.setMemory(MemoryModuleType.ANGRY_AT, Optional.empty());
        brain.setMemory(MemoryModuleType.UNIVERSAL_ANGER, Optional.empty());
        entity.setTarget(null);
    }

    private static boolean playerWearingFullSilverSet(PlayerEntity player) {
        for (ItemStack stack : player.getArmorSlots()) {
            Item item = stack.getItem();
            if (!(item instanceof SilverArmor)) {
                return false;
            }
        }
        return true;
    }
}