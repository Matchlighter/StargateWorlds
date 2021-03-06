package ml.sgworlds.api.world.feature.prefab;

import ml.sgworlds.api.world.IWorldData;
import ml.sgworlds.api.world.feature.FeatureProvider;
import ml.sgworlds.api.world.feature.WorldFeature;
import ml.sgworlds.api.world.feature.types.IBiomeController;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;

public abstract class BaseBiomeController extends WorldFeature implements IBiomeController {
	
	protected BiomeCache biomeCache;

	public BaseBiomeController(FeatureProvider provider, IWorldData worldData) {
		super(provider, worldData);
		
	}
	
	@Override
	public void onProviderCreated(WorldProvider wprovider) {
		this.biomeCache = new BiomeCache(worldData.getWorldProvider().worldChunkMgr);
		super.onProviderCreated(wprovider);
	}

	@Override
	public BiomeGenBase getBiomeAt(int x, int z) {
		return this.biomeCache.getBiomeGenAt(x, z);
	}
	
	public abstract BiomeGenBase calcBiomeAt(int x, int z);
	
	@Override
	public BiomeGenBase[] getBiomesAt(BiomeGenBase[] reuseArray, int x, int z, int width, int length, boolean cacheFlag) {
		IntCache.resetIntCache();

		if (reuseArray == null || reuseArray.length < width * length) {
			reuseArray = new BiomeGenBase[width * length];
		}

		if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0) {
			BiomeGenBase[] abiomegenbase1 = this.biomeCache.getCachedBiomes(x, z);
			System.arraycopy(abiomegenbase1, 0, reuseArray, 0, width * length);
			return reuseArray;
		} else {
			for (int lx=0; lx<width; lx++) {
				for (int lz=0; lz<length; lz++) {
					reuseArray[lx + lz*width] = calcBiomeAt(x+lx, z+lz);
				}
			}
		}

		return reuseArray;
	}

	@Override
	public float[] getRainfall(float[] reuseArray, int x, int z, int width, int length) {
		IntCache.resetIntCache();

		if (reuseArray == null || reuseArray.length < width * length) {
			reuseArray = new float[width * length];
		}

		for (int lx=0; lx<width; lx++) {
			for (int lz=0; lz<length; lz++) {
				float f = calcBiomeAt(x+lx, z+lz).rainfall;
				if (f>1.0F) f=1.0F;
				reuseArray[lx + lz*width] = f;
			}
		}

		return reuseArray;
	}

	@Override
	public float[] getTemperatures(float[] reuseArray, int x, int z, int width, int length) {
		IntCache.resetIntCache();

		if (reuseArray == null || reuseArray.length < width * length) {
			reuseArray = new float[width * length];
		}

		for (int lx=0; lx<width; lx++) {
			for (int lz=0; lz<length; lz++) {
				float f = calcBiomeAt(x+lx, z+lz).temperature;
				if (f>1.0F) f=1.0F;
				reuseArray[lx + lz*width] = f;
			}
		}

		return reuseArray;
	}

	@Override
	public void cleanCache() {
		biomeCache.cleanupCache();
	}

}
