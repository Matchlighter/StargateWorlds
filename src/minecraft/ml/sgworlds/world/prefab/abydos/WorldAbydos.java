package ml.sgworlds.world.prefab.abydos;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureStart;

import ml.core.world.structure.StructureBuilder;
import ml.sgworlds.api.world.IGateTempleGenerator;
import ml.sgworlds.api.world.IStaticWorld;
import ml.sgworlds.api.world.IWorldData;
import ml.sgworlds.api.world.feature.FeatureProvider;
import ml.sgworlds.api.world.feature.IFeatureBuilder;
import ml.sgworlds.api.world.feature.SGWFeature;
import ml.sgworlds.api.world.feature.WorldFeature;
import ml.sgworlds.api.world.feature.types.IStructureProvider;
import ml.sgworlds.world.feature.FeatureBuilder;
import ml.sgworlds.world.gen.temples.TemplePyramid;
import stargatetech2.api.StargateTechAPI;
import stargatetech2.api.stargate.Address;
import stargatetech2.transport.ModuleTransport;

public class WorldAbydos implements IStaticWorld {

	@Override
	public long getSeed() {
		return 0;
	}

	@Override
	public Address getAddress() {
		return StargateTechAPI.api().getStargateNetwork().parseAddress("Croecom Erpvabrei At");
	}

	@Override
	public String getDesignation() {
		return "P8X-873";
	}

	@Override
	public String getName() {
		return "Abydos";
	}

	@Override
	public List<WorldFeature> getWorldFeatureList(IWorldData worldData) {
		IFeatureBuilder fbuilder = new FeatureBuilder(worldData);
		Random rand = new Random();
		
		// Suns
		fbuilder.createFeatureReflection(SGWFeature.SUN_NORMAL.name());
		
		// Moons
		fbuilder.createFeatureReflection(SGWFeature.MOON_NORMAL.name(), "size", 15, "angle", 70, "orbitPeriod", 10000L);
		fbuilder.createFeatureReflection(SGWFeature.MOON_NORMAL.name(), "size", 25, "angle", 10, "orbitPeriod", 32000L, "offset", -0.35F);
		fbuilder.createFeatureReflection(SGWFeature.MOON_NORMAL.name(), "size", 20);
		
		// Biome
		fbuilder.createFeatureConstructor(SGWFeature.BIOME_SINGLE.name(), BiomeGenBase.desert);
		
		// Terrain Modifiers
		fbuilder.createFeatureConstructor(SGWFeature.MOD_CAVES.name());
		fbuilder.createFeatureConstructor(SGWFeature.MOD_RAVINES.name());
		
		// Populators
		fbuilder.createFeatureConstructor(SGWFeature.POPULATE_ORE_NAQUADAH.name());
		
		// Structures
		fbuilder.createFeatureConstructor(SGWFeature.STRUCTURE_CARTOUCHE.name());
		
		// Clouds
		fbuilder.createFeatureConstructor(SGWFeature.CLOUD_COLOR_NORMAL.name(), 0xEFEDCB);
		
		// Fog
		fbuilder.createFeatureConstructor(SGWFeature.FOG_COLOR_NORMAL.name(), 0xCCBF8C);
		
		return fbuilder.getFeatureList();
	}

	@Override
	public IGateTempleGenerator getTempleGenerator(WorldServer world) {
		return new TempleAbydos();
	}
	
	@Override
	public String getDescription() {
		return "Insert description here"; // TODO
	}

	private class TempleAbydos extends TemplePyramid {
		public TempleAbydos() {
			super(48);
		}
		
		@Override
		public void generateGateTemple(World world, ChunkPosition gateCoords, int gateRotation) {
			super.generateGateTemple(world, gateCoords, gateRotation);
			StructureBuilder th = new StructureBuilder(world, gateCoords, gateRotation);
			th.ioffset.posZ = gateOffset;
			
			// Transport Rings
			th.setBlockAt(0, -1, 0, ModuleTransport.transportRing, 0);
			th.setBlockAt(0, gateRoomHeight + 1, 0, ModuleTransport.transportRing, 0);
			
			
			// Upper Floor
			th.ioffset.posZ = gateOffset + sanctumOffset;
			int out = plevels - (gateRoomHeight + 2);
			th.fillArea(-out, gateRoomHeight+2, -out, out, gateRoomHeight+2, out, Block.sandStone, 2);
			
			// TODO Add treasure and monsters
			
		}
	}
	
	private static class NagadaCityProvider extends WorldFeature implements IStructureProvider {

		public NagadaCityProvider(FeatureProvider provider, IWorldData worldData) {
			super(provider, worldData);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean willProvideStructureFor(World world, int chunkX, int chunkZ) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public StructureStart getStructureStart(World world, Random rand, int chunkX, int chunkZ) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public StructureStrata getStrata() {
			return StructureStrata.Aboveground;
		}

		@Override
		public void writeNBTData(NBTTagCompound tag) {}
		
	}
}
