package net.dries008.coolmod.networking.packet;

import net.dries008.coolmod.block.entity.PrecisionOperationTableEntity;
import net.dries008.coolmod.client.ClientThirstData;
import net.dries008.coolmod.screen.PrecisionOperationTableMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnergyDataSyncS2C {
    private final int energy;
    public final BlockPos pos;

    public EnergyDataSyncS2C(int energy, BlockPos pos){
        this.energy = energy;
        this.pos = pos;
    }

    public EnergyDataSyncS2C(FriendlyByteBuf buf){
        this.energy = buf.readInt();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(energy);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            //THIS IS CLIENT SIDE!!!!!!
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof PrecisionOperationTableEntity blockEntity){
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof PrecisionOperationTableMenu menu &&
                menu.getBlockEntity().getBlockPos().equals(pos)){
                    blockEntity.setEnergyLevel(energy);
                }
            }
        });
        return true;
    }

}
