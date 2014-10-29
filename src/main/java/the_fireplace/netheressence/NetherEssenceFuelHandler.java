package the_fireplace.netheressence;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class NetherEssenceFuelHandler implements IFuelHandler {
    @Override
	public int getBurnTime(ItemStack fuel) {
        if(fuel.getItem() == NetherEssence.netherDust){
            return 800;}else
        if(fuel.getItem() == Item.getItemFromBlock(NetherEssence.netherDustBlock)){
            return 6400;}
        else 
        {
        return 0;
        }
        }
}
