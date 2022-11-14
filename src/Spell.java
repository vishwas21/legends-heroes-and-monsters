public class Spell extends Item {

    private Integer damage;
    private Integer mpCost;
    private SpellType spellType;

    public Spell(String name, Integer price, Integer level, Integer damage, Integer mpCost, SpellType spellType) {
        super(name, price, level);
        this.setDamage(damage);
        this.setMpCost(mpCost);
        this.setSpellType(spellType);
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getMpCost() {
        return mpCost;
    }

    public void setMpCost(Integer mpCost) {
        this.mpCost = mpCost;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public void setSpellType(SpellType spellType) {
        this.spellType = spellType;
    }

    public static void printHeaders() {
        System.out.format("|  %-7s  |  %-20s  |  %-12s  |  %-12s  |  %-15s  |  %-10s  |  %-10s  |\n", "Sl. No.", "Name", "Cost", "Level", "Damage", "Mana Cost", "Type");
    }

    public void printData(int index) {
        System.out.format("|  %-7s  |  %-20s  |  %-12d  |  %-12d  |  %-15d  |  %-10d  | %-10s  |\n", index, this.getName(), this.getPrice(), this.getLevel(), this.getDamage(), this.getMpCost(), this.getSpellType().toString());
    }

    public Spell clone() {
        return new Spell(this.getName(), this.getPrice(), this.getLevel(), this.getDamage(), this.getMpCost(), this.getSpellType());
    }
}
