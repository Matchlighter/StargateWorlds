package ml.sgworlds;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class SGWorldsCreativeTab extends CreativeTabs {

	public SGWorldsCreativeTab() {
		super("StargateWorlds");
	}

	@Override
	public ItemStack getIconItemStack() {
		return Registry.itemTablet.createStack(1);
	}

}
