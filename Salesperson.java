import java.math.BigDecimal;

public class Salesperson {
    private String name;
    private float commisionPercentage;
    private int salesToDate;
    private BigDecimal revenueGenerated;
    private Sale recentSale;

    public Salesperson(String n, float commision){
       name = n;
       commisionPercentage = commision;
    }
    void closeSale(Sale s){

    }

}
