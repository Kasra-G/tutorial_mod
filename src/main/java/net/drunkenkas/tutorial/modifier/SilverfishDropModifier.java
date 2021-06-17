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

public class SilverfishDropModifier extends LootModifier {
    private final float dropChance;
    private final Item dropItem;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    public SilverfishDropModifier(ILootCondition[] conditionsIn, final Item dropItem, final float dropChance) {
        super(conditionsIn);
        this.dropItem = dropItem;
        this.dropChance = dropChance;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(getLoot(context));
        return generatedLoot;
    }

    private ItemStack getLoot(LootContext context) {
        Random randy = context.getRandom();
        double amount = 0;
        for (int i = 0; i < context.getLootingModifier() + 1; i++) {
            if (randy.nextDouble() <= dropChance) {
                amount++;
            }
        }
        return new ItemStack(dropItem, (int) amount);
    }

    public static class Serializer extends GlobalLootModifierSerializer<SilverfishDropModifier> {

        @Override
        public SilverfishDropModifier read(ResourceLocation location, JsonObject object, ILootCondition[] conditionsIn) {
            Item dropItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(object, "dropItem")));
            float dropChance = JSONUtils.getAsFloat(object, "dropChance");
            return new SilverfishDropModifier(conditionsIn, dropItem, dropChance);
        }

        @Override
        public JsonObject write(SilverfishDropModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("dropItem", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(instance.dropItem)).toString());
            json.addProperty("dropChance", instance.dropChance);
            return json;
        }
    }
}
