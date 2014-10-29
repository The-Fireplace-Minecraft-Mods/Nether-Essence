package the_fireplace.netheressence;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class NetherDustBlock extends Block {
    public NetherDustBlock (Material material) 
    {
            super(material);
            setBlockTextureName("netheressence:netherDustBlock");
            setHardness(1.0F);
            setLightLevel(0.8f);
            setBlockName("NetherDustBlock");
            setCreativeTab(NetherEssence.tabNetherEssence);
            setHarvestLevel("pickaxe", 0);
    }
}
