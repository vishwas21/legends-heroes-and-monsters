/**
 * Item Factory : Design pattern to create items.
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 5, 2022
 */
public class ItemFactory {

    public Item createObject(ItemType itemType, String ...itemDetails) {
        if (itemType == ItemType.WEAPON) {
            return new Weapon(itemDetails[0], Integer.parseInt(itemDetails[1]), Integer.parseInt(itemDetails[2]), Integer.parseInt(itemDetails[3]), Integer.parseInt(itemDetails[4]));
        } else if (itemType == ItemType.ARMOR) {
            return new Armor(itemDetails[0], Integer.parseInt(itemDetails[1]), Integer.parseInt(itemDetails[2]), Integer.parseInt(itemDetails[3]));
        } else if (itemType == ItemType.POTION) {
            PotionType potionType;
            if (itemDetails[4].equalsIgnoreCase("health")) {
                potionType = PotionType.HEALTH;
            } else if (itemDetails[4].equalsIgnoreCase("magic") || itemDetails[4].equalsIgnoreCase("mana")) {
                potionType = PotionType.MAGIC;
            } else if (itemDetails[4].equalsIgnoreCase("strength")) {
                potionType = PotionType.STRENGTH;
            } else if (itemDetails[4].equalsIgnoreCase("dexterity")) {
                potionType = PotionType.DEXTERITY;
            } else if (itemDetails[4].equalsIgnoreCase("agility")) {
                potionType = PotionType.AGILITY;
            } else {
                potionType = PotionType.ALL;
            }
            return new Potion(itemDetails[0], Integer.parseInt(itemDetails[1]), Integer.parseInt(itemDetails[2]), Integer.parseInt(itemDetails[3]), potionType);
        } else if (itemType == ItemType.SPELL) {
            SpellType spellType;
            if (itemDetails[5].equals("fire")) {
                spellType = SpellType.FIRE;
            } else if (itemDetails[5].equals("ice")) {
                spellType = SpellType.ICE;
            } else if (itemDetails[5].equals("lightning")) {
                spellType = SpellType.LIGHTNING;
            } else {
                spellType = SpellType.ALL;
            }
            return new Spell(itemDetails[0], Integer.parseInt(itemDetails[1]), Integer.parseInt(itemDetails[2]), Integer.parseInt(itemDetails[3]), Integer.parseInt(itemDetails[4]), spellType);
        } else {
            throw new IllegalStateException("Invalid type of item provided for factory!");
        }
    }
}
