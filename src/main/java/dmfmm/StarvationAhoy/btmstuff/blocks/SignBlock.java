package dmfmm.StarvationAhoy.btmstuff.blocks;

import dmfmm.StarvationAhoy.Core.util.BPHelp;
import dmfmm.StarvationAhoy.btmstuff.te.SignBlockTE;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by TeamDMFMM on 7/22/2016. Code originally written
 * for Starvatione Ahoy. Do not copy without permission,
 * because that is just mean. Code is VISIBLE SOURCE, therfore
 * credit us, just don't steal large portions of this.
 */
public class SignBlock extends Block implements ITileEntityProvider{
    public SignBlock() {
        super(Material.ANVIL);
        this.setUnlocalizedName("signblock");

    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }


    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     * @param worldIn
     * @param meta
     */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new SignBlockTE(null, null);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        ArrayList<SignBlockTE> nextTos = new ArrayList<>();
        ArrayList<SignBlockTE> origins = new ArrayList<>();

        for (EnumFacing neighborDirection : EnumFacing.VALUES) {
            TileEntity nextTo = worldIn.getTileEntity(pos.offset(neighborDirection));
            if (nextTo == null || !(nextTo instanceof SignBlockTE)){
                continue;
            }
            else {
                nextTos.add((SignBlockTE) nextTo);
            }
        }

        for (SignBlockTE te : nextTos) {
            if (te.direction == null) {
                origins.add(te);
                continue;
            }
            else {
                if (te.offset_x == 0 && te.offset_y == 0){
                    origins.add(te);
                    continue;
                }
            }

            EnumFacing axis_x = te.direction.getAxisX();
            EnumFacing axis_y = te.direction.getAxisY();

            BlockPos newpos = te.getPos().offset(axis_x.getOpposite(), te.offset_x).offset(axis_y.getOpposite(), te.offset_y);
            SignBlockTE origin = (SignBlockTE) te.getWorld().getTileEntity(newpos);
            if (!origins.contains(origin)) {
                origins.add(origin);
            }
        }

        SignBlockTE me = (SignBlockTE) worldIn.getTileEntity(pos);

        ArrayList<SignBlockTE> possibleOKS = new ArrayList<>();

        if (origins.size() == 0) {
            me.offset_x = 0;
            me.offset_y = 0;

            me.width = 1;
            me.height = 1;

            me.front = BPHelp.getFFE(worldIn, pos, placer, false);
            me.state = SignBlockTE.State.SINGULAR;
        }
        else {
            for (SignBlockTE origin : origins) {
                if (origin.direction == null) {
                    possibleOKS.add(origin);
                    continue;
                }

                EnumFacing axis_x = origin.direction.getAxisX();
                EnumFacing axis_y = origin.direction.getAxisY();

                BlockPos distance = pos.subtract(origin.getPos());

                int offset_x = offsetDistanceAlongAxisFromBlockPos(distance, axis_x);
                int offset_y = offsetDistanceAlongAxisFromBlockPos(distance, axis_y);

                int possible_width = Math.max(Math.abs(offset_x) + 1, origin.width);
                int possible_height = Math.max(Math.abs(offset_y) + 1, origin.height);

                BlockPos startPosition = origin.getPos();
                if (offset_x < 0) {
                    startPosition = startPosition.offset(axis_x, offset_x);
                }
                if (offset_y < 0) {
                    startPosition = startPosition.offset(axis_y, offset_y);
                }
                BlockPos endPosition = startPosition;
                endPosition = endPosition.offset(axis_x, possible_width-1);
                endPosition = endPosition.offset(axis_y, possible_height-1);

                boolean flag = true;
                for (BlockPos test : BlockPos.getAllInBox(startPosition, endPosition)) {
                    if (test == pos) {
                        continue;
                    }
                    else if (worldIn.getTileEntity(test) == null || !(worldIn.getTileEntity(test) instanceof SignBlockTE)) {
                        flag = false;
                    }
                }

                if (flag) {
                    possibleOKS.add(origin);
                }
            }
            SignBlockTE biggest = null;
            int largest_area = -1;
            for (SignBlockTE origin : possibleOKS) {
                if (origin.direction == null) {
                    if (largest_area <= 2) {
                        largest_area = 2;
                        biggest = origin;
                    }
                    continue;
                }
                EnumFacing axis_x = origin.direction.getAxisX();
                EnumFacing axis_y = origin.direction.getAxisY();

                BlockPos distance = pos.subtract(origin.getPos());

                int offset_x = offsetDistanceAlongAxisFromBlockPos(distance, axis_x);
                int offset_y = offsetDistanceAlongAxisFromBlockPos(distance, axis_y);

                int possible_width = Math.max(Math.abs(offset_x) + 1, origin.width);
                int possible_height = Math.max(Math.abs(offset_y) + 1, origin.height);

                int area = possible_height * possible_width;

                if (largest_area <= area) {
                    largest_area = area;
                    biggest = origin;
                }
            }

            SignBlockTE origin = biggest;

            if (origin == null) {
                me.offset_x = 0;
                me.offset_y = 0;

                me.width = 1;
                me.height = 1;

                me.front = BPHelp.getFFE(worldIn, pos, placer, false);
                me.state = SignBlockTE.State.SINGULAR;
                return;
            }

            if (origin.direction == null) {
                Vec3i distance = pos.subtract(origin.getPos());

                for (EnumFacing possibleDirectionX : EnumFacing.VALUES) {
                    Vec3i test = possibleDirectionX.getDirectionVec();
                    if (test.getX() == distance.getX() && test.getY() == distance.getY() && test.getZ() == distance.getZ()) {
                        origin.direction = new SignBlockTE.DirectionData(possibleDirectionX);
                    }
                }

                origin.state = SignBlockTE.State.COMPLETE;
                origin.width = 2;

                me.offset_x = 1;
                me.offset_y = 0;

                me.width = 2;
                me.height = 1;

                me.front = BPHelp.getFFE(worldIn, pos, placer, false);
                me.state = SignBlockTE.State.COMPLETE;
                me.direction = origin.direction;
            }
            else {
                EnumFacing axis_x = origin.direction.getAxisX();
                EnumFacing axis_y = origin.direction.getAxisY();

                BlockPos distance = pos.subtract(origin.getPos());

                EnumFacing nextToDirection = null;

                for (EnumFacing possibleDirectionX : EnumFacing.VALUES) {
                    Vec3i test = possibleDirectionX.getDirectionVec();
                    if (test.getX() == distance.getX() && test.getY() == distance.getY() && test.getZ() == distance.getZ()) {
                        nextToDirection = possibleDirectionX;
                    }
                }

                int offset_x = offsetDistanceAlongAxisFromBlockPos(distance, axis_x);
                int offset_y = offsetDistanceAlongAxisFromBlockPos(distance, axis_y);

                int possible_width = Math.max(Math.abs(offset_x) + 1, origin.width);
                int possible_height = Math.max(Math.abs(offset_y) + 1, origin.height);

                BlockPos startPosition = origin.getPos();
                if (offset_x < 0) {
                    startPosition = startPosition.offset(axis_x, offset_x);
                }
                if (offset_y < 0) {
                    startPosition = startPosition.offset(axis_y, offset_y);
                }

                SignBlockTE newOrigin = (SignBlockTE) worldIn.getTileEntity(startPosition);

                newOrigin.offset_x = 0;
                newOrigin.offset_y = 0;

                newOrigin.width = possible_width;
                newOrigin.height = possible_height;

                if (!origin.direction.isComplete() && nextToDirection != null) {
                    newOrigin.direction = origin.direction;
                    newOrigin.direction.finish(nextToDirection);
                }
                else {
                    newOrigin.direction = origin.direction;
                }

                newOrigin.state = SignBlockTE.State.COMPLETE;

                BlockPos endPosition = startPosition;
                endPosition = endPosition.offset(axis_x, possible_width-1);
                endPosition = endPosition.offset(axis_y, possible_height-1);

                for (BlockPos calculate : BlockPos.getAllInBox(startPosition, endPosition)) {
                    BlockPos new_distance = calculate.subtract(newOrigin.getPos());
                    int new_offset_x = offsetDistanceAlongAxisFromBlockPos(new_distance, axis_x);
                    int new_offset_y = offsetDistanceAlongAxisFromBlockPos(new_distance, axis_y);

                    SignBlockTE calculating = (SignBlockTE) worldIn.getTileEntity(calculate);

                    calculating.direction = newOrigin.direction;
                    calculating.offset_x = new_offset_x;
                    calculating.offset_y = new_offset_y;
                    calculating.state = SignBlockTE.State.COMPLETE;
                    calculating.front = newOrigin.front;

                }

            }

        }
    }

    public int offsetDistanceAlongAxisFromBlockPos(BlockPos distance, EnumFacing axis_x) {
        BlockPos pos = new BlockPos(distance.getX() * axis_x.getDirectionVec().getX(), distance.getY() * axis_x.getDirectionVec().getY(), distance.getZ() * axis_x.getDirectionVec().getZ());
        if (!(pos.getX() == 0)) {
            return pos.getX();
        }
        if (!(pos.getY() == 0)) {
            return pos.getY();
        }
        if (!(pos.getZ() == 0)) {
            return pos.getZ();
        }
        return 0;
    }

    public Vec3i extendAlongAxis(Vec3i axis, BlockPos distance) {
        int compX = axis.getX() * distance.getX();
        int compY = axis.getY() * distance.getY();
        int compZ = axis.getZ() * distance.getZ();
        return new Vec3i(compX, compY, compZ);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        SignBlockTE te = (SignBlockTE) worldIn.getTileEntity(pos);
        if (te == null || te.direction == null || te.front == null) {
            super.breakBlock(worldIn, pos, state);
            return;
        }

        EnumFacing axis_x = te.direction.getAxisX();
        EnumFacing axis_y = te.direction.getAxisY();

        BlockPos newpos = te.getPos().offset(axis_x.getOpposite(), te.offset_x).offset(axis_y.getOpposite(), te.offset_y);
        SignBlockTE origin = (SignBlockTE) te.getWorld().getTileEntity(newpos);

        assert origin != null;
        BlockPos originPos = origin.getPos();
        BlockPos endPos = originPos.offset(axis_x, origin.width-1).offset(axis_y, origin.height-1);

        for (BlockPos breakMe : BlockPos.getAllInBox(originPos, endPos)) {
            if (breakMe.equals(pos)) {
                continue;
            }
            worldIn.setBlockToAir(breakMe);
        }

        super.breakBlock(worldIn, pos, state);
    }
}
