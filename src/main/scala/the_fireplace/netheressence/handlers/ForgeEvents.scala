package the_fireplace.netheressence.handlers

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.event.entity.item.ItemExpireEvent
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

/**
	* @author The_Fireplace
	*/
class ForgeEvents {
	@SubscribeEvent
	def stackExpire(event: ItemExpireEvent) {
		if (!event.isCanceled && !event.getEntity.world.isRemote && event.getEntityItem.getEntityItem != null && !event.getEntityItem.cannotPickup) {
			DeadStackData.getInstance.deadStacks.add(event.getEntityItem.getEntityItem.writeToNBT(new NBTTagCompound).toString)
		}
	}
	@SubscribeEvent
	def worldSave(event: WorldEvent.Unload) {
		DeadStackData.save()
	}
}