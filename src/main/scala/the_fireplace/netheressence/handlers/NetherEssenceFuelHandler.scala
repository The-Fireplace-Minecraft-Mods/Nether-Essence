package the_fireplace.netheressence.handlers

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.IFuelHandler
import the_fireplace.netheressence.NetherEssence

class NetherEssenceFuelHandler extends IFuelHandler {

	override def getBurnTime(fuel: ItemStack): Int = {
		if (fuel.getItem == NetherEssence.nether_essence) {
			800
		} else if (fuel.getItem == Item.getItemFromBlock(NetherEssence.nether_essence_block)) {
			6400
		} else if (fuel.getItem == Item.getItemFromBlock(NetherEssence.radiant_nether_essence_block)) {
			6200
		} else {
			0
		}
	}
}
