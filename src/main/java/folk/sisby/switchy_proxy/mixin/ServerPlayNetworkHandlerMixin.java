package folk.sisby.switchy_proxy.mixin;

import folk.sisby.switchy_proxy.SwitchyProxy;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

	@Inject(method = "method_44900", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;getMessageDecorator()Lnet/minecraft/network/message/MessageDecorator;"))
	private void injectBeforeGetMessageDecoratorInLambda(ChatMessageC2SPacket chatMessageC2SPacket, Optional optional, CallbackInfo ci) {
		SwitchyProxy.beforeMessage(chatMessageC2SPacket.chatMessage(), ((ServerPlayNetworkHandler) (Object) this).getPlayer());
	}
}
