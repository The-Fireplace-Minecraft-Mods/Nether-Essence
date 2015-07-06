package the_fireplace.netheressence.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import the_fireplace.netheressence.NetherEssence;

public class BlockNetherEssence extends Block {
    public BlockNetherEssence (Material material) 
    {
            super(material);
            setHardness(1.0F);
            setLightLevel(0.8f);
            setUnlocalizedName("NetherDustBlock");
            setCreativeTab(NetherEssence.tabNetherEssence);
            setHarvestLevel("pickaxe", 0);
    }
}
