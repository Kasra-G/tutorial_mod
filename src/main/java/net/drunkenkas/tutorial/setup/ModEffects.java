package net.drunkenkas.tutorial.setup;

import net.drunkenkas.tutorial.effect.ModEffect;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.*;

/**
 * This class represents a copy of all the normal effects except that these do not render at all.
 */
public class ModEffects {
    public static final Effect MOVEMENT_SPEED = (new ModEffect(EffectType.BENEFICIAL, 8171462)).addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 0.2F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final Effect MOVEMENT_SLOWDOWN = (new ModEffect(EffectType.HARMFUL, 5926017)).addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final Effect DIG_SPEED = (new ModEffect(EffectType.BENEFICIAL, 14270531)).addAttributeModifier(Attributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final Effect DIG_SLOWDOWN = (new ModEffect(EffectType.HARMFUL, 4866583)).addAttributeModifier(Attributes.ATTACK_SPEED, "55FCED67-E92A-486E-9800-B47F202C4386", -0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL);
//    public static final Effect DAMAGE_BOOST = (new AttackDamageEffect(EffectType.BENEFICIAL, 9643043, 3.0D)).addAttributeModifier(Attributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.0D, AttributeModifier.Operation.ADDITION));
    public static final Effect HEAL = (new InstantEffect(EffectType.BENEFICIAL, 16262179));
    public static final Effect HARM = (new InstantEffect(EffectType.HARMFUL, 4393481));
    public static final Effect JUMP = (new ModEffect(EffectType.BENEFICIAL, 2293580));
    public static final Effect CONFUSION = (new ModEffect(EffectType.HARMFUL, 5578058));
    public static final Effect REGENERATION = (new ModEffect(EffectType.BENEFICIAL, 13458603));
    public static final Effect DAMAGE_RESISTANCE = (new ModEffect(EffectType.BENEFICIAL, 10044730));
    public static final Effect FIRE_RESISTANCE = (new ModEffect(EffectType.BENEFICIAL, 14981690));
    public static final Effect WATER_BREATHING = (new ModEffect(EffectType.BENEFICIAL, 3035801));
    public static final Effect INVISIBILITY = (new ModEffect(EffectType.BENEFICIAL, 8356754));
    public static final Effect BLINDNESS = (new ModEffect(EffectType.HARMFUL, 2039587));
    public static final Effect NIGHT_VISION = (new ModEffect(EffectType.BENEFICIAL, 2039713));
    public static final Effect HUNGER = (new ModEffect(EffectType.HARMFUL, 5797459));
//    public static final Effect WEAKNESS = (new AttackDamageEffect(EffectType.HARMFUL, 4738376, -4.0D)).addAttributeModifier(Attributes.ATTACK_DAMAGE, "22653B89-116E-49DC-9B6B-9971489B5BE5", 0.0D, AttributeModifier.Operation.ADDITION));
    public static final Effect POISON = (new ModEffect(EffectType.HARMFUL, 5149489));
    public static final Effect WITHER = (new ModEffect(EffectType.HARMFUL, 3484199));
    public static final Effect HEALTH_BOOST = (new HealthBoostEffect(EffectType.BENEFICIAL, 16284963)).addAttributeModifier(Attributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, AttributeModifier.Operation.ADDITION);
//    public static final Effect ABSORPTION = (new AbsorptionEffect(EffectType.BENEFICIAL, 2445989));
    public static final Effect SATURATION = (new InstantEffect(EffectType.BENEFICIAL, 16262179));
    public static final Effect GLOWING = (new ModEffect(EffectType.NEUTRAL, 9740385));
    public static final Effect LEVITATION = (new ModEffect(EffectType.HARMFUL, 13565951));
    public static final Effect LUCK = (new ModEffect(EffectType.BENEFICIAL, 3381504).addAttributeModifier(Attributes.LUCK, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 1.0D, AttributeModifier.Operation.ADDITION));
    public static final Effect UNLUCK = (new ModEffect(EffectType.HARMFUL, 12624973)).addAttributeModifier(Attributes.LUCK, "CC5AF142-2BD2-4215-B636-2605AED11727", -1.0D, AttributeModifier.Operation.ADDITION);
    public static final Effect SLOW_FALLING = (new ModEffect(EffectType.BENEFICIAL, 16773073));
    public static final Effect CONDUIT_POWER = (new ModEffect(EffectType.BENEFICIAL, 1950417));
    public static final Effect DOLPHINS_GRACE = (new ModEffect(EffectType.BENEFICIAL, 8954814));

    /**
     * Classloading method.
     */
    public static void load() {}
}
