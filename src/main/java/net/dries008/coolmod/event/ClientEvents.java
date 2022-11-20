package net.dries008.coolmod.event;

import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.block.entity.ModBlockEntities;
import net.dries008.coolmod.block.entity.renderer.PrecisionOperationTableEntityRenderer;
import net.dries008.coolmod.client.ThirstHudOverlay;
import net.dries008.coolmod.networking.ModMessage;
import net.dries008.coolmod.networking.packet.DrinkWaterC2S;
import net.dries008.coolmod.networking.packet.ExampleC2SPacket;
import net.dries008.coolmod.util.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = CoolMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents{
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){

            if(KeyBinding.TESTER.consumeClick()){
                ModMessage.sendToServer(new ExampleC2SPacket());
            }

            if(KeyBinding.DRINKING.consumeClick()){
                ModMessage.sendToServer(new DrinkWaterC2S());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = CoolMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class  ClientModBusEvents{
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.TESTER);
            event.register(KeyBinding.DRINKING);
        }

        @SubscribeEvent
        public static void registerGuiOverlay(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("thirst", ThirstHudOverlay.HUD_THIRST);
        }

        @SubscribeEvent
        public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event){
            event.registerBlockEntityRenderer(ModBlockEntities.PRECISION_OPERATION_TABLE.get(),
                    PrecisionOperationTableEntityRenderer::new);
        }

    }
}
