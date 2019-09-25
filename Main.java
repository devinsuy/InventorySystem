import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    /**
     * Allows for menu system entry with handled input validation
     * @param upperBound The max acceptable value for the particular menu
     * @param input The scanner used since closing System.in closes entire input stream
     *              for when function is called again
     * @return userChoice The user's menu selection choice
     */
    private static int intValidation(int upperBound, Scanner input){
        int userChoice;
        String choiceString;
        try{
            choiceString = input.next();
            userChoice = Integer.parseInt(choiceString); // May throw NumberFormatException
            if(userChoice > upperBound || userChoice < 1){
                throw new NumberFormatException();
            }
        }
        catch(NumberFormatException e){
            System.out.print("ERROR please make a valid selection: ");
            return intValidation(upperBound, input);
        }
        return userChoice;
    }

    /**
     * Recursive input validation for decimal values
     * @param input The scanner used since closing System.in closes entire input stream
     *              for when function is called again
     * @return A valid float value
     */
    private static float priceValidation(Scanner input){
        float price = 0;
        String priceString;
        try{
            priceString = input.next();
            price = Float.parseFloat(priceString); // May throw NumberFormatException
            if(price <= 0){
                throw new NumberFormatException();
            }
        }
        catch(NumberFormatException e){
            System.out.print("ERROR please enter a valid price: ");
            return priceValidation(input);
        }
        return price;
    }

    /**
     * Takes a string and returns it underlined with hyphens
     * @param text The string to be underlined
     * @return Underlined version of the string
     */
    private static String underlineText(String text){
        String underLine = "";
        for (int i = text.length(); i > 0; i--){
            underLine = underLine + "-";
        }
        return text + "\n" + underLine;
    }

    private static void sortMenu(Inventory inv, Scanner input){
        int sortMenu;
        System.out.println(underlineText("Sort the inventory by: "));
        System.out.println("   1. Item #");
        System.out.println("   2. Brand");
        System.out.println("   3. Price");
        System.out.println("   4. Quantity");
        System.out.println("   5. Item Type");
        System.out.println("   6. Cancel");
        System.out.print("\nPlease enter your selection: ");
        sortMenu = intValidation(6, input);

        switch(sortMenu){
            case 1:
                inv.getInventory().sort(Product.byItemNum);
                System.out.println("Inventory has been sorted by Item #");
                break;
            case 2:
                inv.getInventory().sort(Product.byBrand);
                System.out.println("Inventory has been sorted by brand");
                break;
            case 3:
                inv.getInventory().sort(Product.byPrice);
                System.out.println("Inventory has been sorted by price");
                break;
            case 4:
                inv.getInventory().sort(Product.byQuantity);
                System.out.println("Inventory has been sorted by quantity");
                break;
            case 5:
                inv.getInventory().sort(Product.byItemType);
                System.out.println("Inventory has been sorted by item type");
                break;
            case 6:
                System.out.println("Sorting cancelled");
                break;
        }
        System.out.println("\nReturning to menu . . .");
        inventoryMenu(inv, input);
    }

    /**
     * Submenu for modifying products in inventory
     * @param inv The inventory object to be modified
     * @param input The scanner used since closing System.in closes entire input stream
     *              for when function is called again
     */
    private static void inventoryMenu(Inventory inv, Scanner input){
        int inventoryMenu;
        System.out.println(underlineText("\nInventory"));
        System.out.println("   1. Display inventory");
        System.out.println("   2. Change the price of an item");
        System.out.println("   3. Modify the quantity of an item");
        System.out.println("   4. Delete an item");
        System.out.println("   5. Sort the inventory");
        System.out.println("   6. View items low in stock");
        System.out.println("   7. Main Menu");
        System.out.print("\nPlease enter your selection: ");
        inventoryMenu = intValidation(7, input);

        int itemIndex, confirm;
        Product productToModify;
        switch(inventoryMenu){
            case 1:
                System.out.println("\n" + underlineText("Displaying Inventory"));
                inv.displayInventory(inv.getInventory());
                inventoryMenu(inv, input);
                break;
            case 2:
            case 3:
                System.out.print("Enter the Item # of the product you wish to modify: ");
                itemIndex = intValidation(inv.getInventory().size(), input);
                ArrayList<Product> temp = inv.getInventory(); // Used to refer to same index displayed
                temp.sort(Product.byItemNum);
                productToModify = temp.get(itemIndex - 1);
                System.out.println("The item you've chosen to modify is: " + productToModify.displayInfo(false));
                if(inventoryMenu == 2){
                    float newPrice;
                    System.out.println("\nItem #" + productToModify.getItemNum() + " is currently listed at $" + productToModify.getPrice());
                    System.out.print("Enter the new price of this item: ");
                    newPrice = priceValidation(input);
                    System.out.println("Price for item #" + productToModify.getItemNum() + " will be updated to $" + newPrice);
                    System.out.println("   1. Confirm");
                    System.out.println("   2. Cancel");
                    System.out.print("\nPlease enter your selection: ");
                    confirm = intValidation(2, input);
                    if(confirm == 1){
                        productToModify.setPrice(newPrice);
                        System.out.println("Price of item #" + productToModify.getItemNum() + " has been updated");
                    }
                    else{
                        System.out.println("Price modification was cancelled");
                    }

                    System.out.println("\nReturning to menu . . .");
                    inventoryMenu(inv, input);
                }
                else{
                    int newQuantity;
                    System.out.println("\nItem #" + productToModify.getItemNum() + " currently has a quantity of " + productToModify.getQuantity());
                    System.out.print("Enter the new quantity of the item: ");
                    newQuantity = intValidation(99999,input);
                    System.out.println("Quantity of item #" + productToModify.getItemNum() + " will be updated to " + newQuantity);
                    System.out.println("   1. Confirm");
                    System.out.println("   2. Cancel");
                    System.out.print("\nPlease enter your selection: ");
                    confirm = intValidation(2, input);
                    if(confirm == 1){
                      productToModify.setQuantity(newQuantity);
                      System.out.println("Quantity of item #" + productToModify.getItemNum() + " has been updated");
                    }
                    else{
                        System.out.println("Quantity modification was cancelled");
                    }
                    System.out.println("\nReturning to menu . . .");
                    inventoryMenu(inv, input);
                }
                break;
                //FIXME shift item numbers after deletion and decrement itemNum
            case 4:
                int delete;
                System.out.print("Enter the Item # of the product you wish to delete: ");
                itemIndex = intValidation(inv.getInventory().size(), input);
                System.out.println("The item you've chosen to delete is: " + inv.getInventory().get(itemIndex - 1).displayInfo(false));
                System.out.println("   1. Confirm");
                System.out.println("   2. Cancel");
                System.out.print("\nPlease enter your selection: ");
                delete = intValidation(2, input);
                if(delete == 1){
                    inv.getInventory().remove(itemIndex - 1);
                    // Updates item numbers after an item is deleted
                    for(int i = (itemIndex - 1); i < inv.getInventory().size(); i++){
                        inv.getInventory().get(i).decrementItemNum();
                    }
                    Inventory.decrementNumItems(); // There is one less item entry, so decrement

                    System.out.println("Item #" + itemIndex + " has been successfully removed");
                }
                else{
                    System.out.println("Item deletion cancelled");
                }
                System.out.println("\nReturning to menu . . .");
                inventoryMenu(inv, input);
                break;
            case 5: //FIXME maybe add sort by display size
                sortMenu(inv, input);
                break;
            case 6:
                ArrayList<Product> lowStock = new ArrayList<Product>();
                for(Product p : inv.getInventory()){
                    if(p.getQuantity() <= 5){
                        lowStock.add(p);
                    }
                }
                if(lowStock.size() == 0){
                    System.out.println("No items are currently low in stock");
                }
                else{
                    lowStock.sort(Product.byQuantity);
                    System.out.println("\n" + underlineText("Items Low In Stock"));
                    Inventory.displayInventory(lowStock);
                }
                System.out.println("\nReturning to menu . . .");
                inventoryMenu(inv, input);
                break;
            case 7:
                System.out.println("\nReturning to main menu . . .\n");
                mainMenu(inv);
                break;
        }
    }
    /**
     * Displays the main menu and its prompts
     * @param inv The inventory object modified and added to
     */
    private static Inventory mainMenu(Inventory inv){
        Scanner input = new Scanner(System.in);
        int menuChoice; Product p;

        System.out.println(underlineText("Main Menu"));
        System.out.println("\t1. Enter a new product into inventory");
        System.out.println("\t2. Modify an existing product");
        System.out.println("\t3. View/Modify current inventory of products");
        System.out.println("\t4. Exit.\n");
        System.out.print("Please enter your selection: ");

        menuChoice = intValidation(4, input);
        switch(menuChoice){
            case 1:
                int productType;
                String productName, productSpecs, productModel, productQuantity;
                float productPrice;

                System.out.println(underlineText("\nEnter a new product"));
                System.out.println("The product is a: ");
                System.out.println("\t1. Television");
                System.out.println("\t2. Stereo System");
                System.out.print("\nPlease enter your selection: ");
                productType = intValidation(2, input);

                System.out.print("Enter the manufacturer of the product: ");
                productName = input.next();
                if(productType == 1){
                    System.out.print("Enter the size(inches) of the display: ");
                    productSpecs = input.next() + "\"";
                }
                else{productSpecs = "N/A";}
                System.out.print("Enter the product's model number: ");
                productModel = input.next();
                System.out.print("Enter the selling price of the product: ");
                productPrice = priceValidation(input);
                System.out.print("Enter the quantity of the product in stock: ");
                productQuantity = input.next();

                if(productSpecs.equals("N/A")){ // If item is a stereo
                    p = new Product(productName, productModel, productPrice, Integer.valueOf(productQuantity));
                }
                else{ // Item is a tv
                    p = new Product(productName, productSpecs, productModel, productPrice, Integer.valueOf(productQuantity));
                }
                inv.addItem(p);

                System.out.println("\n" + underlineText("New Product Entry: " + p.displayInfo(false) ));
                System.out.println("The item has been added to your inventory of products");
                System.out.println("\nReturning to main menu . . .\n");
                mainMenu(inv);
                break;
            case 2:
                break;
            case 3:
                inv.displayInventory(inv.getInventory());
                inventoryMenu(inv, input);
                break;
            case 4:
                System.out.println("\nExiting program . . . Goodbye!");
                break;
        }
        return inv;
    }

    public static void main(String[] args) {
        Inventory inv = new Inventory();
        mainMenu(inv);
    }
}
