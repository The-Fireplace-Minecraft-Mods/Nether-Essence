package the_fireplace.netheressence;

import the_fireplace.fireplacecore.FireCoreBaseFile;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;

public class NetherEssenceHandler {
	@SubscribeEvent
	public void onPlayerJoinClient(final ClientConnectedToServerEvent event) {
		(new Thread() {
			public void run() {
				while (FMLClientHandler.instance().getClientPlayerEntity() == null)
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}

				NetherEssence.onPlayerJoinClient(FMLClientHandler.instance()
						.getClientPlayerEntity(), event);
			}
		}).start();

	}
}
