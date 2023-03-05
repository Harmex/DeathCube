package com.harmex.deathcube.networking.packet;

import com.harmex.deathcube.util.capabilities.mana.ClientManaData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaDataSyncS2CPacket {
    private final float manaLevel;

    public ManaDataSyncS2CPacket(float pManaLevel) {
        manaLevel = pManaLevel;
    }

    public ManaDataSyncS2CPacket(FriendlyByteBuf buf) {
        manaLevel = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(manaLevel);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientManaData.set(manaLevel);
        });
        return true;
    }
}
