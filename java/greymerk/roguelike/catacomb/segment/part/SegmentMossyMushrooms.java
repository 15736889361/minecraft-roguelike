package greymerk.roguelike.catacomb.segment.part;

import greymerk.roguelike.worldgen.BlockWeightedRandom;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.WorldGenPrimitive;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class SegmentMossyMushrooms extends SegmentBase {

	private int stairType;
	private BlockWeightedRandom mushrooms;
	
	
	@Override
	protected void genWall(Cardinal wallDirection) {
		
		MetaBlock stair = theme.getSecondaryStair();
		MetaBlock air = new MetaBlock(Blocks.air);
		
		mushrooms = new BlockWeightedRandom();
		mushrooms.addBlock(new MetaBlock(Blocks.brown_mushroom), 3);
		mushrooms.addBlock(new MetaBlock(Blocks.red_mushroom), 3);
		mushrooms.addBlock(air, 10);
		
		Coord cursor;
		Coord start;
		Coord end;
		
		Cardinal[] orth = Cardinal.getOrthogonal(wallDirection);
		start = new Coord(x, y, z);
		start.add(wallDirection, 2);
		end = new Coord(start);
		start.add(orth[0], 1);
		end.add(orth[1], 1);
		end.add(Cardinal.UP, 1);
		WorldGenPrimitive.fillRectSolid(world, rand, start, end, air, true, true);
		start.add(Cardinal.DOWN, 1);
		end.add(Cardinal.DOWN, 2);
		
		if(rand.nextInt(5) == 0){
			WorldGenPrimitive.fillRectSolid(world, rand, start, end, new MetaBlock(Blocks.water), true, true);
		} else {
			WorldGenPrimitive.fillRectSolid(world, rand, start, end, new MetaBlock(Blocks.mycelium), true, true);
			start.add(Cardinal.UP, 1);
			end.add(Cardinal.UP, 1);
			WorldGenPrimitive.fillRectSolid(world, rand, start, end, mushrooms, true, true);
		}
		
		for(Cardinal d : orth){
			cursor = new Coord(x, y, z);
			cursor.add(wallDirection, 2);
			cursor.add(d, 1);
			cursor.add(Cardinal.UP, 1);
			stair.setMeta(WorldGenPrimitive.blockOrientation(Cardinal.reverse(d), true));
			WorldGenPrimitive.setBlock(world, rand, cursor, stair, true, true);
		}

	}
}
