package net.dries008.coolmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.entity.ModEntityTypes;
import net.dries008.coolmod.entity.custom.SkullerEntity;
import net.dries008.coolmod.item.ModItems;
import net.dries008.coolmod.networking.ModMessage;
import net.dries008.coolmod.networking.packet.ThirstDataSyncS2C;
import net.dries008.coolmod.thirst.PlayerThirst;
import net.dries008.coolmod.thirst.PlayerThirstProvider;
import net.dries008.coolmod.villager.ModVillagers;
import net.minecraft.client.gui.spectator.SpectatorMenuListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.List;


public class ModEvents {
    @Mod.EventBusSubscriber(modid = CoolMod.MOD_ID)
    public static class ForgeEvents{
        @SubscribeEvent
        public static void addCustomTrades(VillagerTradesEvent event){
            if(event.getType() == ModVillagers.TRASHMASTER.get()){
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.RADIOHEALCROPSEEDS.get(), 2);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 10),
                        stack, 2, 8, 0.02f));
            }
        }


        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
            if(event.getObject() instanceof Player){
                if(!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()){
                    event.addCapability(new ResourceLocation(CoolMod.MOD_ID, "properties"), new PlayerThirstProvider());
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event){
            if(event.isWasDeath()){
                event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
            event.register(PlayerThirst.class);
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event){
            if(event.side == LogicalSide.SERVER){
                event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {

                    if(thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.0005f){
                        thirst.subThirst(1);
                        ModMessage.sendToPlayer(new ThirstDataSyncS2C(thirst.getThirst()), ((ServerPlayer) event.player));
                    }
                });
            }
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event){
            if(!event.getLevel().isClientSide){
                if(event.getEntity() instanceof ServerPlayer player){
                    player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                        ModMessage.sendToPlayer(new ThirstDataSyncS2C(thirst.getThirst()), player);
                    });
                }
            }
        }
    }

    @Mod.EventBusSubscriber(modid = CoolMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{

        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event){
            event.put(ModEntityTypes.SKULLER.get(), SkullerEntity.setAttributes());
        }
    }
}
