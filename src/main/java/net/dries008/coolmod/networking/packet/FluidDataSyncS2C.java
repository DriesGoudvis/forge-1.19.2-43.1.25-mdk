package net.dries008.coolmod.networking.packet;

import net.dries008.coolmod.block.entity.PrecisionOperationTableEntity;
import net.dries008.coolmod.screen.PrecisionOperationTableMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FluidDataSyncS2C {
    private final FluidStack fluidStack;
    public final BlockPos pos;

    public FluidDataSyncS2C(FluidStack fluidStack, BlockPos pos){
        this.fluidStack = fluidStack;
        this.pos = pos;
    }

    public FluidDataSyncS2C(FriendlyByteBuf buf){
        this.fluidStack = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeFluidStack(fluidStack);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            //THIS IS CLIENT SIDE!!!!!!
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof PrecisionOperationTableEntity blockEntity){
                blockEntity.setFluid(fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof PrecisionOperationTableMenu menu &&
                menu.getBlockEntity().getBlockPos().equals(pos)){
                    menu.setFluid(this.fluidStack);
                }
            }
        });
        return true;
    }

}
