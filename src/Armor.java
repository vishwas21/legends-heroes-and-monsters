/**
 * Armor class consists of  functionalities with respect to armors usage in the game
 */
public class Armor extends Item {

    private Integer damageReduction;

    public Armor(String name, Integer price, Integer level, Integer damageReduction) {
        super(name, price, level);
        this.setDamageReduction(damageReduction);
    }

    public Integer getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(Integer damageReduction) {
        this.damageReduction = damageReduction;
    }

    public static void printHeaders() {
        System.out.format("|  %-7s  |  %-20s  |  %-12s  |  %-12s  |  %-15s  |\n", "Sl. No.", "Name", "Cost", "Level", "Damage Reduction");
    }

    public void printData(int index) {
        System.out.format("|  %-7s  |  %-20s  |  %-12d  |  %-12d  |  %-15d  |\n", index, this.getName(), this.getPrice(), this.getLevel(), this.getDamageReduction());
    }

    public Armor clone() {
        return new Armor(this.getName(), this.getPrice(), this.getLevel(), this.getDamageReduction());
    }
}
