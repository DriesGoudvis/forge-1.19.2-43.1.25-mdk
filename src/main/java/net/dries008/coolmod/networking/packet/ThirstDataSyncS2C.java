package net.dries008.coolmod.networking.packet;

import net.dries008.coolmod.client.ClientThirstData;
import net.dries008.coolmod.thirst.PlayerThirstProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ThirstDataSyncS2C {
    private final int thirst;

    public ThirstDataSyncS2C(int thirst){
        this.thirst = thirst;
    }

    public ThirstDataSyncS2C(FriendlyByteBuf buf){
        this.thirst = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(thirst);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            //THIS IS CLIENT SIDE!!!!!!
            ClientThirstData.set(thirst);
        });
        return true;
    }

}
