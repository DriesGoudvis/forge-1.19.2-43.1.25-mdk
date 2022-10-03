package net.dries008.coolmod.fluid;

import com.mojang.math.Vector3f;
import net.dries008.coolmod.CoolMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation P_OVERLAY_RL = new ResourceLocation("misc/in_p_water");

    public static final DeferredRegister<FluidType> FLUID_TYPE =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, CoolMod.MOD_ID);

    public static final RegistryObject<FluidType> P_WATER_FLUID_TYPE= register("p_water_fluid",
            FluidType.Properties.create().canDrown(true).canPushEntity(true).canSwim(true).supportsBoating(true)
                    .lightLevel(2).density(15).viscosity(2).sound(SoundAction.get("drink"),
                            SoundEvents.HONEY_DRINK));


    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties){
        return FLUID_TYPE.register(name, ()->new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, P_OVERLAY_RL,
                0x99e9fa00, new Vector3f(233f/255f, 250f/255f, 0f/255f), properties ));
    }

    public static void register(IEventBus eventBus){
        FLUID_TYPE.register(eventBus);
    }

}
