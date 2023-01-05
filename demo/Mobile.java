// IMT2021055
package demo;
import ecomm.Globals;
import ecomm.*;

public class Mobile extends ProductDemo{
    public Mobile(String name, String product_id, float price, int quantity){
        this.name = name;
        this.product_id = product_id;
        this.price = price;
        this.quantity = quantity;
    }

    public Globals.Category getCategory(){
        return(Globals.Category.Mobile);
    }
}

