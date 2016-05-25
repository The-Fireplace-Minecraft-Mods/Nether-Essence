package the_fireplace.netheressence

import java.lang.{Character => JChar}

import net.minecraft.block.material.Material
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{Item, ItemStack}
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.registry.GameRegistry
import the_fireplace.netheressence.blocks.{BlockNetherEssence, BlockRadiantNetherEssence}
import the_fireplace.netheressence.handlers.NetherEssenceFuelHandler

@Mod(modid = NetherEssence.MODID, name = NetherEssence.MODNAME, modLanguage = "scala", updateJSON = "http://caterpillar.bitnamiapp.com/jsons/netheressence.json")
object NetherEssence {
	final val MODID = "netheressence"
	final val MODNAME = "Nether Essence"
	var VERSION = ""
	final val curseCode = "238223-nether-essence"

	val tabNetherEssence: CreativeTabs = new CreativeTabs("tabNetherEssence") {
		override def getTabIconItem: Item = NetherEssence.nether_essence
	}

	val nether_essence = new Item().setCreativeTab(NetherEssence.tabNetherEssence)
		.setUnlocalizedName("NetherDust")

	val nether_essence_block = new BlockNetherEssence(Material.ROCK)

	val radiant_nether_essence_block = new BlockRadiantNetherEssence(Material.ROCK)

	@EventHandler
	def preInit(event: FMLPreInitializationEvent) {
		val version: Array[String] = event.getModMetadata.version.split("\\.")
		if (version(3).equals("BUILDNUMBER")) {
			//Dev environment
			VERSION = event.getModMetadata.version.replace("BUILDNUMBER", "9001")
		} else {
			//Released build
			VERSION = event.getModMetadata.version
		}
		GameRegistry.registerBlock(nether_essence_block, "NetherDustBlock")
		GameRegistry.registerItem(nether_essence, "NetherDust")
		GameRegistry.registerBlock(radiant_nether_essence_block, "radiant_nether_essence_block")
		GameRegistry.registerFuelHandler(new NetherEssenceFuelHandler())
	}

	@EventHandler
	def load(event: FMLInitializationEvent) {
		val dustStack = new ItemStack(nether_essence)
		val dustStack4 = new ItemStack(nether_essence, 4)
		val dustStack8 = new ItemStack(nether_essence, 8)
		val lavaStack = new ItemStack(Items.LAVA_BUCKET)
		val soulStack = new ItemStack(Blocks.SOUL_SAND)
		val netherrackStack = new ItemStack(Blocks.NETHERRACK)
		val coalStack = new ItemStack(Items.COAL)
		val redStack = new ItemStack(Items.REDSTONE)
		val glowDustStack = new ItemStack(Items.GLOWSTONE_DUST)
		val gunpowderStack = new ItemStack(Items.GUNPOWDER)
		val netherEssenceBlockStack = new ItemStack(nether_essence_block)
		val radNetherEssenceBlockStack = new ItemStack(radiant_nether_essence_block)
		GameRegistry.addRecipe(dustStack4, Array("xxx", "yzy", "xxx", 'x': JChar, netherrackStack, 'y': JChar, soulStack, 'z': JChar, lavaStack): _*)
		GameRegistry.addRecipe(dustStack4, Array("xyx", "xzx", "xyx", 'x': JChar, netherrackStack, 'y': JChar, soulStack, 'z': JChar, lavaStack): _*)
		GameRegistry.addRecipe(gunpowderStack, Array("drd", "rcr", "drd", 'd': JChar, dustStack, 'r': JChar, redStack, 'c': JChar, coalStack): _*)
		GameRegistry.addRecipe(gunpowderStack, Array("rdr", "dcd", "rdr", 'd': JChar, dustStack, 'r': JChar, redStack, 'c': JChar, coalStack): _*)
		GameRegistry.addRecipe(netherEssenceBlockStack, Array("xxx", "x x", "xxx", 'x': JChar, dustStack): _*)
		GameRegistry.addRecipe(radNetherEssenceBlockStack, Array("xxx", "xgx", "xxx", 'x': JChar, dustStack, 'g': JChar, glowDustStack): _*)
		GameRegistry.addShapelessRecipe(dustStack8, netherEssenceBlockStack)
		GameRegistry.addShapelessRecipe(dustStack8, radNetherEssenceBlockStack)
		if (event.getSide.isClient) {
			registerItemRenders()
		}
	}

	def registerItemRenders() {
		Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(net.minecraft.item.Item.getItemFromBlock(nether_essence_block), 0, new ModelResourceLocation(NetherEssence.MODID + ":NetherDustBlock", "inventory"))
		Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(net.minecraft.item.Item.getItemFromBlock(radiant_nether_essence_block), 0, new ModelResourceLocation(NetherEssence.MODID + ":radiant_nether_essence_block", "inventory"))
		Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(nether_essence, 0, new ModelResourceLocation(NetherEssence.MODID + ":NetherDust", "inventory"))
	}
}
