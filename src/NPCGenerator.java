import java.io.*;
import java.util.ArrayList;

public class NPCGenerator {
    public static ArrayList<Pawn> generatePawn(NPCType pawnType, ArrayList<Pawn> npcList, String nameOfFile) throws IOException {
        try(FileReader file = new FileReader("" + System.getProperty("user.dir") + "/src/constants/" + nameOfFile);
            BufferedReader reader = new BufferedReader(file);) {
            String singleLine = "";
            NPCFactory factory = new NPCFactory();
            Pawn tempObj;
            String[] inputArray;
            reader.readLine();

            while (true) {
                singleLine = reader.readLine();
                if (singleLine == null) {
                    break;
                }
                tempObj = factory.createObject(pawnType);
                inputArray = singleLine.split("/");
                if (pawnType == NPCType.HERO) {
                    ((LegendsHero)tempObj).init(inputArray[0], 1, 100, Integer.parseInt(inputArray[6]),
                            Integer.parseInt(inputArray[1]), Integer.parseInt(inputArray[2]), Integer.parseInt(inputArray[4]),
                            Integer.parseInt(inputArray[3]), Integer.parseInt(inputArray[5]));
                } else if (pawnType == NPCType.MONSTER) {
                    ((LegendsMonster)tempObj).init(inputArray[0], Integer.parseInt(inputArray[1]),
                            Integer.parseInt(inputArray[1]) * 100, Integer.parseInt(inputArray[2]),
                            Integer.parseInt(inputArray[3]), Integer.parseInt(inputArray[4]));
                }
                npcList.add(tempObj);
            }

            return npcList;
        }
    }
}
