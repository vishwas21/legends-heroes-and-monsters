/**
 * Cell Class: This class depicts the basic unit in all of the board or layout based games. It helps store the position and piece/character/pawn in the cell.
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 5, 2022
 */

public class Cell {
    private Integer unitPosition;
    private Integer unitPositionX;
    private Integer unitIndexY;
    private Team team;


    public Cell(Integer unitPosition, Integer unitPositionX, Integer unitIndexY) {
        this.setUnitPosition(unitPosition);
        this.setUnitPositionX(unitPositionX);
        this.setUnitIndexY(unitIndexY);
    }

    public void setUnitPosition(Integer unitPosition) {
        this.unitPosition = unitPosition;
    }

    public Integer getUnitPosition() {
        return this.unitPosition;
    }

    public void setUnitPositionX(Integer unitPositionX) {
        this.unitPositionX = unitPositionX;
    }

    public Integer getUnitPositionX() {
        return this.unitPositionX;
    }

    public void setUnitIndexY(Integer unitIndexY) {
        this.unitIndexY = unitIndexY;
    }

    public Integer getUnitIndexY() {
        return this.unitIndexY;
    }

    public void placeTeam(Team team) {
        this.team = team;
    }

    public void removeTeam(){
        this.team = null;
    }

    public Team getTeam() { return this.team; }

    public Boolean isTeamPresent() {
        if (this.team == null) { return false; }
        return true;
    }
}
