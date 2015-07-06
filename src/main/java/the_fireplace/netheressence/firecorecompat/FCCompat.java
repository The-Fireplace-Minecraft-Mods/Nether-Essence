package the_fireplace.netheressence.firecorecompat;

import the_fireplace.fireplacecore.api.FCAPI;
import the_fireplace.fireplacecore.math.VersionMath;
import the_fireplace.netheressence.NetherEssence;

public class FCCompat implements IFCCompat {

	@Override
	public void register() {
		FCAPI.registerModToVersionChecker(NetherEssence.MODNAME, NetherEssence.VERSION, VersionMath.getVersionFor("https://dl.dropboxusercontent.com/s/h8n4cewp5l7jxea/prerelease.version?dl=0"), VersionMath.getVersionFor("https://dl.dropboxusercontent.com/s/sl0t934yt14cc85/release.version?dl=0"), NetherEssence.downloadURL, NetherEssence.MODID);
	}

}
