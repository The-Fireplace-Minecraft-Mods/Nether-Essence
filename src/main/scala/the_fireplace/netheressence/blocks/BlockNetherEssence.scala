package the_fireplace.netheressence.blocks

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import the_fireplace.netheressence.NetherEssence
//remove if not needed
import scala.collection.JavaConversions._

class BlockNetherEssence(material: Material) extends Block(material) {

  setHardness(1.0F)

  setLightLevel(0.8f)

  setUnlocalizedName("NetherDustBlock")

  setCreativeTab(NetherEssence.tabNetherEssence)

  setHarvestLevel("pickaxe", 0)
}
