package the_fireplace.netheressence.handlers

import net.minecraft.item.Item
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.event.entity.item.ItemExpireEvent
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import the_fireplace.netheressence.NetherEssence

/**
	* @author The_Fireplace
	*/
@Mod.EventBusSubscriber(modid = NetherEssence.MODID)
object ForgeEvents {
	@SubscribeEvent
	def stackExpire(event: ItemExpireEvent) {
		if (!event.isCanceled && !event.getEntity.world.isRemote && !event.getEntityItem.getItem.isEmpty && !event.getEntityItem.cannotPickup) {
			DeadStackData.getInstance.deadStacks.add(event.getEntityItem.getItem.writeToNBT(new NBTTagCompound).toString)
		}
	}

	@SubscribeEvent
	def worldSave(event: WorldEvent.Unload) {
		DeadStackData.save()
	}

	@SubscribeEvent
	def fuelEvent(event: FurnaceFuelBurnTimeEvent) {
		if (event.getItemStack.getItem == NetherEssence.nether_essence)
			event.setBurnTime(800)
		else if (event.getItemStack.getItem == Item.getItemFromBlock(NetherEssence.nether_essence_block))
			event.setBurnTime(6400)
		else if (event.getItemStack.getItem == Item.getItemFromBlock(NetherEssence.radiant_nether_essence_block))
			event.setBurnTime(6200)
	}
}