package net.dries008.coolmod.entity.client;

import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.entity.custom.SkullerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SkullerModel extends AnimatedGeoModel<SkullerEntity> {
    @Override
    public ResourceLocation getModelResource(SkullerEntity object) {
        return new ResourceLocation(CoolMod.MOD_ID, "geo/skuller.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SkullerEntity object) {
        return new ResourceLocation(CoolMod.MOD_ID, "textures/entity/skuller_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SkullerEntity animatable) {
        return new ResourceLocation(CoolMod.MOD_ID, "animations/skuller.animations.json");
    }
}
