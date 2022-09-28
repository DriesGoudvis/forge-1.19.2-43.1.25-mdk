package net.dries008.coolmod.painting;

import net.dries008.coolmod.CoolMod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, CoolMod.MOD_ID);

    public static final RegistryObject<PaintingVariant> RICK = PAINTING_VARIANTS.register("rick",
            () -> new PaintingVariant(32,32));

    public static final RegistryObject<PaintingVariant> SHREK = PAINTING_VARIANTS.register("shrek",
            () -> new PaintingVariant(32,32));

    public static void register(IEventBus eventBus){
        PAINTING_VARIANTS.register(eventBus);
    }
}
