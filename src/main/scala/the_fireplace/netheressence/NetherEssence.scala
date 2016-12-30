package the_fireplace.netheressence

import java.lang.{Character => JChar}

import net.minecraft.block.material.Material
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{Item, ItemBlock, ItemStack}
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.registry.GameRegistry
import the_fireplace.netheressence.blocks.{BlockItemRecovery, BlockNetherEssence, BlockRadiantNetherEssence}
import the_fireplace.netheressence.handlers.{ForgeEvents, NetherEssenceFuelHandler}

@Mod(modid = NetherEssence.MODID, name = NetherEssence.MODNAME, modLanguage = "scala", updateJSON = "http://thefireplace.bitnamiapp.com/jsons/netheressence.json", acceptedMinecraftVersions="[1.11,)")
object NetherEssence {
	final val MODID = "netheressence"
	final val MODNAME = "Nether Essence"

	val tabNetherEssence: CreativeTabs = new CreativeTabs("tabNetherEssence") {
		override def getTabIconItem: ItemStack = new ItemStack(NetherEssence.nether_essence)
	}

	val nether_essence = new Item().setCreativeTab(NetherEssence.tabNetherEssence)
		.setUnlocalizedName("NetherDust")

	val nether_essence_block = new BlockNetherEssence(Material.ROCK)

	val radiant_nether_essence_block = new BlockRadiantNetherEssence(Material.ROCK)

	val item_recovery_block = new BlockItemRecovery(Material.ROCK)

	@EventHandler
	def preInit(event: FMLPreInitializationEvent) {
		GameRegistry.register(nether_essence_block.setRegistryName("NetherDustBlock"))
		GameRegistry.register(new ItemBlock(nether_essence_block).setRegistryName("NetherDustBlock"))
		GameRegistry.register(item_recovery_block.setRegistryName("item_recovery_block"))
		GameRegistry.register(new ItemBlock(item_recovery_block).setRegistryName("item_recovery_block"))
		GameRegistry.register(nether_essence.setRegistryName("NetherDust"))
		GameRegistry.register(radiant_nether_essence_block.setRegistryName("radiant_nether_essence_block"))
		GameRegistry.register(new ItemBlock(radiant_nether_essence_block).setRegistryName("radiant_nether_essence_block"))
		GameRegistry.registerFuelHandler(new NetherEssenceFuelHandler)
		MinecraftForge.EVENT_BUS.register(new ForgeEvents)
		if (event.getSide.isClient) {
			registerItemRenders()
		}
	}

	@EventHandler
	def load(event: FMLInitializationEvent) {
		val dustStack = new ItemStack(nether_essence)
		val dustStack4 = new ItemStack(nether_essence, 4)
		val dustStack8 = new ItemStack(nether_essence, 8)
		val lavaStack = new ItemStack(Blocks.MAGMA)
		val soulStack = new ItemStack(Blocks.SOUL_SAND)
		val netherrackStack = new ItemStack(Blocks.NETHERRACK)
		val coalStack = new ItemStack(Items.COAL)
		val redStack = new ItemStack(Items.REDSTONE)
		val glowDustStack = new ItemStack(Items.GLOWSTONE_DUST)
		val gunpowderStack = new ItemStack(Items.GUNPOWDER)
		val netherEssenceBlockStack = new ItemStack(nether_essence_block)
		val radNetherEssenceBlockStack = new ItemStack(radiant_nether_essence_block)
		val recoveryBlockStack2 = new ItemStack(item_recovery_block, 2)
		GameRegistry.addRecipe(dustStack4, Array("xxx", "yzy", "xxx", 'x': JChar, netherrackStack, 'y': JChar, soulStack, 'z': JChar, lavaStack): _*)
		GameRegistry.addRecipe(dustStack4, Array("xyx", "xzx", "xyx", 'x': JChar, netherrackStack, 'y': JChar, soulStack, 'z': JChar, lavaStack): _*)
		GameRegistry.addRecipe(gunpowderStack, Array("drd", "rcr", "drd", 'd': JChar, dustStack, 'r': JChar, redStack, 'c': JChar, coalStack): _*)
		GameRegistry.addRecipe(gunpowderStack, Array("rdr", "dcd", "rdr", 'd': JChar, dustStack, 'r': JChar, redStack, 'c': JChar, coalStack): _*)
		GameRegistry.addRecipe(netherEssenceBlockStack, Array("xxx", "x x", "xxx", 'x': JChar, dustStack): _*)
		GameRegistry.addRecipe(radNetherEssenceBlockStack, Array("xxx", "xgx", "xxx", 'x': JChar, dustStack, 'g': JChar, glowDustStack): _*)
		GameRegistry.addRecipe(recoveryBlockStack2, Array(" b ", "beb", " b ", 'b':JChar, radNetherEssenceBlockStack, 'e':JChar, soulStack): _*)
		GameRegistry.addShapelessRecipe(dustStack8, netherEssenceBlockStack)
		GameRegistry.addShapelessRecipe(dustStack8, radNetherEssenceBlockStack)
	}

	def registerItemRenders() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(nether_essence_block), 0, new ModelResourceLocation(NetherEssence.MODID + ":NetherDustBlock", "inventory"))
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(item_recovery_block), 0, new ModelResourceLocation(NetherEssence.MODID + ":item_recovery_block", "inventory"))
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(radiant_nether_essence_block), 0, new ModelResourceLocation(NetherEssence.MODID + ":radiant_nether_essence_block", "inventory"))
		ModelLoader.setCustomModelResourceLocation(nether_essence, 0, new ModelResourceLocation(NetherEssence.MODID + ":NetherDust", "inventory"))
	}
}
