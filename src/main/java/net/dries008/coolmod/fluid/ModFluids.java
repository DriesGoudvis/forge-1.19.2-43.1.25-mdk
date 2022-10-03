package net.dries008.coolmod.fluid;

import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.block.ModBlocks;
import net.dries008.coolmod.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, CoolMod.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_P_WATER = FLUIDS.register("p_water_fluid",
            ()->new ForgeFlowingFluid.Source(ModFluids.P_WATER_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_P_WATER = FLUIDS.register("flowing_p_water",
            ()-> new ForgeFlowingFluid.Flowing(ModFluids.P_WATER_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties P_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.P_WATER_FLUID_TYPE, SOURCE_P_WATER, FLOWING_P_WATER)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.P_WATER_BLOCK).bucket(ModItems.P_WATER_BUCKET);


    public static void register(IEventBus eventBus){
        FLUIDS.register(eventBus);
    }
}
