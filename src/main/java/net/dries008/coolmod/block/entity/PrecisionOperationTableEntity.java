package net.dries008.coolmod.block.entity;

import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.block.ModBlocks;
import net.dries008.coolmod.block.custom.PrecisionOperationTable;
import net.dries008.coolmod.item.ModItems;
import net.dries008.coolmod.networking.ModMessage;
import net.dries008.coolmod.networking.packet.EnergyDataSyncS2C;
import net.dries008.coolmod.recipe.PrecisionOperationTableRecipe;
import net.dries008.coolmod.screen.PrecisionOperationTableMenu;
import net.dries008.coolmod.util.ModEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;
import java.util.Map;
import java.util.Optional;

public class PrecisionOperationTableEntity extends BlockEntity implements MenuProvider {
    //creates the inventory slots where items can be in
    private final ItemStackHandler itemHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case 0 -> stack.getItem() == ModItems.RADIOHEALCROPEAT  .get();
                case 1 -> stack.getItem() == ModItems.RADIOTRASH.get();
                case 2 -> false;
                default -> super.isItemValid(slot, stack);

            };
        }
    };

    private final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(60000, 256) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            ModMessage.sentToClients(new EnergyDataSyncS2C(this.energy, getBlockPos()));
        }
    };
    private static final int ENERGY_REQ = 32;

    public ItemStack getRenderStack() {
        ItemStack stack;
        if(!itemHandler.getStackInSlot(2).isEmpty()){
            stack = itemHandler.getStackInSlot(2);
        }else{
            stack = itemHandler.getStackInSlot(1);
        }
        return stack;
    }

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2, (i, s) -> false)),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 1,
                            (index, stack) -> itemHandler.isItemValid(0, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2, (i, s) -> false)),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 1,
                            (index, stack) -> itemHandler.isItemValid(0, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0 || index == 1,
                            (index, stack) -> itemHandler.isItemValid(0, stack) || itemHandler.isItemValid(1, stack))));

    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public PrecisionOperationTableEntity( BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRECISION_OPERATION_TABLE.get(), pos, state);
        //for saving date between server and client (I think)
        this.data = new ContainerData() {
            //getting the two variables
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> PrecisionOperationTableEntity.this.progress;
                    case 1 -> PrecisionOperationTableEntity.this.maxProgress;
                    default -> 0;
                };
            }
            //setting the two variables
            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> PrecisionOperationTableEntity.this.progress = value;
                    case 1 -> PrecisionOperationTableEntity.this.maxProgress = value;
                };
            }
            //How many variables that need to get syncronized
            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    //is the name of the block?
    @Override
    public Component getDisplayName() {
        return Component.literal("Precision operation table");
    }


    //probably creates a menu to display the inventory
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new PrecisionOperationTableMenu(id, inventory, this, this.data);
    }

    public IEnergyStorage getEnergyStorage() {
        return ENERGY_STORAGE;
    }

    public void setEnergyLevel(int energy) {
        this.ENERGY_STORAGE.setEnergy(energy);
    }

    //??
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ENERGY){
            return lazyEnergyHandler.cast();
        }


        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == null) {
                return lazyItemHandler.cast();
            }

            if(directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(PrecisionOperationTable.FACING);

                if(side == Direction.UP || side == Direction.DOWN) {
                    return directionWrappedHandlerMap.get(side).cast();
                }

                return switch (localDir) {
                    default -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
                    case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionWrappedHandlerMap.get(side).cast();
                    case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }

        return super.getCapability(cap, side);
    }

    //does something when the block gets loaded
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(()->itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    //uhmmmmmm, makes the block invalid?
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    //saves the inventory
    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("precision_operation_table.progress", this.progress);
        nbt.putInt("precision_operation_table.energy", ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(nbt);
    }

    //loads the inventory
    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("precision_operation_table.progress");
        ENERGY_STORAGE.setEnergy(nbt.getInt("precision_operation_table.energy"));
    }


    //drops content in world when called / probably gets called when block gets destroyed
    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i =0;i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    //does something every tick
    public static void tick(Level level, BlockPos blockPos, BlockState blockState, PrecisionOperationTableEntity pEntity) {
        if(level.isClientSide()){
            return;
        }

        if(hasTrashInFirstSlot(pEntity)){
            pEntity.ENERGY_STORAGE.receiveEnergy(32, false);
        }

        if(hasRecipe(pEntity) && hasEnoughEnergy(pEntity)){
            pEntity.progress++;
            extractEnergy(pEntity);
            setChanged(level, blockPos, blockState);

            if(pEntity.progress >= pEntity.maxProgress){
                craftItem(pEntity);
            }
        }else{
            pEntity.resetProgress();
            setChanged(level, blockPos, blockState);
        }


    }

    private static void extractEnergy(PrecisionOperationTableEntity pEntity) {
        pEntity.ENERGY_STORAGE.extractEnergy(ENERGY_REQ, false);
    }

    private static boolean hasEnoughEnergy(PrecisionOperationTableEntity pEntity) {
        return pEntity.ENERGY_STORAGE.getEnergyStored() >= ENERGY_REQ * pEntity.maxProgress;
    }

    private static boolean hasTrashInFirstSlot(PrecisionOperationTableEntity pEntity) {
        return pEntity.itemHandler.getStackInSlot(0).getItem() == ModItems.RADIOHEALCROPEAT.get();
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(PrecisionOperationTableEntity pEntity) {
        Level level = pEntity.level;
        SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
        for(int i = 0; i < pEntity.itemHandler.getSlots(); i++){
            inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
        }

        Optional<PrecisionOperationTableRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(PrecisionOperationTableRecipe.Type.INSTANCE, inventory, level);


        if(hasRecipe(pEntity)){
            //extracts out item slot 1, 1 item, and ite actually happens (it doesn't simulate it)
            pEntity.itemHandler.extractItem(1,1,false);

                pEntity.itemHandler.setStackInSlot(2, new ItemStack(recipe.get().getResultItem().getItem(),
                        pEntity.itemHandler.getStackInSlot(2).getCount() + 1));
                pEntity.resetProgress();
        }
    }

    private static boolean hasRecipe(PrecisionOperationTableEntity pEntity) {
        Level level = pEntity.level;
        SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
        for(int i = 0; i < pEntity.itemHandler.getSlots(); i++){
            inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
        }

        Optional<PrecisionOperationTableRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(PrecisionOperationTableRecipe.Type.INSTANCE, inventory, level);

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemIntoOutputSlot(inventory, recipe.get().getResultItem());


    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(2).getItem() == itemStack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

}
