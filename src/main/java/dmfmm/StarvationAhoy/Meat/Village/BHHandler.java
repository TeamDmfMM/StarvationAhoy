package dmfmm.StarvationAhoy.Meat.Village;

import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

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
	public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, int x, int y, int z, int p4, int p5) {
		return ButcherHouse.buildComponent(startPiece, pieces, random, x, y, z, p4, p5);
	}

}
