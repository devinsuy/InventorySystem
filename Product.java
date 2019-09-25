import java.util.Collections;
import java.util.Comparator;

// Product is currently used to represent BOTH Stereo and TV
public class Product{
    private String manufacturer;
    private String model;
    private String specification;
    private float listPrice;
    private int quantity;
    private int itemNum;
    private boolean isStereo;

    // Inheritance not used for TV or Stereo until difference in functionality is defined
    // outside of stereo not having a specification (Display size in this case)

    /**
     * TV object constructor
     * @param brand - (Samsung, LG, ...)
     * @param spec - Size of display in inches
     * @param model - Associated model number
     * @param price - List price
     */
    public Product(String brand, String spec, String model, float price){ // TV constructor
        manufacturer = brand;
        specification = spec;
        this.model = model;
        listPrice = price;
        quantity = 6;
        isStereo = false;
    }

    /**
     * TV object constructor
     * @param brand - (Samsung, LG, ...)
     * @param spec - Size of display in inches
     * @param model - Associated model number
     * @param price - List price
     * @param quantity - Quantity of the product on hand
     */
    public Product(String brand, String spec, String model, float price, int quantity){ // TV constructor
        manufacturer = brand;
        specification = spec;
        this.model = model;
        listPrice = price;
        this.quantity = quantity;
        isStereo = false;
    }

    /**
     * Stereo object constructor
     * @param brand - (JBL, Yamaha, ...)
     * @param model - Associated model number
     * @param price - List price
     */
    public Product(String brand, String model, float price) { // Stereo constructor
        manufacturer = brand;
        specification = "N/A";
        this.model = model;
        listPrice = price;
        quantity = 1;
        isStereo = true;
    }
    /**
     * Stereo object constructor
     * @param brand - (JBL, Yamaha, ...)
     * @param model - Associated model number
     * @param price - List price
     * @param quantity - Quantity of the product on hand
     */
    public Product(String brand, String model, float price, int quantity) { // Stereo constructor
        manufacturer = brand;
        specification = "N/A";
        this.model = model;
        listPrice = price;
        this.quantity = quantity;
        isStereo = true;
    }

    /**
     * Formats the product's information, printing dependent on argument
     * @param print Prints the formatted info afterward if true
     * @return The formatted string
     */
    String displayInfo(boolean print){
        String productInfo;
        if(isStereo) {
            productInfo = String.format("%-13s%s%15s%-22sListed At: $%-15.2fQuantity: %d", getBrand(), "Stereo", "Model: ", getModel(), getPrice(), getQuantity());
        }
        else{
            productInfo  = String.format("%-13s%s TV%15s%-22sListed At: $%-15.2fQuantity: %d", getBrand(), getSpec(), "Model: ", getModel(), getPrice(), getQuantity());
        }
        if(print){
            System.out.println(productInfo);
        }
        return productInfo;
    }
    public static Comparator<Product> byItemNum = Comparator.comparing(Product::getItemNum);
    public static Comparator<Product> byBrand = Comparator.comparing(Product::getBrand);
    public static Comparator<Product> byPrice = Comparator.comparing(Product::getPrice);
    public static Comparator<Product> byQuantity = Comparator.comparing(Product::getQuantity);
    public static Comparator<Product> byItemType = Comparator.comparing(Product::getItemType);

    // Setters and Getters
    public String getBrand(){return manufacturer;}
    public String getModel(){return model;}
    public String getSpec(){return specification;}
    public float getPrice(){return listPrice;}
    public void setPrice(float f){listPrice = f;}
    public int getQuantity(){return quantity;}
    public void setQuantity(int i){quantity = i;}
    public int getItemNum(){return itemNum;}
    public void setItemNum(int i){itemNum = i;}
    public boolean getItemType(){return isStereo;}
    public void setItemType(boolean i){isStereo = i;}
    public void incrementItemNum(){itemNum++;}
    public void decrementItemNum(){itemNum--;}
}
