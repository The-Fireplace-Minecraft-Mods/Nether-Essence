package the_fireplace.netheressence

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.Mod.Instance
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import the_fireplace.netheressence.blocks.BlockNetherEssence
import the_fireplace.netheressence.blocks.BlockRadiantNetherEssence
import the_fireplace.netheressence.fulcrumcompat.FulcrumCompat
import the_fireplace.netheressence.fulcrumcompat.IFulcrumCompat
import the_fireplace.netheressence.handlers.NetherEssenceFuelHandler
import NetherEssence._
//remove if not needed
import scala.collection.JavaConversions._

object NetherEssence {

  @Instance(NetherEssence.MODID)
  var instance: NetherEssence = _

  val MODID = "netheressence"

  val MODNAME = "Nether Essence"

  val VERSION = "2.1.1.0"

  val downloadURL = "http://goo.gl/zF0elV"

  val tabNetherEssence = new CreativeTabs("tabNetherEssence") {

    override def getTabIconItem(): Item = NetherEssence.nether_essence
  }

  val nether_essence = new Item().setCreativeTab(NetherEssence.tabNetherEssence)
    .setUnlocalizedName("NetherDust")

  val nether_essence_block = new BlockNetherEssence(Material.rock)

  val radiant_nether_essence_block = new BlockRadiantNetherEssence(Material.rock)
}

@Mod(modid = NetherEssence.MODID, name = NetherEssence.MODNAME, version = NetherEssence.VERSION)
class NetherEssence {

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) {
    GameRegistry.registerBlock(nether_essence_block, "NetherDustBlock")
    GameRegistry.registerItem(nether_essence, "NetherDust")
    GameRegistry.registerBlock(radiant_nether_essence_block, "radiant_nether_essence_block")
    GameRegistry.registerFuelHandler(new NetherEssenceFuelHandler())
    var c: IFulcrumCompat = null
    if (Loader.isModLoaded("fulcrum")) {
      c = new FulcrumCompat()
      c.register()
    }
  }

  @EventHandler
  def load(event: FMLInitializationEvent) {
    val dustStack = new ItemStack(nether_essence)
    val waterStack = new ItemStack(Items.water_bucket)
    val soulStack = new ItemStack(Blocks.soul_sand)
    val netherrackStack = new ItemStack(Blocks.netherrack)
    val coalStack = new ItemStack(Items.coal)
    val redStack = new ItemStack(Items.redstone)
    val potionStack = new ItemStack(Items.potionitem)
    val glowDustStack = new ItemStack(Items.glowstone_dust)
    GameRegistry.addRecipe(new ItemStack(nether_essence, 4), "xxx", "yzy", "xxx", 'x', netherrackStack, 
      'y', soulStack, 'z', waterStack)
    GameRegistry.addRecipe(new ItemStack(nether_essence, 4), "xyx", "xzx", "xyx", 'x', netherrackStack, 
      'y', soulStack, 'z', potionStack)
    GameRegistry.addRecipe(new ItemStack(nether_essence, 4), "xyx", "xzx", "xyx", 'x', netherrackStack, 
      'y', soulStack, 'z', waterStack)
    GameRegistry.addRecipe(new ItemStack(nether_essence, 4), "xxx", "yzy", "xxx", 'x', netherrackStack, 
      'y', soulStack, 'z', potionStack)
    GameRegistry.addRecipe(new ItemStack(Items.gunpowder), "drd", "rcr", "drd", 'd', dustStack, 'r', 
      redStack, 'c', coalStack)
    GameRegistry.addRecipe(new ItemStack(Items.gunpowder), "rdr", "dcd", "rdr", 'd', dustStack, 'r', 
      redStack, 'c', coalStack)
    GameRegistry.addRecipe(new ItemStack(nether_essence_block), "xxx", "x x", "xxx", 'x', dustStack)
    GameRegistry.addRecipe(new ItemStack(radiant_nether_essence_block), "xxx", "xgx", "xxx", 'x', dustStack, 
      'g', glowDustStack)
    GameRegistry.addShapelessRecipe(new ItemStack(nether_essence, 8), new ItemStack(nether_essence_block))
    GameRegistry.addShapelessRecipe(new ItemStack(nether_essence, 8), new ItemStack(radiant_nether_essence_block))
    if (event.getSide.isClient) {
      registerItemRenders()
    }
  }

  def registerItemRenders() {
    Minecraft.getMinecraft.getRenderItem.getItemModelMesher
      .register(net.minecraft.item.Item.getItemFromBlock(nether_essence_block), 0, new ModelResourceLocation(NetherEssence.MODID + ":NetherDustBlock", 
      "inventory"))
    Minecraft.getMinecraft.getRenderItem.getItemModelMesher
      .register(net.minecraft.item.Item.getItemFromBlock(radiant_nether_essence_block), 0, new ModelResourceLocation(NetherEssence.MODID + ":NetherDustBlock", 
      "inventory"))
    Minecraft.getMinecraft.getRenderItem.getItemModelMesher
      .register(nether_essence, 0, new ModelResourceLocation(NetherEssence.MODID + ":NetherDust", "inventory"))
  }
}
