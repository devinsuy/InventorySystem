import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inventory {
    private ArrayList<Product> inventory;
    private static int numItems = 0; // Number of items in inventory
    /**
     * Initializes inventory list and loads it with pre existing products
     */
    public Inventory(){
        inventory = new ArrayList<Product>();
        loadInventory();
        for(Product p : inventory) {
            numItems++;
            p.setItemNum(numItems);
        }
    }

    /**
     * Takes a string and returns it underlined with hyphens
     * @param text The string to be underlined
     * @return Underlined version of the string
     */
    static String underlineText(String text){
        String underLine = "";
        for (int i = text.length(); i > 0; i--){
            underLine = underLine + "-";
        }
        return text + "\n" + underLine;
    }

    public ArrayList<Product> getInventory(){
        return inventory;
    }

    /**
     * Adds an item to the inventory list
     * @param p The product to add
     */
    void addItem(Product p){
        inventory.add(p);
    }

    /**
     * Takes an item number and modifies the quantity of the corresponding product
     * @param itemNum The item number of the product
     * @param q The quantity to set the item to
     */
    void setQuantity(int itemNum, int q){
        inventory.get(itemNum - 1).setQuantity(q);
    }
    /**
     * Formats and displays inventory
     */
    static void displayInventory(ArrayList<Product> inv){
        String productInfo;
        for(Product p : inv){
            if(p.getItemType()){ // If item is a stereo
                productInfo = String.format("%-13s%s%15s%-22sListed At: $%-15.2fQuantity: %d", p.getBrand(), "Stereo", "Model: ", p.getModel(), p.getPrice(), p.getQuantity());
            }
            else{
                productInfo = String.format("%-13s%s TV%15s%-22sListed At: $%-15.2fQuantity: %d", p.getBrand(), p.getSpec(), "Model: ", p.getModel(), p.getPrice(), p.getQuantity());
            }

            System.out.printf("\tItem #%03d%-7s%s\n", p.getItemNum(),".", productInfo);
        }
    }

    /**
     * Initializes pre-existing products with info from a specified .txt file,
     * loading them into the inventory list
     */
    void loadInventory() {
        File inventoryList = new File("C:\\Users\\Devin\\Desktop\\Inventory.txt");
        Pattern tv = Pattern.compile("^([\\w]+)\\s+([\\d]+\\\")\\s+\\#+(\\S+)\\s+\\$(\\d+\\.\\d+)");
        Pattern stereo = Pattern.compile("^(\\w+)\\s+\\#(\\w+[\\-|\\w]+)\\s+\\$(\\d+\\.\\d+)");
        Matcher m; Scanner input; String currentLine; boolean loadTV = true;
        try {
            input = new Scanner(inventoryList);
            while (input.hasNextLine()) {
                currentLine = input.nextLine();
                if (currentLine.equals("Stereo")) { // Stopper line in .txt to signal end of TV info
                    loadTV = false;
                }
                if (loadTV) {
                    m = tv.matcher(currentLine);
                    while (m.find()) { // Initializes TV's from inventory list
                        inventory.add(new Product(m.group(1), m.group(2), m.group(3), Float.valueOf(m.group(4))));
                    }
                } else {
                    m = stereo.matcher(currentLine);
                    while (m.find()) { // Initializes stereos from inventory list
                        inventory.add(new Product(m.group(1), m.group(2), Float.valueOf(m.group(3))));
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException fnf) {System.out.println("Invalid directory specified, terminating . . .");}
    }

    public static void incrementNumItems(){numItems++;}
    public static void decrementNumItems(){numItems--;}
}
