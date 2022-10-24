package net.dries008.coolmod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.dries008.coolmod.block.entity.PrecisionOperationTableEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

public class PrecisionOperationTableEntityRenderer implements BlockEntityRenderer<PrecisionOperationTableEntity> {
    public PrecisionOperationTableEntityRenderer(BlockEntityRendererProvider.Context context){

    }

    @Override
    public void render(PrecisionOperationTableEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack itemStack = pBlockEntity.getRenderStack();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.65f, 0.5f);
        pPoseStack.scale(0.25f, 0.25f, 0.25f);
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
    }
}
