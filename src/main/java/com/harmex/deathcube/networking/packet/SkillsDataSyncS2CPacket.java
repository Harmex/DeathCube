package com.harmex.deathcube.networking.packet;

import com.harmex.deathcube.capabilities.skills.ClientSkillsData;
import com.harmex.deathcube.world.skill.Skill;
import com.harmex.deathcube.world.skill.SkillProperties;
import com.harmex.deathcube.world.skill.Skills;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.Map;
import java.util.function.Supplier;

public class SkillsDataSyncS2CPacket {
    private final Map<Skill, SkillProperties> skillsLVL;

    public SkillsDataSyncS2CPacket(Map<Skill, SkillProperties> pSkillsLVL) {
        skillsLVL = pSkillsLVL;
    }

    public SkillsDataSyncS2CPacket(FriendlyByteBuf buf) {
        skillsLVL = buf.readMap(friendlyByteBuf -> friendlyByteBuf.readEnum(Skills.class), friendlyByteBuf ->
                new SkillProperties(friendlyByteBuf.readInt(), friendlyByteBuf.readFloat()));
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeMap(skillsLVL, (friendlyByteBuf, skill) -> friendlyByteBuf.writeEnum((Enum<?>) skill), (friendlyByteBuf, skillLVL) -> {
            friendlyByteBuf.writeInt(skillLVL.getLvl());
            friendlyByteBuf.writeFloat(skillLVL.getXp());
        });
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientSkillsData.set(skillsLVL);
        });
        return true;
    }
}
