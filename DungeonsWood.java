package greymerk.roguelike;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;

public class DungeonsWood implements IDungeon{
	
	private static final int HEIGHT = 3;
	private static final int WIDTH = 2;
	private static final int LENGTH = 3;
	private static final int NUM_CHESTS = 2;
	
	private int originX;
	private int originY;
	private int originZ;

	private int woodType;
	
	public DungeonsWood() {
	}

	@Override
	public boolean generate(World inWorld, Random inRandom, int inOriginX, int inOriginY, int inOriginZ) {
		
		originX = inOriginX;
		originY = inOriginY;
		originZ = inOriginZ;
		woodType = inRandom.nextInt(4);
		
		WorldGenPrimitive.fillRectSolid(inWorld, originX - WIDTH, originY, originZ - LENGTH, originX + WIDTH, originY + HEIGHT, originZ + LENGTH, 0);
		WorldGenPrimitive.fillRectHollow(inWorld, originX - WIDTH - 1, originY - 1, originZ - LENGTH - 1, originX + WIDTH + 1, originY + HEIGHT + 1, originZ + LENGTH + 1, Block.planks.blockID, woodType, 2, false, true);
		
		// log beams
		WorldGenPrimitive.fillRectSolid(inWorld, originX - WIDTH, originY, originZ - LENGTH, originX - WIDTH, originY + HEIGHT, originZ - LENGTH, Block.wood.blockID, woodType, 2, true, true);
		WorldGenPrimitive.fillRectSolid(inWorld, originX - WIDTH, originY, originZ + LENGTH, originX - WIDTH, originY + HEIGHT, originZ + LENGTH, Block.wood.blockID, woodType, 2, true, true);
		WorldGenPrimitive.fillRectSolid(inWorld, originX + WIDTH, originY, originZ - LENGTH, originX + WIDTH, originY + HEIGHT, originZ - LENGTH, Block.wood.blockID, woodType, 2, true, true);
		WorldGenPrimitive.fillRectSolid(inWorld, originX + WIDTH, originY, originZ + LENGTH, originX + WIDTH, originY + HEIGHT, originZ + LENGTH, Block.wood.blockID, woodType, 2, true, true);

		// glowstone
		WorldGenPrimitive.setBlock(inWorld, originX - WIDTH + 1, originY - 1, originZ - LENGTH + 1, Block.glowStone.blockID);
		WorldGenPrimitive.setBlock(inWorld, originX - WIDTH + 1, originY - 1, originZ + LENGTH - 1, Block.glowStone.blockID);
		WorldGenPrimitive.setBlock(inWorld, originX + WIDTH - 1, originY - 1, originZ - LENGTH + 1, Block.glowStone.blockID);
		WorldGenPrimitive.setBlock(inWorld, originX + WIDTH - 1, originY - 1, originZ + LENGTH - 1, Block.glowStone.blockID);
		
		WorldGenPrimitive.setBlock(inWorld, originX, originY, originZ, Block.wood.blockID, woodType, 2, true, true);
		WorldGenPrimitive.setBlock(inWorld, originX, originY + 1, originZ, Block.cake.blockID);
		
		List<Coord> space = new ArrayList<Coord>();
		space.add(new Coord(originX - WIDTH, originY, originZ - LENGTH + 1));
		space.add(new Coord(originX - WIDTH, originY, originZ + LENGTH - 1));
		space.add(new Coord(originX + WIDTH, originY, originZ - LENGTH + 1));
		space.add(new Coord(originX + WIDTH, originY, originZ + LENGTH - 1));
		
		TreasureChest[] types = {TreasureChest.FOOD, TreasureChest.POTIONS, TreasureChest.SUPPLIES};
		TreasureChest.createChests(inWorld, inRandom, 1 + inRandom.nextInt(2), space, types);
		
		return true;
	}

	@Override
	public boolean isValidDungeonLocation(World world, int x, int y, int z) {
		return false;
	}
}
