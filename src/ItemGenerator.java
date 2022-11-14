import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ItemGenerator {
    public static ArrayList<Item> generateItem(ItemType itemType, ArrayList<Item> itemList, String nameOfFile) throws IOException {
        try(FileReader file = new FileReader("" + System.getProperty("user.dir") + "/src/constants/" + nameOfFile);
            BufferedReader reader = new BufferedReader(file);) {
            String singleLine = "";
            ItemFactory factory = new ItemFactory();
            Item tempObj;
            String[] inputArray;
            reader.readLine();

            while (true) {
                singleLine = reader.readLine();
                if (singleLine == null) {
                    break;
                }

                inputArray = singleLine.split("/");
                tempObj = factory.createObject(itemType, inputArray);
                itemList.add(tempObj);
            }
            return itemList;
        }
    }

}
