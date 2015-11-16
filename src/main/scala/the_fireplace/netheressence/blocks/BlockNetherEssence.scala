package the_fireplace.netheressence.blocks

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import the_fireplace.netheressence.NetherEssence

class BlockNetherEssence(material: Material) extends Block(material) {

  setHardness(1.0F)

  setLightLevel(0.6f)

  setUnlocalizedName("NetherDustBlock")

  setCreativeTab(NetherEssence.tabNetherEssence)

  setHarvestLevel("pickaxe", 0)
}
