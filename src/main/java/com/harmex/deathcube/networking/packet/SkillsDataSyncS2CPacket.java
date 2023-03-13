package com.harmex.deathcube.networking.packet;

import com.harmex.deathcube.util.capabilities.skills.ClientSkillsData;
import com.harmex.deathcube.world.skill.SkillData;
import com.harmex.deathcube.world.skill.Skills;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class SkillsDataSyncS2CPacket {
    private final EnumMap<Skills, SkillData> skills;

    public SkillsDataSyncS2CPacket(EnumMap<Skills, SkillData> pSkills) {
        skills = pSkills;
    }

    public SkillsDataSyncS2CPacket(FriendlyByteBuf buf) {
        Map<Skills, SkillData> temp = buf.readMap(friendlyByteBuf -> friendlyByteBuf.readEnum(Skills.class), friendlyByteBuf -> {
            SkillData skillData = new SkillData(friendlyByteBuf.readEnum(Skills.class), 0, 0, 0);
            skillData.setLevel(friendlyByteBuf.readInt());
            skillData.setExperience(friendlyByteBuf.readFloat());
            skillData.setTotalExperience(friendlyByteBuf.readFloat());
            skillData.setRequiredExperience(friendlyByteBuf.readFloat());
            return skillData;
        });

        skills = new EnumMap<>(temp);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(skills, FriendlyByteBuf::writeEnum, (friendlyByteBuf, skillData) -> {
            friendlyByteBuf.writeEnum((Enum<?>) skillData.getSkill());
            friendlyByteBuf.writeInt(skillData.getLevel());
            friendlyByteBuf.writeFloat(skillData.getExperience());
            friendlyByteBuf.writeFloat(skillData.getTotalExperience());
            friendlyByteBuf.writeFloat(skillData.getRequiredExperience());
        });
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientSkillsData.set(skills);
        });
        return true;
    }
}
