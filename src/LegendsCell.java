/**
 * LegendsCell Class: This class inherits all the features of the Cell Super class and also store the type of the cell which is required for legends game
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 5, 2022
 */

public class LegendsCell extends Cell {

    private CellSpace cellType;

    public LegendsCell(Integer unitPosition, Integer unitIndexX, Integer unitIndexY) {
        super(unitPosition, unitIndexX, unitIndexY);
        this.setCellType(CellSpace.COMMON);
    }

    public CellSpace getCellType() {
        return cellType;
    }

    public void setCellType(CellSpace cellType) {
        this.cellType = cellType;
    }
}
