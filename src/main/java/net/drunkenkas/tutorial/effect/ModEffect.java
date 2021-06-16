package net.drunkenkas.tutorial.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class ModEffect extends Effect {
    public ModEffect(EffectType effectType, int color) {
        super(effectType, color);
    }

    @Override
    public boolean shouldRender(EffectInstance effect) {
        return false;
    }

    @Override
    public boolean shouldRenderInvText(EffectInstance effect) {
        return false;
    }

    @Override
    public boolean shouldRenderHUD(EffectInstance effect) {
        return false;
    }
}
