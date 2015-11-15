package the_fireplace.netheressence.blocks

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import the_fireplace.netheressence.NetherEssence
//remove if not needed
import scala.collection.JavaConversions._

class BlockRadiantNetherEssence(material: Material) extends Block(material) {

  setHardness(1.0F)

  setLightLevel(1.0F)

  setUnlocalizedName("radiant_nether_essence_block")

  setCreativeTab(NetherEssence.tabNetherEssence)

  setHarvestLevel("pickaxe", 0)
}
