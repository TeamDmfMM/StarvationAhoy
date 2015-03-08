package dmfmm.StarvationAhoy.Meat.Block;

import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.CookerTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Matthew on 2/28/2015.
 */
public class Cooker extends BlockContainer {
    protected Cooker() {
        super(Material.anvil);
        this.setCreativeTab(SATabs.INSTANCE);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new CookerTileEntity(null);
    }
}
