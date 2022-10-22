package net.dries008.coolmod.block.custom;

import net.dries008.coolmod.block.entity.ModBlockEntities;
import net.dries008.coolmod.block.entity.PrecisionOperationTableEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;


public class PrecisionOperationTable extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public PrecisionOperationTable(Properties properties) {
        super(properties);
    }




    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
    // TO FUCKING LARGE TO EXPLAIN, JUST OPEN IT AND READ!!
        switch (state.getValue(FACING)) {
            case NORTH -> {
                VoxelShape NORTH = Shapes.empty();
                NORTH = Shapes.join(NORTH, Block.box(0, 6, 0, 16, 9, 16), BooleanOp.OR);
                NORTH = Shapes.join(NORTH, Block.box(0, 0, 14, 2, 6, 16), BooleanOp.OR);
                NORTH = Shapes.join(NORTH, Block.box(14, 0, 14, 16, 6, 16), BooleanOp.OR);
                NORTH = Shapes.join(NORTH, Block.box(14, 0, 0, 16, 6, 2), BooleanOp.OR);
                NORTH = Shapes.join(NORTH, Block.box(0, 0, 0, 2, 6, 2), BooleanOp.OR);
                NORTH = Shapes.join(NORTH, Block.box(1, 9, 5, 15, 14.75, 11), BooleanOp.OR);
                return NORTH;
            }
            case SOUTH -> {
                VoxelShape SOUTH = Shapes.empty();
                SOUTH = Shapes.join(SOUTH, Block.box(0, 6, 0, 16, 9, 16), BooleanOp.OR);
                SOUTH = Shapes.join(SOUTH, Block.box(0, 0, 14, 2, 6, 16), BooleanOp.OR);
                SOUTH = Shapes.join(SOUTH, Block.box(14, 0, 14, 16, 6, 16), BooleanOp.OR);
                SOUTH = Shapes.join(SOUTH, Block.box(14, 0, 0, 16, 6, 2), BooleanOp.OR);
                SOUTH = Shapes.join(SOUTH, Block.box(0, 0, 0, 2, 6, 2), BooleanOp.OR);
                SOUTH = Shapes.join(SOUTH, Block.box(1, 9, 5, 15, 14.75, 11), BooleanOp.OR);
                return SOUTH;
            }
            case EAST -> {
                VoxelShape EAST = Shapes.empty();
                EAST = Shapes.join(EAST, Block.box(0, 6, 0, 16, 9, 16), BooleanOp.OR);
                EAST = Shapes.join(EAST, Block.box(0, 0, 14, 2, 6, 16), BooleanOp.OR);
                EAST = Shapes.join(EAST, Block.box(14, 0, 14, 16, 6, 16), BooleanOp.OR);
                EAST = Shapes.join(EAST, Block.box(14, 0, 0, 16, 6, 2), BooleanOp.OR);
                EAST = Shapes.join(EAST, Block.box(0, 0, 0, 2, 6, 2), BooleanOp.OR);
                EAST = Shapes.join(EAST, Block.box(5, 9, 1, 11, 14.75, 15), BooleanOp.OR);
                return EAST;
            }
            case WEST -> {
                VoxelShape WEST = Shapes.empty();
                WEST = Shapes.join(WEST, Block.box(0, 6, 0, 16, 9, 16), BooleanOp.OR);
                WEST = Shapes.join(WEST, Block.box(0, 0, 14, 2, 6, 16), BooleanOp.OR);
                WEST = Shapes.join(WEST, Block.box(14, 0, 14, 16, 6, 16), BooleanOp.OR);
                WEST = Shapes.join(WEST, Block.box(14, 0, 0, 16, 6, 2), BooleanOp.OR);
                WEST = Shapes.join(WEST, Block.box(0, 0, 0, 2, 6, 2), BooleanOp.OR);
                WEST = Shapes.join(WEST, Block.box(5, 9, 1, 11, 14.75, 15), BooleanOp.OR);
                return WEST;
            }
            default -> {
                VoxelShape SHAPE = Shapes.empty();
                SHAPE = Shapes.join(SHAPE, Block.box(0, 6, 0, 16, 9, 16), BooleanOp.OR);
                SHAPE = Shapes.join(SHAPE, Block.box(0, 0, 14, 2, 6, 16), BooleanOp.OR);
                SHAPE = Shapes.join(SHAPE, Block.box(14, 0, 14, 16, 6, 16), BooleanOp.OR);
                SHAPE = Shapes.join(SHAPE, Block.box(14, 0, 0, 16, 6, 2), BooleanOp.OR);
                SHAPE = Shapes.join(SHAPE, Block.box(0, 0, 0, 2, 6, 2), BooleanOp.OR);
                SHAPE = Shapes.join(SHAPE, Block.box(5, 9, 1, 11, 14.75, 15), BooleanOp.OR);
                return SHAPE;
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext){
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation){
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror){
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }


    /* BLOCK ENTITY */


    //rendershape of the block (duhh!)
    @Override
    public RenderShape getRenderShape(BlockState state) {
        //the return is important!! without RenderShape.MODEL the block will be invisable
        return RenderShape.MODEL;
    }


    //basically drops the content when destroyed
    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewstate, boolean pIsMoving) {
        if (pState.getBlock() != pNewstate.getBlock()){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if(blockEntity instanceof PrecisionOperationTableEntity){
                //this right here is the drop method from the PrecisionOperationTableEntity.java class
                ((PrecisionOperationTableEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewstate, pIsMoving);
    }


    //when used it opens the menu of the block
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult result) {
        if(!level.isClientSide){
            BlockEntity entity = level.getBlockEntity(pos);
            //checks if the block exists
            if(entity instanceof PrecisionOperationTableEntity){
                //if so, it opens the screen right here
                NetworkHooks.openScreen(((ServerPlayer) player), (PrecisionOperationTableEntity)entity, pos);
            }else{
                //else, it throws a super cool exception
                throw new IllegalStateException("WTF WHERE IS THE CONTAINER?");
            }
        }
        return super.use(state, level, pos, player, hand, result);
    }


    //creates new block entity
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PrecisionOperationTableEntity(pos, state);
    }


    //does something every gametick? probably
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.PRECISION_OPERATION_TABLE.get(),
                PrecisionOperationTableEntity::tick);
        //executes the function you write in the tick method in PrecisionOperationTableEntity
    }
}
