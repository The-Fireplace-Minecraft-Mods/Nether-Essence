package the_fireplace.netheressence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.fireplacecore.FireCoreBaseFile;

@Mod(modid = NetherEssence.MODID, name = NetherEssence.MODNAME, version = NetherEssence.VERSION)
public class NetherEssence
{
	@Instance(NetherEssence.MODID)
	public static NetherEssence instance;

	public static final String MODID = "netheressence";
	public static final String MODNAME = "Nether Essence";
	public static final String VERSION = "2.0.2.0";

	private static int updateNotification;
	private static String releaseVersion;
	private static String prereleaseVersion;
	private static final String downloadURL = "http://goo.gl/zF0elV";
	public static NBTTagCompound update = new NBTTagCompound();

	public static CreativeTabs tabNetherEssence = new CreativeTabs("tabNetherEssence"){
		@Override
		public Item getTabIconItem() {
			return NetherEssence.netherDust;
		}
	};
	public static final Item netherDust = new NetherDust();
	public static final Block netherDustBlock = new NetherDustBlock(Material.rock);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GameRegistry.registerBlock(netherDustBlock, "NetherDustBlock");
		GameRegistry.registerItem(netherDust, "NetherDust");
		GameRegistry.registerFuelHandler(new NetherEssenceFuelHandler());
		retriveCurrentVersions();
		FireCoreBaseFile.addUpdateInfo(update, MODNAME, VERSION, prereleaseVersion, releaseVersion, downloadURL, MODID);
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
		if(event.getSide().isClient()){
			registerItemRenders();
		}
	}
	public void registerItemRenders(){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(net.minecraft.item.Item.getItemFromBlock(netherDustBlock), 0, 
				new ModelResourceLocation(NetherEssence.MODID+":NetherDustBlock", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(netherDust, 0, 
				new ModelResourceLocation(NetherEssence.MODID+":NetherDust", "inventory"));
	}
	/**
	 * This method is client side called when a player joins the game. Both for
	 * a server or a single player world.
	 */
	public static void onPlayerJoinClient(EntityPlayer player,
			ClientConnectedToServerEvent event) {
		updateNotification=FireCoreBaseFile.getUpdateNotification();
		if (!prereleaseVersion.equals("")
				&& !releaseVersion.equals("")) {
			switch (updateNotification) {
			case 0:
				if (isHigherVersion(VERSION, releaseVersion) && isHigherVersion(prereleaseVersion, releaseVersion)) {
					FireCoreBaseFile.sendClientUpdateNotification(player, MODNAME, VERSION, downloadURL);
				}else if(isHigherVersion(VERSION, prereleaseVersion)){
					FireCoreBaseFile.sendClientUpdateNotification(player, MODNAME, VERSION, downloadURL);
				}

				break;
			case 1:
				if (isHigherVersion(VERSION, releaseVersion)) {
					FireCoreBaseFile.sendClientUpdateNotification(player, MODNAME, VERSION, downloadURL);
				}
				break;
			case 2:

				break;
			}
		}
	}

	/**
	 * Checks if the new version is higher than the current one
	 * 
	 * @param currentVersion
	 *            The version which is considered current
	 * @param newVersion
	 *            The version which is considered new
	 * @return Whether the new version is higher than the current one or not
	 */
	private static boolean isHigherVersion(String currentVersion,
			String newVersion) {
		final int[] _current = splitVersion(currentVersion);
		final int[] _new = splitVersion(newVersion);

		return (_current[0] < _new[0])
				|| ((_current[0] == _new[0]) && (_current[1] < _new[1]))
				|| ((_current[0] == _new[0]) && (_current[1] == _new[1]) && (_current[2] < _new[2]))
				|| ((_current[0] == _new[0]) && (_current[1] == _new[1]) && (_current[2] == _new[2]) && (_current[3] < _new[3]));
	}

	/**
	 * Splits a version in its number components (Format ".\d+\.\d+\.\d+.*" )
	 * 
	 * @param Version
	 *            The version to be splitted (Format is important!
	 * @return The numeric version components as an integer array
	 */
	private static int[] splitVersion(String Version) {
		final String[] tmp = Version.split("\\.");
		final int size = tmp.length;
		final int out[] = new int[size];

		for (int i = 0; i < size; i++) {
			out[i] = Integer.parseInt(tmp[i]);
		}

		return out;
	}

	/**
	 * Retrieves what the latest version is from Dropbox
	 */
	private static void retriveCurrentVersions() {
		try {
			releaseVersion = get_content(new URL(
					"https://dl.dropboxusercontent.com/s/sl0t934yt14cc85/release.version?dl=0")
			.openConnection());

			prereleaseVersion = get_content(new URL(
					"https://dl.dropboxusercontent.com/s/h8n4cewp5l7jxea/prerelease.version?dl=0")
			.openConnection());

		} catch (final MalformedURLException e) {
			System.out.println("Malformed URL Exception");
			releaseVersion = "";
			prereleaseVersion = "";
		} catch (final IOException e) {
			System.out.println("IO Exception");
			releaseVersion = "";
			prereleaseVersion = "";
		}
	}

	private static String get_content(URLConnection con) throws IOException {
		String output = "";

		if (con != null) {
			final BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			String input;

			while ((input = br.readLine()) != null) {
				output = output + input;
			}
			br.close();
		}

		return output;
	}
}