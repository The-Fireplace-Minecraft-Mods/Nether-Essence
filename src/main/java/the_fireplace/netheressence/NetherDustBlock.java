package the_fireplace.netheressence;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;

public class NetherDustBlock extends Block {
    public NetherDustBlock (Material material) 
    {
            super(material);
            setHardness(1.0F);
            setLightLevel(0.8f);
            setUnlocalizedName("NetherDustBlock");
            setCreativeTab(NetherEssence.tabNetherEssence);
            setHarvestLevel("pickaxe", 0);
    }
}
