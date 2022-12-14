package net.dries008.coolmod.networking;

import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.networking.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessage {
    private static SimpleChannel INSTANCE;

    private static int packetId =0;
    private static int id(){
        return packetId++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(CoolMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ExampleC2SPacket::new)
                .encoder(ExampleC2SPacket::toBytes)
                .consumerMainThread(ExampleC2SPacket::handle)
                .add();

        net.messageBuilder(DrinkWaterC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DrinkWaterC2S::new)
                .encoder(DrinkWaterC2S::toBytes)
                .consumerMainThread(DrinkWaterC2S::handle)
                .add();

        net.messageBuilder(ThirstDataSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ThirstDataSyncS2C::new)
                .encoder(ThirstDataSyncS2C::toBytes)
                .consumerMainThread(ThirstDataSyncS2C::handle)
                .add();

        net.messageBuilder(EnergyDataSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnergyDataSyncS2C::new)
                .encoder(EnergyDataSyncS2C::toBytes)
                .consumerMainThread(EnergyDataSyncS2C::handle)
                .add();

        net.messageBuilder(FluidDataSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FluidDataSyncS2C::new)
                .encoder(FluidDataSyncS2C::toBytes)
                .consumerMainThread(FluidDataSyncS2C::handle)
                .add();

        net.messageBuilder(ItemStackSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ItemStackSyncS2C::new)
                .encoder(ItemStackSyncS2C::toBytes)
                .consumerMainThread(ItemStackSyncS2C::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sentToClients(MSG msg){
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }

}
