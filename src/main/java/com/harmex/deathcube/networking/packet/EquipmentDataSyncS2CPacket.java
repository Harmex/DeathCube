package com.harmex.deathcube.networking.packet;

import com.harmex.deathcube.util.capabilities.equipment.ClientEquippedSetsData;
import com.harmex.deathcube.world.item.custom.ArmorSet;
import com.harmex.deathcube.world.item.custom.ArmorSets;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.Map;
import java.util.function.Supplier;

public class EquipmentDataSyncS2CPacket {
    private final Map<ArmorSet, Integer> equippedNumberForArmorSet;

    public EquipmentDataSyncS2CPacket(Map<ArmorSet, Integer> pEquippedNumberForArmorSet) {
        equippedNumberForArmorSet = pEquippedNumberForArmorSet;
    }

    public EquipmentDataSyncS2CPacket(FriendlyByteBuf buf) {
        equippedNumberForArmorSet = buf.readMap(friendlyByteBuf -> friendlyByteBuf.readEnum(ArmorSets.class), FriendlyByteBuf::readInt);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(equippedNumberForArmorSet, (friendlyByteBuf, armorSet) -> friendlyByteBuf.writeEnum((Enum<?>) armorSet), FriendlyByteBuf::writeInt);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientEquippedSetsData.set(equippedNumberForArmorSet);
        });
        return true;
    }
}
