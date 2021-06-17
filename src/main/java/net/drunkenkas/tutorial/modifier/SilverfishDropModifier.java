package net.drunkenkas.tutorial.modifier;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Modifies the loot dropped by the Silverfish on death.
 * The item dropped and the chance of drop is configurable.
 */
public class SilverfishDropModifier extends LootModifier {
    private final float dropChance;
    private final Item dropItem;

    /**
     * Constructs a SilverfishDropModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     * @param dropItem the Item to be dropped by the Silverfish on death
     * @param dropChance the float chance of dropping the Item by the Silverfish on death
     */
    public SilverfishDropModifier(ILootCondition[] conditionsIn, final Item dropItem, final float dropChance) {
        super(conditionsIn);
        this.dropItem = dropItem;
        this.dropChance = dropChance;
    }

    /**
     * Applies the modifier.
     *
     * @param generatedLoot the loot generated already
     * @param context the LootContext of this loot generation
     * @return the updated loot
     */
    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(getLoot(context));
        return generatedLoot;
    }

    /**
     * Gets the appropriate loot for the Silverfish to drop.
     * Also accounts for looting.
     *
     * @param context the LootContext of this loot generation
     * @return the added loot for the Silverfish
     */
    private ItemStack getLoot(LootContext context) {
        Random randy = context.getRandom();
        double amount = 0;

        //account for looting by just reapplying the drop for every level of looting.
        for (int i = 0; i < context.getLootingModifier() + 1; i++) {
            if (randy.nextDouble() <= dropChance) {
                amount++;
            }
        }
        return new ItemStack(dropItem, (int) amount);
    }

    /**
     * Serializer for the SilverfishDropModifier.
     * Created so that the class can read and write from the Loot modifier JSON file.
     */
    public static class Serializer extends GlobalLootModifierSerializer<SilverfishDropModifier> {

        /**
         * Reads the dropItem and dropChance from the JSON file.
         *
         * @param location the ResourceLocation of the serializer (unused)
         * @param object the JsonObject for the JSON file for the SilverfishDropModifier
         * @param conditionsIn the loot conditions
         * @return the new SilverfishDropModifier constructed from the JSON.
         */
        @Override
        public SilverfishDropModifier read(ResourceLocation location, JsonObject object, ILootCondition[] conditionsIn) {
            Item dropItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(object, "dropItem")));
            float dropChance = JSONUtils.getAsFloat(object, "dropChance");
            return new SilverfishDropModifier(conditionsIn, dropItem, dropChance);
        }

        /**
         * Encodes the given instance of SilvefishDropModifier as a JsonObject.
         *
         * @param instance the instance of SilverfishDropModifier to encode
         * @return the encoded JsonObject
         */
        @Override
        public JsonObject write(SilverfishDropModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("dropItem", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(instance.dropItem)).toString());
            json.addProperty("dropChance", instance.dropChance);
            return json;
        }
    }
}
