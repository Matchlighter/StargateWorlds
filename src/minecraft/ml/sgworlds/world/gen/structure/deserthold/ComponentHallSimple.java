package ml.sgworlds.world.gen.structure.deserthold;

import java.util.List;
import java.util.Random;

import ml.core.world.structure.StructureBuilder;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentHallSimple extends ComponentHallBase {

	public ComponentHallSimple() {}
	
	@Override
	public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random rnd) {
		super.buildComponent(par1StructureComponent, par2List, rnd);
		
		ComponentHoldStart ic = (ComponentHoldStart)par1StructureComponent;
		componentEast = ic.getNextStructureComponent(this, 1, ic.roomWeightedComponents, par2List, getAbsOffset( 4, 0, 0), rnd) != null;
		componentWest = ic.getNextStructureComponent(this, 3, ic.roomWeightedComponents, par2List, getAbsOffset(-4, 0, 0), rnd) != null;
	}
	
	@Override
	protected boolean addHallComponentParts(StructureBuilder b, World world, Random rand, StructureBoundingBox chunkBox) {
		
		b.fillArea(-3, 4, -2, 3, 4, 2, Block.sandStone, 2);
		
		if (componentEast) { // +X
			b.fillArea(2, 1, -1, 3, 3, 1, null, 0);
		}
		
		if (componentWest) { // -X
			b.fillArea(-3, 1, -1, 2, 3, 1, null, 0);
		}
		
		return true;
	}
}
