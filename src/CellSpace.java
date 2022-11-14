/**
 * CellSpace Enum: This Enum stores the different type of cells which can be present in the Legends game and their respective Color and Symbol.
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 5, 2022
 */

import java.util.HashMap;

public enum CellSpace {
    INACCESSIBLE,
    MARKET,
    COMMON;

    public static HashMap<CellSpace, String> spaceColor;
    public static HashMap<CellSpace, String> spaceSymbol;

    static {
        spaceColor = new HashMap<>(3);
        spaceColor.put(CellSpace.INACCESSIBLE, TextColors.RED);
        spaceColor.put(CellSpace.MARKET, TextColors.BLUE);
        spaceColor.put(CellSpace.COMMON, TextColors.GREEN);

        spaceSymbol = new HashMap<>(3);
        spaceSymbol.put(CellSpace.INACCESSIBLE, "X");
        spaceSymbol.put(CellSpace.MARKET, "M");
        spaceSymbol.put(CellSpace.COMMON, " ");
    }
}
