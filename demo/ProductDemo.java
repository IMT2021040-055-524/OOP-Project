// File for ProductDemo class.
package demo;

import ecomm.Globals;
import ecomm.Product;
import ecomm.*;

// Public class ProductDemo, inherits from abstract class Product.
public abstract class ProductDemo extends Product{
    protected String name;
    protected String product_id;
    protected float price;
    protected int quantity;
    
    public abstract Globals.Category getCategory();
    
    // Getters.
    public String getName(){
        return(this.name);
    }

    public String getProductID(){
        return(this.product_id);
    }

    public float getPrice(){
        return(this.price);
    }

    public int getQuantity(){
        return(this.quantity);
    }

    // Setter for quantity.
    public void setQuantity(int new_quantity){
        this.quantity = new_quantity;
    }
}
