package ml.sgworlds.api.world.feature.gen;

import ml.sgworlds.api.world.WorldFeatureProvider.IWorldFeature;

public interface ITerrainGenerator extends IWorldFeature {

	public void generateTerrain(int chunkX, int chunkY, short[] blockIds, byte[] blockMetas);

}
