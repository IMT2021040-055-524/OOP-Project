// File for Book class.
package demo;
import ecomm.Globals;
import ecomm.*;

// Public class Book, inherits from ProductDemo, which inherits from abstract class Product.
public class Book extends ProductDemo{
    // Public Constructor.
    public Book(String name, String product_id, float price, int quantity){
        this.name = name;
        this.product_id = product_id;
        this.price = price;
        this.quantity = quantity;
    }

    public Globals.Category getCategory(){
        return(Globals.Category.Book);
    }

    
}
