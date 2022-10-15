package net.dries008.coolmod;

import com.mojang.logging.LogUtils;
import net.dries008.coolmod.block.ModBlocks;
import net.dries008.coolmod.block.entity.ModBlockEntities;
import net.dries008.coolmod.fluid.ModFluidTypes;
import net.dries008.coolmod.fluid.ModFluids;
import net.dries008.coolmod.item.ModItems;
import net.dries008.coolmod.networking.ModMessage;
import net.dries008.coolmod.painting.ModPaintings;
import net.dries008.coolmod.recipe.ModRecipes;
import net.dries008.coolmod.screen.ModMenuTypes;
import net.dries008.coolmod.screen.PrecisionOperationTableScreen;
import net.dries008.coolmod.villager.ModVillagers;
import net.dries008.coolmod.world.feature.ModConfiguredFeatures;
import net.dries008.coolmod.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(CoolMod.MOD_ID)
public class CoolMod
{
    public static final String MOD_ID = "coolmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CoolMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModVillagers.register(modEventBus);
        ModPaintings.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ModMessage.register();
            ModVillagers.registerPOIs();
        });

    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_P_WATER.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_P_WATER.get(), RenderType.translucent());

            MenuScreens.register(ModMenuTypes.PRECISION_OPERATION_TABLE_MENU.get(), PrecisionOperationTableScreen::new);
        }
    }
}
