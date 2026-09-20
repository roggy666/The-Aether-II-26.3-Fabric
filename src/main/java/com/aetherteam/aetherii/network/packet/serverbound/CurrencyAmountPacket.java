package com.aetherteam.aetherii.network.packet.serverbound;

import com.aetherteam.aetherii.AetherII;
import com.aetherteam.aetherii.advancement.trigger.AetherIIAdvancementTriggers;
import com.aetherteam.aetherii.attachment.AetherIIDataAttachments;
import com.aetherteam.aetherii.inventory.menu.GuidebookEquipmentMenu;
import com.aetherteam.aetherii.inventory.CurrencyTransfer;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.aetherteam.aetherii.item.miscellaneous.CurrencyItem;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

/** Requests a wallet click; balances and carried items are determined by the server. */
public record CurrencyAmountPacket(int containerId, int button) implements CustomPacketPayload {
    public static final Type<CurrencyAmountPacket> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AetherII.MODID, "currency_transfer"));
    public static final StreamCodec<RegistryFriendlyByteBuf, CurrencyAmountPacket> STREAM_CODEC = CustomPacketPayload.codec(
            (packet, buf) -> { buf.writeVarInt(packet.containerId()); buf.writeByte(packet.button()); },
            buf -> new CurrencyAmountPacket(buf.readVarInt(), buf.readByte()));

    @Override
    public Type<CurrencyAmountPacket> type() { return TYPE; }

    public static void handleServer(CurrencyAmountPacket payload, ServerPlayer player) {
        if (!(player.containerMenu instanceof GuidebookEquipmentMenu menu)
                || menu.containerId != payload.containerId() || menu.getMoa() != null
                || !player.isAlive() || player.isSpectator()) return;
        var wallet = player.getAttachedOrCreate(AetherIIDataAttachments.CURRENCY);
        ItemStack carried = menu.getCarried();
        if (carried.isEmpty()) {
            int count = CurrencyTransfer.withdraw(wallet.getAmount(), payload.button());
            if (count == 0) return;
            wallet.setAmount(wallet.getAmount() - count);
            menu.setCarried(new ItemStack(AetherIIItems.GLINT_COIN, count));
        } else if (carried.getItem() instanceof CurrencyItem currency) {
            int count = CurrencyTransfer.deposit(wallet.getAmount(), carried.getCount(), currency.getCurrencyAmount(), payload.button());
            if (count == 0) return;
            wallet.setAmount(wallet.getAmount() + count * currency.getCurrencyAmount());
            ItemStack remainder = carried.copy();
            remainder.shrink(count);
            menu.setCarried(remainder);
            AetherIIAdvancementTriggers.CURRENCY.trigger(player, wallet.getAmount());
        } else return;
        menu.broadcastChanges();
    }
}
