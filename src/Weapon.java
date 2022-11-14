public class Weapon extends Item {

    private Integer damage;
    private Integer numOfHands;

    public Weapon(String name, Integer price, Integer level, Integer damage, Integer numOfHands) {
        super(name, price, level);
        this.setDamage(damage);
        this.setNumOfHands(numOfHands);
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getNumOfHands() {
        return numOfHands;
    }

    public void setNumOfHands(Integer numOfHands) {
        this.numOfHands = numOfHands;
    }

    public static void printHeaders() {
        System.out.format("|  %-7s  |  %-20s  |  %-12s  |  %-12s  |  %-10s  |  %-10s  |\n", "Sl. No.", "Name", "Cost", "Level", "Damage", "Hands");
    }

    public void printData(int index) {
        System.out.format("|  %-7s  |  %-20s  |  %-12d  |  %-12d  |  %-10d  |  %-10d  |\n", index, this.getName(), this.getPrice(), this.getLevel(), this.getDamage(), this.getNumOfHands());
    }

    public Weapon clone() {
        return new Weapon(this.getName(), this.getPrice(), this.getLevel(), this.getDamage(), this.getNumOfHands());
    }
}
