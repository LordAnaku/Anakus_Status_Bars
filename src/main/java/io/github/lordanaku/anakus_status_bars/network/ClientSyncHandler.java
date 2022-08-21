package io.github.lordanaku.anakus_status_bars.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientSyncHandler
{
	@Environment(EnvType.CLIENT)
	public static void init()
	{
		ClientPlayNetworking.registerGlobalReceiver(SyncHandler.EXHAUSTION_SYNC, (client, handler, buf, responseSender) -> {
			float exhaustion = buf.readFloat();
			client.execute(() -> {
				assert client.player != null;
				client.player.getHungerManager().setExhaustion(exhaustion);
			});
		});
		ClientPlayNetworking.registerGlobalReceiver(SyncHandler.SATURATION_SYNC, (client, handler, buf, responseSender) -> {
			float saturation = buf.readFloat();
			client.execute(() -> {
				assert client.player != null;
				client.player.getHungerManager().setSaturationLevel(saturation);
			});
		});
	}
}
