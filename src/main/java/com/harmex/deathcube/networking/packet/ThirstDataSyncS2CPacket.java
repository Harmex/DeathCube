package com.harmex.deathcube.networking.packet;

import com.harmex.deathcube.util.capabilities.thirst.ClientThirstData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ThirstDataSyncS2CPacket {
    private final int thirstLevel;
    private final float saturationLevel;
    private final float exhaustionLevel;

    public ThirstDataSyncS2CPacket(int pThirst, float pSaturationLevel, float pExhaustionLevel) {
        thirstLevel = pThirst;
        saturationLevel = pSaturationLevel;
        exhaustionLevel = pExhaustionLevel;
    }

    public ThirstDataSyncS2CPacket(FriendlyByteBuf buf) {
        thirstLevel = buf.readInt();
        saturationLevel = buf.readFloat();
        exhaustionLevel = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(thirstLevel);
        buf.writeFloat(saturationLevel);
        buf.writeFloat(exhaustionLevel);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientThirstData.set(thirstLevel, saturationLevel, exhaustionLevel);
        });
        return true;
    }
}
