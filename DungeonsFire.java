package greymerk.roguelike;

import java.util.List;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;

public class DungeonsFire implements IDungeon{

	
	
	@Override
	public boolean generate(World inWorld, Random inRandom, int inOriginX, int inOriginY, int inOriginZ) {

		BlockRandomizer fillBlocks;
		
		fillBlocks = new BlockRandomizer(inRandom, new MetaBlock(Block.stoneBrick.blockID));
		fillBlocks.addBlock(new MetaBlock(Block.stoneBrick.blockID, 1), 3);
		fillBlocks.addBlock(new MetaBlock(Block.stoneBrick.blockID, 2), 3);
		fillBlocks.addBlock(new MetaBlock(Block.cobblestone.blockID, 1), 20);
		fillBlocks.addBlock(new MetaBlock(Block.gravel.blockID, 1), 30);
		
		// clear air
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 6, inOriginY, inOriginZ - 6, inOriginX + 6, inOriginY + 3, inOriginZ + 6, 0);
		
		// floor
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 7, inOriginY - 1, inOriginZ - 7, inOriginX + 7, inOriginY - 1, inOriginZ + 7, fillBlocks);
		
		// fill door walls
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 7, inOriginY - 1, inOriginZ - 4, inOriginX - 7, inOriginY + 5, inOriginZ + 4, fillBlocks, false, true);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX + 7, inOriginY - 1, inOriginZ - 4, inOriginX + 7, inOriginY + 5, inOriginZ + 4, fillBlocks, false, true);
		
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 4, inOriginY - 1, inOriginZ - 7, inOriginX + 4, inOriginY + 5, inOriginZ - 7, fillBlocks, false, true);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 4, inOriginY - 1, inOriginZ + 7, inOriginX + 4, inOriginY + 5, inOriginZ + 7, fillBlocks, false, true);
		
		// columns
		
		// column 1
		WorldGenPrimitive.fillRectHollow(inWorld, inOriginX - 6, inOriginY, inOriginZ - 6, inOriginX - 4, inOriginY + 6, inOriginZ - 4, fillBlocks, true, true);
		WorldGenPrimitive.setBlock(inWorld, inOriginX - 4, inOriginY + 1, inOriginZ - 5, Block.fenceIron.blockID);
		WorldGenPrimitive.setBlock(inWorld, inOriginX - 4, inOriginY + 2, inOriginZ - 5, Block.fenceIron.blockID);
		
		WorldGenPrimitive.setBlock(inWorld, inOriginX - 5, inOriginY + 1, inOriginZ - 4, Block.fenceIron.blockID);
		WorldGenPrimitive.setBlock(inWorld, inOriginX - 5, inOriginY + 2, inOriginZ - 4, Block.fenceIron.blockID);
		
		WorldGenPrimitive.setBlock(inWorld, inOriginX - 5, inOriginY, inOriginZ - 5, Block.netherrack.blockID);
		WorldGenPrimitive.setBlock(inWorld, inOriginX - 5, inOriginY + 1, inOriginZ - 5, Block.fire.blockID);
		
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 3, inOriginY + 3, inOriginZ - 6, inOriginX - 3, inOriginY + 3, inOriginZ - 5, fillBlocks);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 6, inOriginY + 3, inOriginZ - 3, inOriginX - 5, inOriginY + 3, inOriginZ - 3, fillBlocks);
		
		// column 2
		WorldGenPrimitive.fillRectHollow(inWorld, inOriginX - 6, inOriginY, inOriginZ + 4, inOriginX - 4, inOriginY + 6, inOriginZ + 6, fillBlocks, true, true);
		WorldGenPrimitive.setBlock(inWorld, inOriginX - 4, inOriginY + 1, inOriginZ + 5, Block.fenceIron.blockID);
		WorldGenPrimitive.setBlock(inWorld, inOriginX - 4, inOriginY + 2, inOriginZ + 5, Block.fenceIron.blockID);
		
		WorldGenPrimitive.setBlock(inWorld, inOriginX - 5, inOriginY + 1, inOriginZ + 4, Block.fenceIron.blockID);
		WorldGenPrimitive.setBlock(inWorld, inOriginX - 5, inOriginY + 2, inOriginZ + 4, Block.fenceIron.blockID);
		
		WorldGenPrimitive.setBlock(inWorld, inOriginX - 5, inOriginY, inOriginZ + 5, Block.netherrack.blockID);
		WorldGenPrimitive.setBlock(inWorld, inOriginX - 5, inOriginY + 1, inOriginZ + 5, Block.fire.blockID);
		
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 3, inOriginY + 3, inOriginZ + 5, inOriginX - 3, inOriginY + 3, inOriginZ + 6, Block.stoneBrick.blockID);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 6, inOriginY + 3, inOriginZ + 3, inOriginX - 5, inOriginY + 3, inOriginZ + 3, Block.stoneBrick.blockID);
		
		//column 3
		WorldGenPrimitive.fillRectHollow(inWorld, inOriginX + 4, inOriginY, inOriginZ - 6, inOriginX + 6, inOriginY + 6, inOriginZ - 4, fillBlocks, true, true);
		WorldGenPrimitive.setBlock(inWorld, inOriginX + 4, inOriginY + 1, inOriginZ - 5, Block.fenceIron.blockID);
		WorldGenPrimitive.setBlock(inWorld, inOriginX + 4, inOriginY + 2, inOriginZ - 5, Block.fenceIron.blockID);
		
		WorldGenPrimitive.setBlock(inWorld, inOriginX + 5, inOriginY + 1, inOriginZ - 4, Block.fenceIron.blockID);
		WorldGenPrimitive.setBlock(inWorld, inOriginX + 5, inOriginY + 2, inOriginZ - 4, Block.fenceIron.blockID);
		
		WorldGenPrimitive.setBlock(inWorld, inOriginX + 5, inOriginY, inOriginZ - 5, Block.netherrack.blockID);
		WorldGenPrimitive.setBlock(inWorld, inOriginX + 5, inOriginY + 1, inOriginZ - 5, Block.fire.blockID);
		
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX + 3, inOriginY + 3, inOriginZ - 6, inOriginX + 3, inOriginY + 3, inOriginZ - 5, fillBlocks);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX + 5, inOriginY + 3, inOriginZ - 3, inOriginX + 6, inOriginY + 3, inOriginZ - 3, fillBlocks);
		
		// column 4
		WorldGenPrimitive.fillRectHollow(inWorld, inOriginX + 4, inOriginY, inOriginZ + 4, inOriginX + 6, inOriginY + 6, inOriginZ + 6, fillBlocks, true, true);
		WorldGenPrimitive.setBlock(inWorld, inOriginX + 4, inOriginY + 1, inOriginZ + 5, Block.fenceIron.blockID);
		WorldGenPrimitive.setBlock(inWorld, inOriginX + 4, inOriginY + 2, inOriginZ + 5, Block.fenceIron.blockID);
		
		WorldGenPrimitive.setBlock(inWorld, inOriginX + 5, inOriginY + 1, inOriginZ + 4, Block.fenceIron.blockID);
		WorldGenPrimitive.setBlock(inWorld, inOriginX + 5, inOriginY + 2, inOriginZ + 4, Block.fenceIron.blockID);
		
		WorldGenPrimitive.setBlock(inWorld, inOriginX + 5, inOriginY, inOriginZ + 5, Block.netherrack.blockID);
		WorldGenPrimitive.setBlock(inWorld, inOriginX + 5, inOriginY + 1, inOriginZ + 5, Block.fire.blockID);
		
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX + 3, inOriginY + 3, inOriginZ + 5, inOriginX + 3, inOriginY + 3, inOriginZ + 6, fillBlocks);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX + 5, inOriginY + 3, inOriginZ + 3, inOriginX + 6, inOriginY + 3, inOriginZ + 3, fillBlocks);
		
		// mid ceiling
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 6, inOriginY + 4, inOriginZ - 6, inOriginX + 6, inOriginY + 4, inOriginZ + 6, fillBlocks);
		
		// upper ceiling
		WorldGenPrimitive.fillRectHollow(inWorld, inOriginX - 4, inOriginY + 4, inOriginZ - 4, inOriginX + 4, inOriginY + 8, inOriginZ + 4, fillBlocks, false, true);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 3, inOriginY + 4, inOriginZ - 3, inOriginX + 3, inOriginY + 7, inOriginZ + 3, 0);
		
		
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 4, inOriginY + 4, inOriginZ - 2, inOriginX - 4, inOriginY + 4, inOriginZ + 2, 0);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX + 4, inOriginY + 4, inOriginZ - 2, inOriginX + 4, inOriginY + 4, inOriginZ + 2, 0);
		
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 2, inOriginY + 4, inOriginZ - 4, inOriginX + 2, inOriginY + 4, inOriginZ - 4, 0);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 2, inOriginY + 4, inOriginZ + 4, inOriginX + 2, inOriginY + 4, inOriginZ + 4, 0);
		
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 3, inOriginY + 5, inOriginZ - 3, inOriginX - 3, inOriginY + 7, inOriginZ - 3, fillBlocks, false, true);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 3, inOriginY + 5, inOriginZ + 3, inOriginX - 3, inOriginY + 7, inOriginZ + 3, fillBlocks, false, true);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX + 3, inOriginY + 5, inOriginZ - 3, inOriginX + 3, inOriginY + 7, inOriginZ - 3, fillBlocks, false, true);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX + 3, inOriginY + 5, inOriginZ + 3, inOriginX + 3, inOriginY + 7, inOriginZ + 3, fillBlocks, false, true);
		
		// ceiling cross
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 5, inOriginY + 5, inOriginZ, inOriginX + 5, inOriginY + 5, inOriginZ, fillBlocks);
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX, inOriginY + 5, inOriginZ - 5, inOriginX, inOriginY + 5, inOriginZ + 5, fillBlocks);
		
		WorldGenPrimitive.fillRectSolid(inWorld, inOriginX - 1, inOriginY + 5, inOriginZ - 1, inOriginX + 1, inOriginY + 5, inOriginZ + 1, fillBlocks);
		WorldGenPrimitive.setBlock(inWorld, inOriginX, inOriginY + 5, inOriginZ, 0);
		
		List<Coord> chestSpace = WorldGenPrimitive.getRectSolid(inOriginX - 3, inOriginY, inOriginZ - 3, inOriginX + 3, inOriginY, inOriginZ + 3);
		
		TreasureChest.createChests(inWorld, inRandom, 1 + inRandom.nextInt(2), chestSpace);
		
		return false;
	}

	@Override
	public boolean isValidDungeonLocation(World world, int x, int y, int z) {
		return false;
	}

}
