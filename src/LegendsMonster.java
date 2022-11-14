public class LegendsMonster extends LegendsNPC {

    private Integer baseDamage;
    private Integer defense;
    private Integer dodge;

    public LegendsMonster() {}

    public LegendsMonster(String name, Integer level, Integer maxHealthPoints, Integer baseDamage, Integer defense, Integer dodge) {
        super(name, level, maxHealthPoints);
        this.setBaseDamage(baseDamage);
        this.setDefense(defense);
        this.setDodge(dodge);
    }

    public void init(String name, Integer level, Integer maxHealthPoints, Integer baseDamage, Integer defense, Integer dodge) {
        super.init(name, level, maxHealthPoints);
        this.setBaseDamage(baseDamage);
        this.setDefense(defense);
        this.setDodge(dodge);
    }

    public Integer getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(Integer baseDamage) {
        this.baseDamage = baseDamage;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getDodge() {
        return dodge;
    }

    public void setDodge(Integer dodge) {
        this.dodge = dodge;
    }

    public static void printHeaders() {
        System.out.format("|  %-7s  |  %-20s  |  %-8s  |  %-10s  |  %-15s  |  %-10s  |  %-10s  |\n", "Sl. No.", "Name", "Level", "Hit Points", "Damage", "Defense", "Dodge");
    }

    public void printData(int index) {
        System.out.format("|  %-7d  |  %-20s  |  %-8d  |  %-10d  |  %-15s  |  %-10d  |  %-10d  |\n", index, this.getName(), this.getLevel(), this.getCurrentHitPoints(), this.getBaseDamage(), this.getDefense(), this.getDodge());
    }
}
