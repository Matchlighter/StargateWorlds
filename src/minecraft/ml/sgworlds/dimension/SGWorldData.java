package ml.sgworlds.dimension;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.common.DimensionManager;
import stargatetech2.api.stargate.Address;
import cpw.mods.fml.common.FMLLog;

/**
 * Stores info for individual worlds generated by SGWorlds. e.g. Day Length, # of suns/moons, etc.
 * Pre-generated when a world is started.
 * @author Matchlighter
 */
public class SGWorldData extends WorldSavedData {

	private String name = null;
	private String designation;
	private Address primaryAddress;
	private int dimensionId = 0;
	
	public SGWorldData(String Designation, Address address) {
		super(getUID(address));
		// TODO Auto-generated constructor stub
	}

	public String getDisplayName() {
		return name != null ? name : designation;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}

	public static String getUID(Address sga) {
		return "sgworlddata_"+sga.toString().replace(" ", "");
	}
	
	public static SGWorldData loadData(Address sga) {
		World overWorld = DimensionManager.getWorld(0);
		String ufn = getUID(sga);
		SGWorldData data = (SGWorldData)overWorld.loadItemData(SGWorldData.class, ufn);
		
		if (data == null) { //Somebody did something stupid.
			FMLLog.severe("Could not load world data for SGWorlds world %s", sga.toString());
			data = WorldGenerator.instance.generateRandomWorld();
			overWorld.setItemData(ufn, data);
			data.markDirty();
		}
		return data;
	}
}
