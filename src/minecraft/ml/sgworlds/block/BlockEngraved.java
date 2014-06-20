package ml.sgworlds.block;

import java.util.ArrayList;

import ml.sgworlds.Registry;
import ml.sgworlds.block.tile.TileEntityEngraved;
import ml.sgworlds.block.tile.TileEntityFacade;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEngraved extends BlockContainer {

	public BlockEngraved() {
		super(Registry.config.engravedBlockId, Material.rock);
	}

	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		return ((TileEntityFacade)te).getIconFromSide(side);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		TileEntityEngraved tee = new TileEntityEngraved();
		return tee;
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> alit = new ArrayList<ItemStack>();
		return alit;
	}
	
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		TileEntityEngraved tee = (TileEntityEngraved)par1World.getBlockTileEntity(par2, par3, par4);
		tee.setBlockSide(-1, Block.sandStone, 1);
		tee.setString(2, "Hello\nWorld");
		tee.updateClients();
		super.onBlockAdded(par1World, par2, par3, par4);
	}
}