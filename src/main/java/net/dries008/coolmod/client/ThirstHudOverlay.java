package net.dries008.coolmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dries008.coolmod.CoolMod;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ThirstHudOverlay {
    private static ResourceLocation FILLED_THIRST = new ResourceLocation(CoolMod.MOD_ID,
            "textures/thirst/filled_thirst.png");
    private static ResourceLocation EMPTY_THIRST = new ResourceLocation(CoolMod.MOD_ID,
            "textures/thirst/empty_thirst.png");

    public static final IGuiOverlay HUD_THIRST = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
       int x = screenWidth/2;
       int y = screenHeight;

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, EMPTY_THIRST);

        for(int i = 0; i<10; i++){
            GuiComponent.blit(poseStack, x - 94 + (i*8), y -54, 0, 0, 12,12,12,
                    12);
        }

        RenderSystem.setShaderTexture(0, FILLED_THIRST);
        for(int i =0; i<10; i++){
            if(ClientThirstData.getPlayerThirst() > i){
                GuiComponent.blit(poseStack, x -94 +(i*8), y - 54, 0, 0, 12, 12,
                        12,12);
            }else{
                break;
            }
        }


    });
}
