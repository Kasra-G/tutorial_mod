package net.drunkenkas.tutorial.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

/**
 * This class defines how a ModEffect behaves compared to an Effect.
 */
public class ModEffect extends Effect {

    /**
     * Constructs a new ModEffect.
     *
     * @param effectType  the EffectType of this new ModEffect
     * @param color  the color of this effect given as an integer
     */
    public ModEffect(EffectType effectType, int color) {
        super(effectType, color);
    }

    /**
     * Makes sure that no ModEffects render.
     *
     * @param effect  the EffectInstance (unused)
     * @return  whether the ModEffect should render
     */
    @Override
    public boolean shouldRender(EffectInstance effect) {
        return false;
    }

    /**
     * Makes sure the ModEffect does not render in the player's inventory.
     *
     * @param effect  the EffectInstance (unused)
     * @return  whether the ModEffect should render in the inventory
     */
    @Override
    public boolean shouldRenderInvText(EffectInstance effect) {
        return false;
    }

    /**
     * Makes sure the ModEffect does not render in the player's hud.
     *
     * @param effect  the EffectInstance (unused)
     * @return  whether the ModEffect should render in the player's hud
     */
    @Override
    public boolean shouldRenderHUD(EffectInstance effect) {
        return false;
    }
}
