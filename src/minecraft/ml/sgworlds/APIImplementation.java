package ml.sgworlds;

import ml.sgworlds.api.SGWorldsAPI;
import ml.sgworlds.api.world.IGateTempleGenerator;
import ml.sgworlds.api.world.IStaticWorld;
import ml.sgworlds.api.world.feature.IFeatureManager;
import ml.sgworlds.world.SGWorldManager;
import ml.sgworlds.world.feature.FeatureManager;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.FMLLog;

public class APIImplementation extends SGWorldsAPI {

	public static void initAPI() {
		FMLLog.info("Exposing SGWorlds API Instance");
		sgworldsAPI = new APIImplementation();
	}

	@Override
	public CreativeTabs getSGWorldsCreativeTab() {
		return Registry.creativeTab;
	}

	@Override
	public IFeatureManager getFeatureManager() {
		return FeatureManager.instance;
	}

	@Override
	public boolean registerStaticWorld(IStaticWorld staticWorld) {
		return SGWorldManager.staticWorlds.add(staticWorld);
	}
	
	@Override
	public boolean registerGateTemple(IGateTempleGenerator templeGen) {
		if (SGWorldManager.templeGens.contains(templeGen)) return false;
		return SGWorldManager.templeGens.add(templeGen);
	}

}
