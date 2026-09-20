package com.aetherteam.aetherii.attachment;

import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

/** Mutable entity state published once its tick has finished updating all fields. */
public abstract class SyncedEntityAttachment implements Cloneable {
    private boolean syncPending;

    public final void markDirty() {
        this.syncPending = true;
    }

    public static <T extends SyncedEntityAttachment> void flush(AttachmentTarget target, AttachmentType<T> type) {
        T current = target.getAttached(type);
        if (current == null || !((SyncedEntityAttachment) current).syncPending) return;
        SyncedEntityAttachment state = current;
        state.syncPending = false;
        // Preserve runtime-only fields as well as serialized fields. A codec round trip would lose them.
        // Publishing is deferred until the caller has finished mutating the current instance.
        target.setAttached(type, state.copyForSync());
    }

    @SuppressWarnings("unchecked")
    private <T extends SyncedEntityAttachment> T copyForSync() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new AssertionError(exception);
        }
    }
}
