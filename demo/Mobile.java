// File for Mobile class.
package demo;
import ecomm.Globals;
import ecomm.*;

// Public class Mobile, inherits from ProductDemo, which inherits from abstract class Product.
public class Mobile extends ProductDemo{
    // Public Constructor.
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

