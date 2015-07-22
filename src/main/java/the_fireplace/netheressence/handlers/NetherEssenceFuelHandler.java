package the_fireplace.netheressence.handlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import the_fireplace.netheressence.NetherEssence;
/**
 *
 * @author The_Fireplace
 *
 */
public class NetherEssenceFuelHandler implements IFuelHandler {
	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() == NetherEssence.nether_essence){
			return 800;
		}else if(fuel.getItem() == Item.getItemFromBlock(NetherEssence.nether_essence_block)){
			return 6400;
		}else if(fuel.getItem() == Item.getItemFromBlock(NetherEssence.radiant_nether_essence_block)){
			return 6200;
		}else{
			return 0;
		}
	}
}