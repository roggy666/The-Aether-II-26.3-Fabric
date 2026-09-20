package com.aetherteam.aetherii.attachment;

import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

/** Runs without a Minecraft world; the target models Fabric's identity/equality change detection. */
public final class AttachmentSyncRegressionTest {
    private static final class State extends SyncedEntityAttachment {
        int serializedValue;
        int runtimeTimer;
        boolean deferredLogin;
    }

    private static final class Target implements AttachmentTarget {
        Object value;
        int updates;

        @Override @SuppressWarnings("unchecked")
        public <T> T getAttached(AttachmentType<T> type) { return (T) value; }

        @Override @SuppressWarnings("unchecked")
        public <T> T setAttached(AttachmentType<T> type, T next) {
            Object previous = value;
            value = next;
            if (!java.util.Objects.equals(previous, next)) updates++;
            return (T) previous;
        }
    }

    public static void main(String[] args) {
        Target target = new Target();
        SyncedEntityAttachment.flush(target, null);
        check(target.updates == 0, "Absent state must not be created");
        State original = new State();
        target.value = original;
        SyncedEntityAttachment.flush(target, null);
        check(target.updates == 0, "Unchanged state must not sync");
        original.serializedValue = 150;
        original.markDirty();
        original.runtimeTimer = 37;
        original.deferredLogin = false;
        original.markDirty();
        original.serializedValue = 200;
        SyncedEntityAttachment.flush(target, null);
        State published = (State) target.value;
        check(published != original, "Fabric must receive a distinct object");
        check(published.serializedValue == 200, "Include changes after the initial dirty mark");
        check(published.runtimeTimer == 37 && !published.deferredLogin, "Preserve runtime-only fields");
        check(target.updates == 1, "Coalesce changes into one update");
        SyncedEntityAttachment.flush(target, null);
        check(target.updates == 1, "Published state must not remain dirty");
        published.runtimeTimer--;
        published.markDirty();
        SyncedEntityAttachment.flush(target, null);
        check(((State) target.value).runtimeTimer == 36 && target.updates == 2, "Subsequent ticks must keep progressing");
        verifyDiscoveryIsolation();
        verifyCurrencyTransfers();
        System.out.println("Attachment sync and discovery isolation regression checks passed");
    }

    private static void verifyCurrencyTransfers() {
        check(com.aetherteam.aetherii.inventory.CurrencyTransfer.withdraw(1, 1) == 1, "Right click must withdraw the last coin");
        check(com.aetherteam.aetherii.inventory.CurrencyTransfer.withdraw(3, 1) == 2, "Odd stacks round up");
        check(com.aetherteam.aetherii.inventory.CurrencyTransfer.withdraw(100, 0) == 64, "Withdrawal is limited to one stack");
        check(com.aetherteam.aetherii.inventory.CurrencyTransfer.withdraw(100, 1) == 32, "Right click withdraws half a stack");
        check(com.aetherteam.aetherii.inventory.CurrencyTransfer.withdraw(10, 5) == 0, "Invalid buttons cannot withdraw");
        check(com.aetherteam.aetherii.inventory.CurrencyTransfer.deposit(Integer.MAX_VALUE - 9, 64, 10, 0) == 0, "Deposits cannot overflow");
        check(com.aetherteam.aetherii.inventory.CurrencyTransfer.deposit(Integer.MAX_VALUE - 20, 64, 10, 0) == 2, "Only coins that fit are consumed");
        int balance = 150;
        int held = 0;
        for (int i = 0; i < 10; i++) {
            int withdrawn = com.aetherteam.aetherii.inventory.CurrencyTransfer.withdraw(balance, 0);
            balance -= withdrawn;
            held += withdrawn;
        }
        check(balance == 0 && held == 150, "Repeated withdrawals conserve currency");
        int deposited = com.aetherteam.aetherii.inventory.CurrencyTransfer.deposit(balance, held, 1, 0);
        check(balance + deposited == 150 && held - deposited == 0, "Deposit/withdraw round trip conserves currency");
    }

    private static void verifyDiscoveryIsolation() {
        var id = net.minecraft.resources.Identifier.fromNamespaceAndPath("aether_ii", "test_entry");
        var template = new com.aetherteam.aetherii.api.guidebook.GuidebookEntry(id, id, "name", "slot", java.util.Optional.empty(), "description");
        var firstPlayer = template.getValues();
        var secondPlayer = template.getValues();
        firstPlayer.get("name").reveal();
        firstPlayer.get("name").view();
        check(!secondPlayer.get("name").isVisible() && !secondPlayer.get("name").isViewed(), "Discovery flags must not leak between players");
        check(!template.getValues().get("name").isVisible(), "Registry templates must stay undiscovered");
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
