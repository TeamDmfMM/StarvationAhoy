package dmfmm.StarvationAhoy.Meat.Village;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class BHHandler implements IVillageCreationHandler{

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) {
		return new PieceWeight(ButcherHouse.class, 4, 1);
	}

	@Override
	public Class<?> getComponentClass() {
		return ButcherHouse.class;
	}


	@Override
	public StructureVillagePieces.Village buildComponent(PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces, Random random, int x, int y, int z, EnumFacing facing, int p5) {
		return ButcherHouse.buildComponent(startPiece, pieces, random, x, y, z, facing, p5);
	}

}
