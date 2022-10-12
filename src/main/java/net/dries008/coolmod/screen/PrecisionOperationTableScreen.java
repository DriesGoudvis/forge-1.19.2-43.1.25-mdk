package net.dries008.coolmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.dries008.coolmod.CoolMod;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PrecisionOperationTableScreen extends AbstractContainerScreen<PrecisionOperationTableMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(CoolMod.MOD_ID, "textures/gui/precision_operation_table_gui.png");


    public PrecisionOperationTableScreen(PrecisionOperationTableMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack stack, float pPartialTick, int pMousX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f,1.0f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(stack, x, y, 0,0,imageWidth,  imageHeight);
        renderProgressArrow(stack, x, y);
    }

    //draws the progress bar
    private void renderProgressArrow(PoseStack pPoseStack, int x, int y){
        if(menu.isCrafting()){
            //desciped like this: (IDK, where to draw x, where to draw y, what to draw x, what to draw y, how much to draw x, and how much to draw y
            blit(pPoseStack, x+105,y+33,176,0,8, menu.getScaledProgress());
        }
    }


    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float delta) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, delta);
        renderTooltip(stack, mouseX, mouseY);
    }
}
