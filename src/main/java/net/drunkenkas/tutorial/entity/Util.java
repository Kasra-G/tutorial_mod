package net.drunkenkas.tutorial.entity;

import net.drunkenkas.tutorial.item.ModArmorMaterial;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;

import java.util.Optional;

public class Util {
    public static void registerEvents() {
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onEntityTargetedEvent);
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onLivingUpdateEvent);
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onDamageTakenEvent);
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onEntitySpawn);
    }

    public static void resetSilverfishTargeting(SilverfishEntity entity) {
        Brain<?> brain = entity.getBrain();
        brain.setMemory(MemoryModuleType.ATTACK_TARGET, Optional.empty());
        brain.setMemory(MemoryModuleType.ANGRY_AT, Optional.empty());
        brain.setMemory(MemoryModuleType.UNIVERSAL_ANGER, Optional.empty());
        entity.setTarget(null);
    }

    public static AxisAlignedBB centeredCubeOfSize(double x, double y, double z, double size) {
        return new AxisAlignedBB(x - size / 2, y - size / 2, z - size / 2,
                x + size / 2, y + size / 2, z + size / 2);
    }

    public static boolean isWearingFullSilverSet(PlayerEntity player) {
        for (ItemStack stack : player.getArmorSlots()) {
            Item item = stack.getItem();
            if (!(item instanceof ArmorItem)) {
                return false;
            }

            //check if the armor item is made of silver
            ArmorItem armorItem = (ArmorItem) item;
            if (!(armorItem.getMaterial().equals(ModArmorMaterial.SILVER))) {
                return false;
            }
        }
        return true;
    }

    public static AxisAlignedBB centeredCubeOfSize(double x, double y, double z) {
        return centeredCubeOfSize(x, y, z, LivingListener.silverfish_range * 2);
    }
}
