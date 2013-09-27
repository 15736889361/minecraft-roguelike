package greymerk.roguelike;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class DungeonsPit implements IDungeon {
	World world;
	Random rand;
	int originX;
	int originY;
	int originZ;
	byte dungeonHeight;
	int dungeonLength;
	int dungeonWidth;
	int woolColor;
	int numChests;
	
	int floorBlock;
	int wallBlock;
	
	public DungeonsPit() {
		dungeonHeight = 3;
		dungeonLength = 2;
		dungeonWidth = 2;
		floorBlock = Block.stoneBrick.blockID;
		wallBlock = Block.stoneBrick.blockID;
	}

	public boolean generate(World inWorld, Random inRandom, int inOriginX, int inOriginY, int inOriginZ) {
		world = inWorld;
		rand = inRandom;
		originX = inOriginX;
		originY = inOriginY;
		originZ = inOriginZ;

		buildWalls();
		buildFloor();
		buildRoof();
		buildPit();
		

		for(int dir = 0; dir < 4; dir++){
			setTrap(dir);
		}
		
		List<Coord> space = new ArrayList<Coord>();
		space.add(new Coord(originX - 2, originY, originZ - 2));
		space.add(new Coord(originX - 2, originY, originZ + 2));
		space.add(new Coord(originX + 2, originY, originZ - 2));
		space.add(new Coord(originX + 2, originY, originZ + 2));
		
		TreasureChest.createChests(inWorld, inRandom, 1, space);
		
		return true;
	}
	
	public boolean isValidDungeonLocation(World world, int originX, int originY, int originZ) {

		int dungeonClearance = 0;
		for (int x = originX - dungeonLength - 1; x <= originX + dungeonLength + 1; x++) {
			for (int y = originY - 1; y <= originY + dungeonHeight + 1; y++) {
				for (int z = originZ - dungeonWidth - 1; z <= originZ + dungeonWidth + 1; z++) {
					Material material = world.getBlockMaterial(x, y, z);

					if (y == originY - 1 && !material.isSolid()) {
						return false;
					}

					if (y == originY + dungeonHeight + 1 && !material.isSolid()) {
						return false;
					}

					if ((      x == originX - dungeonLength - 1
							|| x == originX + dungeonLength + 1
							|| z == originZ - dungeonWidth - 1
							|| z == originZ + dungeonWidth + 1)
							&& y == originY
							&& world.isAirBlock(x, y, z)
							&& world.isAirBlock(x, y + 1, z)){
						dungeonClearance++;
					}
				}
			}
		}

		if (dungeonClearance < 1 || dungeonClearance > 5) {
			return false;
		}

		return true;
	}

	protected void buildWalls() {
		for (int blockX = originX - dungeonLength - 1; blockX <= originX + dungeonLength + 1; blockX++) {
			for (int blockY = originY + dungeonHeight; blockY >= originY - 1; blockY--) {
				for (int blockZ = originZ - dungeonWidth - 1; blockZ <= originZ + dungeonWidth + 1; blockZ++) {

					// This prevents overlapping dungeons from overwriting
					// spawners
					if (world.getBlockId(blockX, blockY, blockZ) == Block.mobSpawner.blockID) {
						continue;
					}

					// this prevents overlapping dungeons from breaking chests
					if (world.getBlockId(blockX, blockY, blockZ) == Block.chest.blockID) {
						continue;
					}

					if (blockX == originX - dungeonLength - 1 || blockZ == originZ - dungeonWidth - 1 || blockX == originX + dungeonLength + 1 || blockZ == originZ + dungeonWidth + 1) {

						if (blockY >= 0 && !world.getBlockMaterial(blockX, blockY - 1, blockZ).isSolid()) {
							WorldGenPrimitive.setBlock(world, blockX, blockY, blockZ, 0);
							continue;
						}
						if (!world.getBlockMaterial(blockX, blockY, blockZ)
								.isSolid()) {
							continue;
						}
						
						WorldGenPrimitive.setBlock(world, blockX, blockY, blockZ, wallBlock);
						
					} else {
						WorldGenPrimitive.setBlock(world, blockX, blockY, blockZ, 0);
					}
				}
			}
		}
	}
	
	protected void buildFloor(){
		
		for (int blockX = originX - dungeonLength - 1; blockX <= originX + dungeonLength + 1; blockX++){
			for (int blockZ = originZ - dungeonWidth - 1; blockZ <= originZ + dungeonWidth + 1; blockZ++){
				if (rand.nextInt(4) == 0) {
					WorldGenPrimitive.setBlock(world, blockX, originY - 1, blockZ, floorBlock);
				}

				else {
					WorldGenPrimitive.setBlock(world, blockX, originY - 1, blockZ, wallBlock);
				}				
			}
		}
	}
	
	protected void buildRoof(){
		for (int blockX = originX - dungeonLength - 1; blockX <= originX + dungeonLength + 1; blockX++){
			for (int blockZ = originZ - dungeonWidth - 1; blockZ <= originZ + dungeonWidth + 1; blockZ++){
				WorldGenPrimitive.setBlock(world, blockX, originY + dungeonHeight + 1, blockZ, wallBlock);
			}
		}
	}

	private void buildPit(){
		
		for(int x = originX - 2; x <= originX + 2; x++){
			for(int z = originZ - 2; z <= originZ + 2; z++){
				for(int y = originY - 1; y > 0; y--){
					
					if(world.getBlockId(x, y, z) == 0){
						continue;
					}
					
					if(y < 0 + rand.nextInt(5) && world.getBlockId(x, y, z) == Block.bedrock.blockID){
						continue;
					}
					
					if(world.getBlockId(x, y, z) == Block.mobSpawner.blockID){
						continue;
					}
					
					if(    x == originX - 2
						|| x == originX +2
						|| z == originZ -2
						|| z == originZ +2){
						
						WorldGenPrimitive.setBlock(world, x, y, z, Block.stoneBrick.blockID, rand.nextInt(3), 2, true, true);
						continue;
						
					}
					
					if(y < 10){
						WorldGenPrimitive.setBlock(world, x, y, z, Block.waterStill.blockID);
						continue;
					}
					
					WorldGenPrimitive.setBlock(world, x, y, z, 0);
				}
			}
		}
	}
	
	private void setTrap(int dir){
		
		switch(dir){
		
		// South
		case 0: 
			

			for (int x = originX - 1; x <= originX + 1; x++){
				for (int z = originZ + 6; z >= originZ + 3; z--){
					for (int y = originY - 2; y <= originY + 3; y++){
						if(world.isAirBlock(x, y, z)){
							return;
						}
					}
				}
			}
			
			world.setBlock(originX, originY, originZ + 2, Block.pressurePlateStone.blockID);
			world.setBlock(originX, originY - 1, originZ + 3, Block.torchRedstoneActive.blockID, 3, 2);
			world.setBlock(originX, originY - 1, originZ + 4, Block.redstoneWire.blockID);
			world.setBlock(originX, originY, originZ + 5, Block.torchRedstoneIdle.blockID, 5, 2);
			world.setBlock(originX, originY + 1, originZ + 4, Block.pistonStickyBase.blockID, 2, 2);
			break;
			
			
		// West
		case 1:
			
			for (int x = originX - 6; x <= originX - 3; x++){
				for (int z = originZ - 1; z <= originZ + 1; z++){
					for (int y = originY - 2; y <= originY + 3; y++){
						if(world.isAirBlock(x, y, z)){
							return;
						}
					}
				}
			}
			
			world.setBlock(originX - 2, originY, originZ, Block.pressurePlateStone.blockID);
			world.setBlock(originX - 3, originY - 1, originZ, Block.torchRedstoneActive.blockID, 2, 2);
			world.setBlock(originX - 4, originY - 1, originZ, Block.redstoneWire.blockID);
			world.setBlock(originX - 5, originY, originZ, Block.torchRedstoneIdle.blockID, 5, 2);
			world.setBlock(originX - 4, originY + 1, originZ, Block.pistonStickyBase.blockID, 5, 2);
			break;
			
		// North
		case 2:

			for (int x = originX - 1; x <= originX + 1; x++){
				for (int z = originZ - 6; z <= originZ - 3; z++){
					for (int y = originY - 2; y <= originY + 3; y++){
						if(world.isAirBlock(x, y, z)){
							return;
						}
					}
				}
			}
			
			world.setBlock(originX, originY, originZ - 2, Block.pressurePlateStone.blockID);
			world.setBlock(originX, originY - 1, originZ - 3, Block.torchRedstoneActive.blockID, 4, 2);
			world.setBlock(originX, originY - 1, originZ - 4, Block.redstoneWire.blockID);
			world.setBlock(originX, originY, originZ - 5, Block.torchRedstoneIdle.blockID, 5, 2);
			world.setBlock(originX, originY + 1, originZ - 4, Block.pistonStickyBase.blockID, 3, 2);
			break;
			
		// East 
		case 3:

			for (int x = originX + 6; x >= originX + 3; x--){
				for (int z = originZ - 1; z <= originZ + 1; z++){
					for (int y = originY - 2; y <= originY + 3; y++){
						if(world.isAirBlock(x, y, z)){
							return;
						}
					}
				}
			}
			
			world.setBlock(originX + 2, originY, originZ, Block.pressurePlateStone.blockID);
			world.setBlock(originX + 3, originY - 1, originZ, Block.torchRedstoneActive.blockID, 1, 2);
			world.setBlock(originX + 4, originY - 1, originZ, Block.redstoneWire.blockID);
			world.setBlock(originX + 5, originY, originZ, Block.torchRedstoneIdle.blockID, 5, 2);
			world.setBlock(originX + 4, originY + 1, originZ, Block.pistonStickyBase.blockID, 4, 2);
			break;
		}
	}
}
