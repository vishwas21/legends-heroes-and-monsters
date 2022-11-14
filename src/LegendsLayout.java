/**
 * LegendsLayout Class: The LegendsLayout class implements the Layout Interface and has Legends specific functionality.
 *      This class instantiates the layout and also add the inaccessible and market sport for the legends game layout
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 5, 2022
 */


import java.util.Random;

public class LegendsLayout implements Layout {

    private Integer length;
    private Integer breadth;
    private Integer inaccessibleCellPercent;
    private Integer marketCellPercent;

    private Cell[][] gameLayout;

    public LegendsLayout(Integer squareSide) {
        this(squareSide, squareSide);
    }

    public LegendsLayout(Integer rectangleLength, Integer rectangleBreadth) {
        this.setLength(rectangleLength);
        this.setBreadth(rectangleBreadth);
        this.setInaccessibleCellPercent(20);
        this.setMarketCellPercent(25);
    }

    @Override
    public Integer getLength() {
        return length;
    }

    @Override
    public void setLength(Integer length) throws IllegalStateException {
        if (length > 0) {
            this.length = length;
        } else {
            throw new IllegalStateException("Invalid dimension for length of the layout");
        }
    }

    @Override
    public Integer getBreadth() {
        return breadth;
    }

    @Override
    public void setBreadth(Integer breadth) throws IllegalStateException {
        if (breadth > 0) {
            this.breadth = breadth;
        } else {
            throw new IllegalStateException("Invalid dimension for breadth of the layout");
        }
    }

    @Override
    public void createLayout() {
        gameLayout = new Cell[length][breadth];

        int positionX = 0;
        int positionY = 0;
        while(positionX < length) {
            positionY = 0;
            while (positionY < breadth) {
                gameLayout[positionX][positionY] = new LegendsCell(((positionX * breadth) + positionY + 1), positionX, positionY);
                positionY ++;
            }
            positionX ++;
        }
    }

    @Override
    public void initLayout() {
        this.createLayout();
        this.setInaccessibleSpaces();
        this.setMarketSpaces();
    }

    public Cell[][] getGameLayout() {
        return gameLayout;
    }

    @Override
    public Cell getCell(Integer positionX, Integer positionY) {
        return this.getGameLayout()[positionX][positionY];
    }

    public CellSpace getCellType(Integer positionX, Integer positionY) {
        return ((LegendsCell)this.getCell(positionX, positionY)).getCellType();
    }

    public void setSpaces(CellSpace cellType, Integer percentage) {
        int totalCells = this.length * this.breadth;
        int numCells = (totalCells * percentage ) / 100;
        Random randomIndex = new Random();
        int cellIndex = 0, positionX = -1, positionY = -1;

        while(numCells > 0) {
            cellIndex = randomIndex.nextInt(totalCells - 1) + 1;
            positionX = cellIndex / this.length;
            positionY = (int) Math.round(((1.0 * cellIndex / this.length) - positionX) * this.length);
            if (this.getCellType(positionX, positionY) == CellSpace.COMMON) {
                ((LegendsCell)this.getCell(positionX, positionY)).setCellType(cellType);
                numCells --;
            }
        }
    }

    public void setInaccessibleSpaces() {
        this.setSpaces(CellSpace.INACCESSIBLE, this.getInaccessibleCellPercent());
    }

    public void setMarketSpaces() {
        this.setSpaces(CellSpace.MARKET, this.getMarketCellPercent());
    }

    @Override
    public void displayLayout() {
        for (int indexI = 0; indexI < length; indexI ++) {
            for (int indexJ = 0; indexJ < breadth; indexJ ++) {
                System.out.print("+=================");
            }
            System.out.println("+");
            for (int indexJ = 0; indexJ < breadth; indexJ ++) {
                System.out.print("|                 ");
            }
            System.out.println("|");
            for (int indexJ = 0; indexJ < breadth; indexJ++) {
                if (gameLayout[indexI][indexJ].isTeamPresent()) {
                    System.out.print("|        " + TextColors.PURPLE + "H" + TextColors.RESET + "        ");
                } else {
                    System.out.print("|        " + CellSpace.spaceColor.get(this.getCellType(indexI, indexJ)) + CellSpace.spaceSymbol.get(this.getCellType(indexI, indexJ)) + TextColors.RESET + "        ");
                }
            }
            System.out.println("|");
            for (int indexJ = 0; indexJ < breadth; indexJ ++) {
                System.out.print("|                 ");
            }
            System.out.println("|");
        }
        for (int indexJ = 0; indexJ < breadth; indexJ++) {
            System.out.print("+=================");
        }
        System.out.println("+");

        System.out.println("\n" + CellSpace.spaceColor.get(CellSpace.INACCESSIBLE) + CellSpace.spaceSymbol.get(CellSpace.INACCESSIBLE) + TextColors.RESET + " - Inaccessible Spaces");
        System.out.println("" + CellSpace.spaceColor.get(CellSpace.MARKET) + CellSpace.spaceSymbol.get(CellSpace.MARKET) + TextColors.RESET + " - Market Spaces");
        System.out.println("" + TextColors.PURPLE + "H" + TextColors.RESET + " - Hero Team");
    }


    public Integer getInaccessibleCellPercent() {
        return inaccessibleCellPercent;
    }

    public void setInaccessibleCellPercent(Integer inaccessibleCellPercent) throws IllegalStateException {
        if (inaccessibleCellPercent > 25) {
            throw new IllegalStateException("The percent of Inaccessible spaces in the map cannot be more than 25%");
        }

        this.inaccessibleCellPercent = inaccessibleCellPercent;
    }

    public Integer getMarketCellPercent() {
        return marketCellPercent;
    }

    public void setMarketCellPercent(Integer marketCellPercent) throws IllegalStateException {
        if (marketCellPercent > 30) {
            throw new IllegalStateException("The percent of Market Spaces in the map cannot be more than 25%");
        }

        this.marketCellPercent = marketCellPercent;
    }
}
