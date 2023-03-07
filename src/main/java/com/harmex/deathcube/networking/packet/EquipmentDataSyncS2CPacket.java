package com.harmex.deathcube.networking.packet;

import com.harmex.deathcube.util.capabilities.equipment.ClientEquipmentData;
import com.harmex.deathcube.world.item.custom.set.ArmorSet;
import com.harmex.deathcube.world.item.custom.set.ArmorSets;
import com.harmex.deathcube.world.item.custom.totem.Totems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class EquipmentDataSyncS2CPacket {
    private final Map<ArmorSet, Integer> equippedNumberForArmorSet;
    private final EnumMap<Totems, Boolean> equippedTotems;

    public EquipmentDataSyncS2CPacket(Map<ArmorSet, Integer> pEquippedNumberForArmorSet, EnumMap<Totems, Boolean> pIsTotemEquippedMap) {
        equippedNumberForArmorSet = pEquippedNumberForArmorSet;
        equippedTotems = pIsTotemEquippedMap;
    }

    public EquipmentDataSyncS2CPacket(FriendlyByteBuf buf) {
        equippedNumberForArmorSet = buf.readMap(friendlyByteBuf -> friendlyByteBuf.readEnum(ArmorSets.class), FriendlyByteBuf::readInt);
        equippedTotems = new EnumMap<>(Totems.class);
        equippedTotems.putAll(buf.readMap(friendlyByteBuf -> friendlyByteBuf.readEnum(Totems.class), FriendlyByteBuf::readBoolean));
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(equippedNumberForArmorSet, (friendlyByteBuf, armorSet) -> friendlyByteBuf.writeEnum((Enum<?>) armorSet), FriendlyByteBuf::writeInt);
        buf.writeMap(equippedTotems, FriendlyByteBuf::writeEnum, FriendlyByteBuf::writeBoolean);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientEquipmentData.set(equippedNumberForArmorSet, equippedTotems);
        });
        return true;
    }
}
