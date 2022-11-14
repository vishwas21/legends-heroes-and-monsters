/**
 * Potion class consists of  functionalities with respect to armors usage in the game
 */
public class Potion extends Item {

    private Integer effectAmount;
    private PotionType potionType;


    public Potion(String name, Integer price, Integer level, Integer effectAmount, PotionType potionType) {
        super(name, price, level);
        this.setEffectAmount(effectAmount);
        this.setPotionType(potionType);
    }

    public Integer getEffectAmount() {
        return effectAmount;
    }

    public void setEffectAmount(Integer effectAmount) {
        this.effectAmount = effectAmount;
    }

    public PotionType getPotionType() {
        return potionType;
    }

    public void setPotionType(PotionType potionType) {
        this.potionType = potionType;
    }

    public static void printHeaders() {
        System.out.format("|  %-7s  |  %-20s  |  %-12s  |  %-12s  |  %-17s  |  %-10s  |\n", "Sl. No.", "Name", "Cost", "Level", "Attribute Increase", "Attribute");
    }

    public void printData(int index) {
        System.out.format("|  %-7s  |  %-20s  |  %-12d  |  %-12d  |  %-17d  |  %-10s  |\n", index, this.getName(), this.getPrice(), this.getLevel(), this.getEffectAmount(), this.getPotionType().toString());
    }

    public Potion clone() {
        return new Potion(this.getName(), this.getPrice(), this.getLevel(), this.getEffectAmount(), this.getPotionType());
    }
}
