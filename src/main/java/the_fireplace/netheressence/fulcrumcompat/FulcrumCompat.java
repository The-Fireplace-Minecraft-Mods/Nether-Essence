package the_fireplace.netheressence.fulcrumcompat;

import the_fireplace.fulcrum.api.API;
import the_fireplace.fulcrum.math.VersionMath;
import the_fireplace.netheressence.NetherEssence;

public class FulcrumCompat implements IFulcrumCompat {

	@Override
	public void register() {
		API.registerModToVersionChecker(NetherEssence.MODNAME, NetherEssence.VERSION, VersionMath.getVersionFor("https://dl.dropboxusercontent.com/s/h8n4cewp5l7jxea/prerelease.version?dl=0"), VersionMath.getVersionFor("https://dl.dropboxusercontent.com/s/sl0t934yt14cc85/release.version?dl=0"), NetherEssence.downloadURL, NetherEssence.MODID);
	}

}
