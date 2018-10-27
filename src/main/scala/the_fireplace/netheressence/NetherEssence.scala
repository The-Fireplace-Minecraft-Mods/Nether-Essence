package the_fireplace.netheressence

import java.lang.{Character => JChar}

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{Item, ItemBlock, ItemStack}
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.{Side, SideOnly}
import net.minecraftforge.registries.IForgeRegistry
import the_fireplace.netheressence.blocks.{BlockItemRecovery, BlockNetherEssence, BlockRadiantNetherEssence}

@Mod(modid = NetherEssence.MODID, name = NetherEssence.MODNAME, modLanguage = "scala", acceptedMinecraftVersions = "[1.12,1.13)")
@Mod.EventBusSubscriber
object NetherEssence {
	final val MODID = "netheressence"
	final val MODNAME = "Nether Essence"

	val tabNetherEssence: CreativeTabs = new CreativeTabs("tabNetherEssence") {
		override def createIcon: ItemStack = new ItemStack(NetherEssence.nether_essence)
	}

	val nether_essence = new Item().setCreativeTab(NetherEssence.tabNetherEssence).setTranslationKey("NetherDust").setRegistryName("NetherDust")

	val nether_essence_block = new BlockNetherEssence(Material.ROCK).setRegistryName("NetherDustBlock")

	val radiant_nether_essence_block = new BlockRadiantNetherEssence(Material.ROCK).setRegistryName("radiant_nether_essence_block")

	val item_recovery_block = new BlockItemRecovery(Material.ROCK).setRegistryName("item_recovery_block")

	var blockRegistry: IForgeRegistry[Block] = _

	def registerBlock(block: Block) {
		if (blockRegistry == null)
			return
		blockRegistry.register(block)
	}

	var itemRegistry: IForgeRegistry[Item] = _

	def registerItem(item: Item) {
		if (itemRegistry == null)
			return
		itemRegistry.register(item)
	}

	def registerItemForBlock(block: Block) {
		if (itemRegistry == null)
			return
		itemRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName))
	}

	@SubscribeEvent
	def registerBlocks(event: RegistryEvent.Register[Block]) {
		blockRegistry = event.getRegistry
		registerBlock(nether_essence_block)
		registerBlock(radiant_nether_essence_block)
		registerBlock(item_recovery_block)
	}

	@SubscribeEvent
	def registerItems(event: RegistryEvent.Register[Item]) {
		itemRegistry = event.getRegistry
		registerItem(nether_essence)
		registerItemForBlock(nether_essence_block)
		registerItemForBlock(radiant_nether_essence_block)
		registerItemForBlock(item_recovery_block)
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	def registerItemRenders(event: ModelRegistryEvent) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(nether_essence_block), 0, new ModelResourceLocation(NetherEssence.MODID + ":NetherDustBlock", "inventory"))
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(item_recovery_block), 0, new ModelResourceLocation(NetherEssence.MODID + ":item_recovery_block", "inventory"))
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(radiant_nether_essence_block), 0, new ModelResourceLocation(NetherEssence.MODID + ":radiant_nether_essence_block", "inventory"))
		ModelLoader.setCustomModelResourceLocation(nether_essence, 0, new ModelResourceLocation(NetherEssence.MODID + ":NetherDust", "inventory"))
	}
}
