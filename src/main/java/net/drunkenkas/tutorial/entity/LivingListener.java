package net.drunkenkas.tutorial.entity;

import net.drunkenkas.tutorial.setup.SilverArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

import java.util.Optional;

public class LivingListener {
    public static void registerEvents() {
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onEntityTargetedEvent);
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onLivingUpdateEvent);
    }

    public static void onEntityTargetedEvent(LivingSetAttackTargetEvent event) {
        Entity entity = event.getEntity();
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
        Entity entity = event.getEntity();
        if (!(entity instanceof SilverfishEntity)) {
            return;
        }
        SilverfishEntity silverfishEntity = (SilverfishEntity) entity;
        if (!(silverfishEntity.getTarget() instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) silverfishEntity.getTarget();

        if (playerWearingFullSilverSet(player)) {
            resetSilverfishTargeting(silverfishEntity);
        }
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
