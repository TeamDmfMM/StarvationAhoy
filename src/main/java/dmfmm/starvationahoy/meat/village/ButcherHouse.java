package dmfmm.starvationahoy.meat.village;

import dmfmm.starvationahoy.core.blocks.BlockContainerRotate;
import dmfmm.starvationahoy.meat.ModuleMeat;
import dmfmm.starvationahoy.meat.block.tileentity.MeatHangerData;
import dmfmm.starvationahoy.meat.block.tileentity.MeatHangerTileEntity;
import dmfmm.starvationahoy.meat.init.MeatBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ButcherHouse extends StructureVillagePieces.Village {

    private boolean hasdone = true;
    private int randomNum;
    private int randomNum1;
	private EnumFacing coordBaseMode;
	private Random random;
	
	public ButcherHouse(){}
	
	public ButcherHouse(Start villagePiece, int par2, Random random, StructureBoundingBox sbb, EnumFacing facing) {
         super(villagePiece, par2);
		this.setCoordBaseMode(facing);
         this.coordBaseMode = facing;
         this.boundingBox = sbb;
         this.random = random;
	}

	 public static ButcherHouse buildComponent(Start villagePiece, List pieces, Random random, int x, int y, int z, EnumFacing facing, int p5) {
		 StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 10, 7, 12, facing);
		 return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new ButcherHouse(villagePiece, p5, random, box, facing) : null;
	 }

	
	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox sbb) {
		if (this.averageGroundLvl < 0) {
			this.averageGroundLvl = this.getAverageGroundLevel(world, sbb);

			if (this.averageGroundLvl < 0) {
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

			int totalTypes = ModuleMeat.registry.meatIds().size();
			randomNum = random.nextInt((totalTypes - 1) + 1) + 1;
			randomNum1 = ((randomNum + 1) % totalTypes) + 1;

			int j1 = this.getXWithOffset(2 + 1, 9);
			int k1 = this.getYWithOffset(1);
			int l1 = this.getZWithOffset(2 + 1, 9);
			int j2 = this.getXWithOffset(6 + 1, 9);
			int l2 = this.getZWithOffset(6 + 1, 9);

			List<EntityLiving> pastures = this.getPastureEntities(randomNum, world);
			EntityLiving aniA = pastures.get(0);
			EntityLiving aniB = pastures.get(1);
			aniA.setPosition(j1, k1, l1);
			aniB.setPosition(j1, k1, l1);
			world.spawnEntity(aniA);
			world.spawnEntity(aniB);

			pastures = this.getPastureEntities(randomNum1, world);
			aniA = pastures.get(0);
			aniB = pastures.get(1);
			aniA.setPosition(j2, k1, l2);
			aniB.setPosition(j2, k1, l2);
			world.spawnEntity(aniA);
			world.spawnEntity(aniB);

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
		this.placeBlockAtCurrentPosition(world, Blocks.OAK_FENCE,  2, 2, 4, sbb);


		spawnVillagers(world, sbb, 1, 1, 5, 1);
		

		return true;
	}

	@Override
	protected net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession chooseForgeProfession(int count, net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession prof)
	{
		return ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("starvationahoy:SAButcher"));
	}
	
	
	private void placeHanger(World world, StructureBoundingBox sbb, int x, int y, int z, int rand){
		int placeState = MathHelper.getInt(random, 0, 2);
		world.setBlockState(new BlockPos(x, y, z), MeatBlockRegistry.MEAT_HANGER.getDefaultState().withProperty(BlockContainerRotate.FACING, this.coordBaseMode), 2);
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		if(te instanceof MeatHangerTileEntity){
			MeatHangerTileEntity MHA = (MeatHangerTileEntity) te;
			if(placeState > 0) {
				MHA.updateHanger(rand, MeatHangerData.MeatStates.values()[placeState]);
				world.notifyBlockUpdate(new BlockPos(x, y, z), world.getBlockState(new BlockPos(x, y, z)).getBlock().getDefaultState(),
						world.getBlockState(new BlockPos(x, y, z)).getBlock().getDefaultState(), 3);
			}
		}
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
	private void placeBlockAtCurrentPosition(World world, Block block, int x, int y, int z, StructureBoundingBox sbb){
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

	private List<EntityLiving> getPastureEntities(int registryNum, World world){
		EntityLiving animal1;
		EntityLiving animal2;

		switch (registryNum){
			case 1:
				animal1 = new EntityCow(world);
				animal2 = new EntityCow(world);
				break;
			case 2:
				animal1 = new EntityPig(world);
				animal2 = new EntityPig(world);
				break;
			case 3:
				animal1 = new EntityChicken(world);
				animal2 = new EntityChicken(world);
				break;
			case 4:
				animal1 = new EntitySheep(world);
				animal2 = new EntitySheep(world);
				break;
			case 5:
				animal1 = new EntityRabbit(world);
				animal2 = new EntityRabbit(world);
				break;
			default:
				animal1 = new EntityCow(world);
				animal2 = new EntityCow(world);
		}
		List<EntityLiving> entities = new ArrayList<>();
		entities.add(animal1);
		entities.add(animal2);
		return entities;
	}

}
