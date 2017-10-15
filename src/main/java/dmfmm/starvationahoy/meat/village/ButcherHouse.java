package dmfmm.starvationahoy.meat.village;

import dmfmm.starvationahoy.meat.init.MeatBlockRegistry;
import dmfmm.starvationahoy.meat.block.tileentity.MeatHangerData;
import dmfmm.starvationahoy.meat.block.tileentity.MeatHangerTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

public class ButcherHouse extends StructureVillagePieces.Village {
    
	private int villagersSpawned =0;
    private boolean hasdone = true;
    private int randomNum;
    private int randomNum1;
	private EnumFacing coordBaseMode;
	
	public ButcherHouse(){}
	
	public ButcherHouse(Start villagePiece, int par2, Random par3Random, StructureBoundingBox sbb, EnumFacing facing) {
         super(villagePiece, par2);
		this.setCoordBaseMode(facing);
         this.coordBaseMode = facing;
         this.boundingBox = sbb; 
	}

	 public static ButcherHouse buildComponent(Start villagePiece, List pieces, Random random, int x, int y, int z, EnumFacing facing, int p5) {
		 StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 10, 7, 12, facing);
		 return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new ButcherHouse(villagePiece, p5, random, box, facing) : null;
	 }

	
	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox sbb) {
		if (this.averageGroundLvl < 0)
		{
			this.averageGroundLvl = this.getAverageGroundLevel(world, sbb);

			if (this.averageGroundLvl < 0)
			{
				return true;
			}

			this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6, 0);
		}
       // int x = this.boundingBox.minX;
       // int y = this.boundingBox.minY;
       // int z = this.boundingBox.minZ;
        
        //Clear Everything
		fillWithBlocks(world, sbb, 0, 0, 0, 9, 7, 11, Blocks.AIR, Blocks.AIR, false);
		
		//Place Floor
		fillWithBlocks(world, sbb, 0, 0, 7, 8, 0, 11, Blocks.GRASS, Blocks.GRASS, false);
		fillWithBlocks(world, sbb, 0, 0, 0, 8, 0, 6, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
		
		//Fence & Torches
		fillWithBlocks(world, sbb, 0, 1, 7, 0, 1, 11, Blocks.OAK_FENCE, Blocks.OAK_FENCE, false);
		this.placeBlockAtCurrentPosition(world, Blocks.TORCH, 0, 0, 2, 11, sbb);
		fillWithBlocks(world, sbb, 8, 1, 7, 8, 1, 11, Blocks.OAK_FENCE, Blocks.OAK_FENCE, false);
		this.placeBlockAtCurrentPosition(world, Blocks.TORCH, 0, 8, 2, 11, sbb);
		fillWithBlocks(world, sbb, 1, 1, 11, 7, 1, 11, Blocks.OAK_FENCE, Blocks.OAK_FENCE, false);
			//Middle Torch/Fence
		fillWithBlocks(world,sbb, 4, 1, 7, 4, 1, 11, Blocks.OAK_FENCE, Blocks.OAK_FENCE, false);
		this.placeBlockAtCurrentPosition(world, Blocks.TORCH, 0, 4, 2, 11, sbb);
		
		//Wall @ Pen
		fillWithBlocks(world, sbb, 1, 1, 6, 2, 3, 6, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
		fillWithBlocks(world, sbb, 6, 1, 6, 7, 3, 6, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
		fillWithBlocks(world, sbb, 4, 1, 6, 4, 3, 6, Blocks.COBBLESTONE, Blocks.COBBLESTONE, false);
		this.placeBlockAtCurrentPosition(world, Blocks.COBBLESTONE, 3, 3, 6, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.COBBLESTONE, 5, 3, 6, sbb);
		generateDoor(world, sbb, rand, 5, 1, 6, EnumFacing.SOUTH, this.biomeDoor());
		generateDoor(world, sbb, rand, 3, 1, 6, EnumFacing.SOUTH, this.biomeDoor());

		
		//Wooden Log Corners
		fillWithBlocks(world, sbb, 0, 1, 6, 0, 3, 6, Blocks.LOG, Blocks.LOG, false);
		fillWithBlocks(world, sbb, 8, 1, 6, 8, 3, 6, Blocks.LOG, Blocks.LOG, false);
		fillWithBlocks(world, sbb, 0, 1, 0, 0, 3, 0, Blocks.LOG, Blocks.LOG, false);
		fillWithBlocks(world, sbb, 8, 1, 0, 8, 3, 0, Blocks.LOG, Blocks.LOG, false);
		
		//Solid Walls
		fillWithBlocks(world, sbb, 0, 1, 1, 0, 3, 5, Blocks.BRICK_BLOCK, Blocks.BRICK_BLOCK, false);
		fillWithBlocks(world, sbb, 1, 1, 0, 7, 3, 0, Blocks.BRICK_BLOCK, Blocks.BRICK_BLOCK, false);
		
		//Exit/Entrance Wall
		fillWithBlocks(world, sbb, 8, 1, 1, 8, 3, 2, Blocks.BRICK_BLOCK, Blocks.BRICK_BLOCK, false);
		fillWithBlocks(world, sbb, 8, 1, 4, 8, 3, 5, Blocks.BRICK_BLOCK, Blocks.BRICK_BLOCK, false);
		this.placeBlockAtCurrentPosition(world, Blocks.BRICK_BLOCK, 8, 3, 3, sbb);
		//int entryStair = getMetadataWithOffset(blocks.STONE_STAIRS, 1);
		//this.placeBlockAtCurrentPosition(world, blocks.STONE_STAIRS, 0, 9, 0, 3, sbb);
		this.setBlockState(world, Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST), 9, 0, 3, sbb);

		generateDoor(world, sbb, rand, 8, 1, 3, EnumFacing.EAST, this.biomeDoor());
		
		//ROOF
		fillWithBlocks(world, sbb, 0, 6, 3, 8, 6, 3, Blocks.PLANKS, Blocks.PLANKS, false);
		for (int i = 0; i <= 8; i++){
			//To Pen
			this.setBlockState(world, Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH), i, 6, 4, sbb);
			this.setBlockState(world, Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH), i, 5, 5, sbb);
			this.setBlockState(world, Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH), i, 4, 6, sbb);
			//Out Side
			this.setBlockState(world, Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH), i, 6, 2, sbb);
			this.setBlockState(world, Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH), i, 5, 1, sbb);
			this.setBlockState(world, Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH), i, 4, 0, sbb);
			
		}
		fillWithBlocks(world, sbb, 0, 4, 1, 0, 4, 5, Blocks.PLANKS, Blocks.PLANKS, false);
		fillWithBlocks(world, sbb, 8, 4, 1, 8, 4, 5, Blocks.PLANKS, Blocks.PLANKS, false);
		this.placeBlockAtCurrentPosition(world, Blocks.PLANKS,  0, 5, 2, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.GLASS_PANE, 0, 5, 3, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.PLANKS,  0, 5, 4, sbb);
		
		this.placeBlockAtCurrentPosition(world, Blocks.PLANKS,  8, 5, 2, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.GLASS_PANE, 8, 5, 3, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.PLANKS,  8, 5, 4, sbb);
		

		
		//Animals
		if(hasdone){
		Random randss = new Random();
		randomNum = randss.nextInt((3 - 1) + 1) + 1;
		randomNum1 = randss.nextInt((3 - 1) + 1) + 1;
		
		 int j1 = this.getXWithOffset(2 + 1, 9);
         int k1 = this.getYWithOffset(1);
         int l1 = this.getZWithOffset(2 + 1, 9);
         int j2 = this.getXWithOffset(6 + 1, 9);
         int l2 = this.getZWithOffset(6 + 1, 9);
		
		if(randomNum == 1){
			Entity cow = new EntityCow(world);
			cow.setPosition(j1, k1, l1);
			world.spawnEntity(cow);
			Entity cow4 = new EntityCow(world);
			cow4.setPosition(j1, k1, l1);
			world.spawnEntity(cow4);
		} else if(randomNum == 2){
			Entity pig = new EntityPig(world);
			pig.setPosition(j1, k1, l1);
			world.spawnEntity(pig);
			Entity pig5 = new EntityPig(world);
			pig5.setPosition(j1, k1, l1);
			world.spawnEntity(pig5);
		} else if(randomNum == 3){
			Entity chicken = new EntityChicken(world);
			chicken.setPosition(j1, k1, l1);
			world.spawnEntity(chicken);
			Entity chicken6 = new EntityChicken(world);
			chicken6.setPosition(j1, k1, l1);
			world.spawnEntity(chicken6);
		}
		if(randomNum1 == 1){
			Entity cow = new EntityCow(world);
			cow.setPosition(j2, k1, l2);
			world.spawnEntity(cow);
			Entity cow3 = new EntityCow(world);
			cow3.setPosition(j2, k1, l2);
			world.spawnEntity(cow3);
		} else if(randomNum1 == 2){
			Entity pig = new EntityPig(world);
			pig.setPosition(j2, k1, l2);
			world.spawnEntity(pig);
			Entity pig2 = new EntityPig(world);
			pig2.setPosition(j2, k1, l2);
			world.spawnEntity(pig2);
		} else if(randomNum1 == 3){
			Entity chicken = new EntityChicken(world);
			chicken.setPosition(j2, k1, l2);
			world.spawnEntity(chicken);
			Entity chicken1 = new EntityChicken(world);
			chicken1.setPosition(j2, k1, l2);
			world.spawnEntity(chicken1);
		}
		hasdone = false;
		}
		
		//Interior Decor
		fillWithMetadataBlocks(world, sbb, 1, 0, 3, 2, 0, 5, Blocks.DOUBLE_STONE_SLAB, 7, Blocks.DOUBLE_STONE_SLAB, 7, false);
		fillWithMetadataBlocks(world, sbb, 2, 1, 4, 2, 1, 5, Blocks.DOUBLE_STONE_SLAB, 7, Blocks.DOUBLE_STONE_SLAB, 7, false);
		this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH), 1, 4, 5, sbb);
		this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH), 4, 3, 5, sbb);//Middle to outside
		this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH), 1, 4, 1, sbb);
		this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST), 7, 4, 1, sbb);//AT DOOR
		this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST), 7, 4, 5, sbb);//AT DOOR
		placeHanger(world, sbb, getXOff(2, 1), getYOff(3), getZOff(2, 1), randomNum);
		placeHanger(world, sbb, getXOff(4, 1), getYOff(3), getZOff(4, 1), randomNum1);
		placeHanger(world, sbb, getXOff(6, 1), getYOff(3), getZOff(6, 1), randomNum);
		fillWithBlocks(world, sbb, 2, 3, 4, 2, 3, 5, Blocks.GLASS_PANE, Blocks.GLASS_PANE, false);
		this.placeBlockAtCurrentPosition(world, Blocks.GLASS_PANE,  1, 3, 4, sbb);
		this.setBlockState(world, Blocks.OAK_FENCE_GATE.getDefaultState().withProperty(BlockFenceGate.FACING, EnumFacing.NORTH).withProperty(BlockFenceGate.OPEN, Boolean.FALSE), 1, 1, 4, sbb);
		//this.placeBlockAtCurrentPosition(world, blocks.OAK_FENCE_gate, this.coordBaseMode.rotateY().getIndex(), 1, 1, 4, sbb);
		this.placeBlockAtCurrentPosition(world, Blocks.OAK_FENCE,  2, 2, 4, sbb);



		if (this.villagersSpawned < 1) {
			int j = this.getXWithOffset(1, 5);
			int k = this.getYWithOffset(1);
			int l = this.getZWithOffset(1, 5);

			++this.villagersSpawned;
			EntityVillager entityvillager = new EntityVillager(world);
			entityvillager.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, 0.0F, 0.0F);
			entityvillager.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData) null);
			entityvillager.setProfession(VillagerRegistry.instance().getRegistry().getValue(new ResourceLocation("starvationahoy:SAButcher")));
			world.spawnEntity(entityvillager);
		}
		//spawnVillagers(world, sbb, 1, 1, 5, 1);
		

		return true;
	}
	
	@Override
	protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession)
    {
        return 4;
		//return VillagerTradeAdditions.getVID();
    }
	
	
	private void placeHanger(World world, StructureBoundingBox sbb, int x, int y, int z, int rand){ 

		//this.placeBlockAtCurrentPosition(world, MeatBlockRegistry.MeatHanger, 4, 2, 3, 1, sbb);
		world.setBlockState(new BlockPos(x, y, z), MeatBlockRegistry.MeatHanger.getStateFromMeta(this.coordBaseMode.getIndex()), 2);
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		if(te instanceof MeatHangerTileEntity){
			MeatHangerTileEntity MHA = (MeatHangerTileEntity) te;
			MHA.updateHanger(rand, MeatHangerData.MeatStates.NORMAL);
			world.notifyBlockUpdate(new BlockPos(x, y, z), world.getBlockState(new BlockPos(x, y, z)).getBlock().getDefaultState(),
					world.getBlockState(new BlockPos(x, y, z)).getBlock().getDefaultState(), 3);
		}

		
		//if(MHA != null){MHA.setMeatType(randomNum);}
		//MeatHangerTileEntity MHB = (MeatHangerTileEntity) world.getTileEntity(getXOff(4, 1), getYOff(3), getZOff(4, 1));
		//if(MHB != null){MHB.setMeatType(1);}
		//MeatHangerTileEntity MHC = (MeatHangerTileEntity) world.getTileEntity(getXOff(6, 1), getYOff(3), getZOff(6, 1));
		//if(MHC != null){MHC.setMeatType(1);}
	}
	private int getYOff(int y){
		return this.getYWithOffset(y);
	}
	private int getXOff(int x, int z){
		return this.getXWithOffset(x, z);
	}
	private int getZOff(int x, int z){
		return this.getZWithOffset(x, z);
	}

	private void fillWithBlocks(World world, StructureBoundingBox sbb, int i, int j, int k, int l, int m, int n, Block blockb, Block blocka, boolean bool){
		this.fillWithBlocks(world, sbb, i, j, k, l, m, n, blockb.getDefaultState(), blocka.getDefaultState(), bool);
	}
	private void placeBlockAtCurrentPosition(World world,Block block, int x, int y, int z, StructureBoundingBox sbb){
		this.setBlockState(world, block.getDefaultState(), x, y, z, sbb);
	}
	private void placeBlockAtCurrentPosition(World world,Block block, int meta, int x, int y, int z, StructureBoundingBox sbb){
		this.setBlockState(world, block.getStateFromMeta(meta), x, y, z, sbb);
	}

	protected void fillWithMetadataBlocks(World world, StructureBoundingBox sbb, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, Block toPlace, int toPlaceMeta, Block replace, int replaceMeta, boolean alwaysReplace)
	{
		for (int i2 = minY; i2 <= maxY; ++i2)
		{
			for (int j2 = minX; j2 <= maxX; ++j2)
			{
				for (int k2 = minZ; k2 <= maxZ; ++k2)
				{
					if (!alwaysReplace || this.getBlockStateFromPos(world, j2, i2, k2, sbb).getBlock().getMaterial(this.getBlockStateFromPos(world, j2, i2, k2, sbb)) != Material.AIR)
					{
						if (i2 != minY && i2 != maxY && j2 != minX && j2 != maxX && k2 != minZ && k2 != maxZ)
						{
							this.placeBlockAtCurrentPosition(world, replace, replaceMeta, j2, i2, k2, sbb);
						}
						else
						{
							this.placeBlockAtCurrentPosition(world, toPlace, toPlaceMeta, j2, i2, k2, sbb);
						}
					}
				}
			}
		}
	}

}
