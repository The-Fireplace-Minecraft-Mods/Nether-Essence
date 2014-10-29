package the_fireplace.netheressence;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = NetherEssence.MOD_ID, name = NetherEssence.MOD_NAME, version = NetherEssence.MOD_VERSION)
public class NetherEssence
{
    @Instance(NetherEssence.MOD_ID)
    public static NetherEssence instance;
    
    public static final String MOD_ID = "netheressence";
    public static final String MOD_NAME = "Nether Essence";
    public static final String MOD_VERSION = "2.0.0";
    
	public static CreativeTabs tabNetherEssence = new CreativeTabs("tabNetherEssence"){
		@Override
		public Item getTabIconItem() {
			return NetherEssence.netherDust;
		}
	};
    public static Item netherDust = new NetherDust();
    public static Block netherDustBlock = new NetherDustBlock(Material.rock);
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
            GameRegistry.registerBlock(netherDustBlock, "NetherDustBlock");
            GameRegistry.registerItem(netherDust, "NetherDust");
            GameRegistry.registerFuelHandler(new NetherEssenceFuelHandler());
        }
        
        @EventHandler
        public void load(FMLInitializationEvent event) {
        	ItemStack dustStack = new ItemStack(netherDust);
        	ItemStack waterStack = new ItemStack(Items.water_bucket);
        	ItemStack soulStack = new ItemStack(Blocks.soul_sand);
        	ItemStack netherrackStack = new ItemStack(Blocks.netherrack);
        	ItemStack coalStack = new ItemStack(Items.coal);
        	ItemStack redStack = new ItemStack(Items.redstone);
        	ItemStack potionStack = new ItemStack(Items.potionitem);
        	
        	GameRegistry.addRecipe(new ItemStack(netherDust, 4), "xxx", "yzy", "xxx",
        	        'x', netherrackStack, 'y', soulStack, 'z', waterStack);
        	GameRegistry.addRecipe(new ItemStack(netherDust, 4), "xyx", "xzx", "xyx",
        	        'x', netherrackStack, 'y', soulStack, 'z', potionStack);
        	GameRegistry.addRecipe(new ItemStack(netherDust, 4), "xyx", "xzx", "xyx",
        	        'x', netherrackStack, 'y', soulStack, 'z', waterStack);
        	GameRegistry.addRecipe(new ItemStack(netherDust, 4), "xxx", "yzy", "xxx",
        	        'x', netherrackStack, 'y', soulStack, 'z', potionStack);
        	GameRegistry.addRecipe(new ItemStack(Items.gunpowder), "drd", "rcr", "drd",
        	        'd', dustStack, 'r', redStack, 'c', coalStack);
        	GameRegistry.addRecipe(new ItemStack(Items.gunpowder), "rdr", "dcd", "rdr",
        	        'd', dustStack, 'r', redStack, 'c', coalStack);
        	GameRegistry.addRecipe(new ItemStack(netherDustBlock), "xxx", "x x", "xxx",
        	        'x', dustStack);
        	GameRegistry.addShapelessRecipe(new ItemStack(netherDust, 8), new ItemStack(netherDustBlock));
        }
}