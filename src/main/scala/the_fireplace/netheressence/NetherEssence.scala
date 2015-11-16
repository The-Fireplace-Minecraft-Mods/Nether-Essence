package the_fireplace.netheressence

import net.minecraft.block.material.Material
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import the_fireplace.netheressence.blocks.BlockNetherEssence
import the_fireplace.netheressence.blocks.BlockRadiantNetherEssence
import the_fireplace.netheressence.handlers.NetherEssenceFuelHandler
import java.lang.{Character => JChar}

@Mod(modid=NetherEssence.MODID, name=NetherEssence.MODNAME, version=NetherEssence.VERSION, acceptedMinecraftVersions="1.8", modLanguage="scala")
object NetherEssence {

  final val MODID = "netheressence"

  final val MODNAME = "Nether Essence"

  final val VERSION = "2.2.0.1"

  final val downloadURL = "http://goo.gl/zF0elV"

  val tabNetherEssence:CreativeTabs = new CreativeTabs("tabNetherEssence") {
    override def getTabIconItem: Item = NetherEssence.nether_essence
  }

  final val LATEST = "https://dl.dropboxusercontent.com/s/sl0t934yt14cc85/release.version?dl=0"

  val nether_essence = new Item().setCreativeTab(NetherEssence.tabNetherEssence)
    .setUnlocalizedName("NetherDust")

  val nether_essence_block = new BlockNetherEssence(Material.rock)

  val radiant_nether_essence_block = new BlockRadiantNetherEssence(Material.rock)

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) {
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
    val waterStack = new ItemStack(Items.water_bucket)
    val soulStack = new ItemStack(Blocks.soul_sand)
    val netherrackStack = new ItemStack(Blocks.netherrack)
    val coalStack = new ItemStack(Items.coal)
    val redStack = new ItemStack(Items.redstone)
    val potionStack = new ItemStack(Items.potionitem)
    val glowDustStack = new ItemStack(Items.glowstone_dust)
    val gunpowderStack = new ItemStack(Items.gunpowder)
    val netherEssenceBlockStack = new ItemStack(nether_essence_block)
    val radNetherEssenceBLockStack = new ItemStack(radiant_nether_essence_block)
    GameRegistry.addRecipe(dustStack4, Array("xxx", "yzy", "xxx", 'x':JChar, netherrackStack, 'y':JChar, soulStack, 'z':JChar, waterStack):_*)
    GameRegistry.addRecipe(dustStack4, Array("xyx", "xzx", "xyx", 'x':JChar, netherrackStack, 'y':JChar, soulStack, 'z':JChar, potionStack):_*)
    GameRegistry.addRecipe(dustStack4, Array("xyx", "xzx", "xyx", 'x':JChar, netherrackStack, 'y':JChar, soulStack, 'z':JChar, waterStack):_*)
    GameRegistry.addRecipe(dustStack4, Array("xxx", "yzy", "xxx", 'x':JChar, netherrackStack, 'y':JChar, soulStack, 'z':JChar, potionStack):_*)
    GameRegistry.addRecipe(gunpowderStack, Array("drd", "rcr", "drd", 'd':JChar, dustStack, 'r':JChar, redStack, 'c':JChar, coalStack):_*)
    GameRegistry.addRecipe(gunpowderStack, Array("rdr", "dcd", "rdr", 'd':JChar, dustStack, 'r':JChar, redStack, 'c':JChar, coalStack):_*)
    GameRegistry.addRecipe(netherEssenceBlockStack, Array("xxx", "x x", "xxx", 'x':JChar, dustStack):_*)
    GameRegistry.addRecipe(radNetherEssenceBLockStack, Array("xxx", "xgx", "xxx", 'x':JChar, dustStack, 'g':JChar, glowDustStack):_*)
    GameRegistry.addShapelessRecipe(dustStack8, netherEssenceBlockStack)
    GameRegistry.addShapelessRecipe(dustStack8, radNetherEssenceBLockStack)
    if (event.getSide.isClient) {
      registerItemRenders()
    }
  }

  def registerItemRenders() {
    Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(net.minecraft.item.Item.getItemFromBlock(nether_essence_block), 0, new ModelResourceLocation(NetherEssence.MODID + ":NetherDustBlock", "inventory"))
    Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(net.minecraft.item.Item.getItemFromBlock(radiant_nether_essence_block), 0, new ModelResourceLocation(NetherEssence.MODID + ":NetherDustBlock", "inventory"))
    Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(nether_essence, 0, new ModelResourceLocation(NetherEssence.MODID + ":NetherDust", "inventory"))
  }
}
