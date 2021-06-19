package net.drunkenkas.tutorial.entity;

import net.drunkenkas.tutorial.item.ModArmorMaterial;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * This is a utility class for the entity package.
 */
public class Util {

    /**
     * Registers the event listeners to the Forge Event Bus.
     */
    public static void registerEvents() {
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onEntityTargetedEvent);
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onLivingUpdateEvent);
        MinecraftForge.EVENT_BUS.addListener(LivingListener::onDamageTakenEvent);
    }

    /**
     * Resets the targeting of the specified entity.
     *
     * @param entity the entity to reset the targeting for.
     */
    public static void resetTargeting(@Nonnull MobEntity entity) {
        Brain<?> brain = entity.getBrain();
        brain.setMemory(MemoryModuleType.ATTACK_TARGET, Optional.empty());
        brain.setMemory(MemoryModuleType.ANGRY_AT, Optional.empty());
        brain.setMemory(MemoryModuleType.UNIVERSAL_ANGER, Optional.empty());
        entity.setTarget(null);
    }

    /**
     * Creates an AxisAlignedBB with the given radius and the center at the given coordinates.
     *
     * @param x  the x coordinate of the center
     * @param y  the y coordinate of the center
     * @param z  the z coordinate of the center
     * @param radius  the radius of the AxisAlignedBB
     * @return  the AxisAlignedBB with the specified radius and centered at the coordinates.
     */
    public static @Nonnull AxisAlignedBB newBoxCenteredAt(double x, double y, double z, double radius) {
        return new AxisAlignedBB(x - radius, y - radius, z - radius,
                x + radius, y + radius, z + radius);
    }

    /**
     * Creates an AxisAlignedBB centered at the given coordinates with the size of the silverfish targeting range.
     *
     * @param x  the x coordinate of the center
     * @param y  the y coordinate of the center
     * @param z  the z coordinate of the center
     * @return  the new AxisAlignedBB centered at the given coordinates
     */
    public static @Nonnull AxisAlignedBB newBoxCenteredAt(double x, double y, double z) {
        return newBoxCenteredAt(x, y, z, LivingListener.silverfish_range);
    }

    /**
     * Determines if the specified player is wearing a full set of silver armor.
     *
     * @param player  the player to check
     * @return  whether or not the player is wearing a full set of silver armor
     */
    public static boolean isWearingFullSilverSet(@Nonnull PlayerEntity player) {
        for (ItemStack stack : player.getArmorSlots()) {

            //false if the item in the player's inventory slots is not an ArmorItem.
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
}
