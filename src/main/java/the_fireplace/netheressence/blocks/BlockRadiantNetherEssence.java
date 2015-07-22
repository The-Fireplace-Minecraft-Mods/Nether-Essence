package the_fireplace.netheressence.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import the_fireplace.netheressence.NetherEssence;

public class BlockRadiantNetherEssence extends Block {
	public BlockRadiantNetherEssence (Material material)
	{
		super(material);
		setHardness(1.0F);
		setLightLevel(1.0F);
		setUnlocalizedName("radiant_nether_essence_block");
		setCreativeTab(NetherEssence.tabNetherEssence);
		setHarvestLevel("pickaxe", 0);
	}
}
