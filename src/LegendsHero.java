/**
 * Armor class consists of  functionalities with respect to armors usage in the game
 */
import java.util.ArrayList;
import java.util.HashMap;

public class LegendsHero extends LegendsNPC {

    private Integer experiencePoints;
    private Integer maxMagicPoints;
    private Integer currentMagicPoints;
    private Integer strength;
    private Integer dexterity;
    private Integer agility;
    private Integer gold;
    private Boolean didFaint;
    private HashMap<ItemType, Item> itemsEquipped;
    private HashMap<ItemType, ArrayList<Item>> itemInventory;

    public LegendsHero() { }

    public LegendsHero(String name, Integer level, Integer maxHealthPoints, Integer experiencePoints, Integer maxMagicPoints, Integer strength, Integer dexterity, Integer agility, Integer gold) {
        super(name, level, maxHealthPoints);
        this.setExperiencePoints(experiencePoints);
        this.setMaxMagicPoints(maxMagicPoints);
        this.setCurrentMagicPoints(maxMagicPoints);
        this.setStrength(strength);
        this.setDexterity(dexterity);
        this.setAgility(agility);
        this.setGold(gold);
        this.initItemInventory();
        this.setFaint(Boolean.FALSE);
        this.initItemsEquipped();
    }

    public void init(String name, Integer level, Integer maxHealthPoints, Integer experiencePoints, Integer maxMagicPoints, Integer strength, Integer dexterity, Integer agility, Integer gold) {
        super.init(name, level, maxHealthPoints);
        this.setExperiencePoints(experiencePoints);
        this.setMaxMagicPoints(maxMagicPoints);
        this.setCurrentMagicPoints(maxMagicPoints);
        this.setStrength(strength);
        this.setDexterity(dexterity);
        this.setAgility(agility);
        this.setGold(gold);
        this.initItemInventory();
        this.setFaint(Boolean.FALSE);
        this.initItemsEquipped();
    }

    public Integer getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(Integer experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public Integer getMaxMagicPoints() {
        return maxMagicPoints;
    }

    public void setMaxMagicPoints(Integer maxMagicPoints) {
        this.maxMagicPoints = maxMagicPoints;
    }

    public Integer getCurrentMagicPoints() {
        return currentMagicPoints;
    }

    public void setCurrentMagicPoints(Integer currentMagicPoints) {
        this.currentMagicPoints = currentMagicPoints;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public HashMap<ItemType, ArrayList<Item>> getItemInventory() {
        return itemInventory;
    }

    public void initItemInventory() {
        this.itemInventory = new HashMap<>();
        this.itemInventory.put(ItemType.WEAPON, new ArrayList<>());
        this.itemInventory.put(ItemType.ARMOR, new ArrayList<>());
        this.itemInventory.put(ItemType.POTION, new ArrayList<>());
        this.itemInventory.put(ItemType.SPELL, new ArrayList<>());
    }

    public void initItemsEquipped() {
        this.itemsEquipped = new HashMap<>();
        this.itemsEquipped.put(ItemType.WEAPON, null);
        this.itemsEquipped.put(ItemType.ARMOR, null);
    }

    public Boolean isItemEquipped(ItemType itemType) {
        return this.itemsEquipped.get(itemType) != null;
    }

    public void setItemsEquipped(ItemType itemType, Item item) {
        this.itemsEquipped.put(itemType, item);
    }

    public Item getItemEquipped(ItemType itemType) {
        return this.itemsEquipped.get(itemType);
    }

    public void removeItemEquipped(ItemType itemType) {
        this.itemsEquipped.put(itemType, null);
    }

    public Boolean getDidFaint() {
        return didFaint;
    }

    public void setFaint(Boolean didFaint) {
        this.didFaint = didFaint;
    }

    public static void printHeaders() {
        System.out.format("|  %-7s  |  %-20s  |  %-15s  |  %-12s  |  %-10s  |  %-10s  |  %-10s  |  %-10s  |  %-12s  |\n", "Sl. No.", "Name", "Hit Points", "Magic Power", "Strength", "Agility", "Dexterity", "Gold", "Experience");
    }

    public void printData(int index) {
        System.out.format("|  %-7d  |  %-20s  |  %-15d  |  %-12d  |  %-10d  |  %-10d  |  %-10d  |  %-10d  |  %-12d  |\n", index, this.getName(), this.getCurrentHitPoints(), this.getCurrentMagicPoints(), this.getStrength(), this.getAgility(), this.getDexterity(), this.getGold(), this.getExperiencePoints());
    }
}
