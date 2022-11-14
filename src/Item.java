/**
 * Item Abstract Class: This class holds the default details of all the items and its functionality.
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 5, 2022
 */

public abstract class Item {

    private String name;
    private Integer price;
    private Integer level;

    public Item(String name, Integer price, Integer level) {
        this.setName(name);
        this.setPrice(price);
        this.setLevel(level);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
