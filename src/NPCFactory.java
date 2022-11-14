public class NPCFactory {
    public LegendsNPC createObject(NPCType npcType) throws IllegalStateException {
        if (npcType == NPCType.HERO) {
            return new LegendsHero();
        } else if (npcType == NPCType.MONSTER) {
            return new LegendsMonster();
        } else {
            throw new IllegalStateException("Invalid value passed to the NPC Factory!");
        }
    }
}
