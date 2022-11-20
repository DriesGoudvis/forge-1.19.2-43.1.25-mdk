package net.dries008.coolmod.networking.packet;

import net.dries008.coolmod.block.entity.PrecisionOperationTableEntity;
import net.dries008.coolmod.screen.PrecisionOperationTableMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class ItemStackSyncS2C {
    private final ItemStackHandler itemStackHandler;
    public final BlockPos pos;

    public ItemStackSyncS2C(ItemStackHandler itemStackHandler, BlockPos pos){
        this.itemStackHandler = itemStackHandler;
        this.pos = pos;
    }

    public ItemStackSyncS2C(FriendlyByteBuf buf){
        List<ItemStack> collection = buf.readCollection(ArrayList::new, FriendlyByteBuf::readItem);
        itemStackHandler = new ItemStackHandler(collection.size());
        for (int i = 0; i < collection.size(); i++){
            itemStackHandler.insertItem(i, collection.get(i), false);
        }
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf){
        Collection<ItemStack> list = new ArrayList<>();
        for(int i = 0; i < itemStackHandler.getSlots(); i++){
            list.add(itemStackHandler.getStackInSlot(i));
        }
        buf.writeCollection(list, FriendlyByteBuf::writeItem);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            //THIS IS CLIENT SIDE!!!!!!
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof PrecisionOperationTableEntity blockEntity){
                blockEntity.setHandler(this.itemStackHandler);
            }
        });
        return true;
    }

}
