// File for Seller implementation by IMT2021055.
package demo;
import ecomm.Product;
import ecomm.Seller;
import ecomm.Globals;
import ecomm.Platform;
import ecomm.*;
import java.util.*;

// Public class for Seller055, inherits from abstract class Seller.
public class Seller055 extends Seller{
    // Private data members.
    // Instance of Globals class, to be used as a helper.
    private Globals global_helper = new Globals();
    
    // List of categories.
    private ArrayList <String> categories;

    // Map: category -> list of products of that category.
    private HashMap <String, ArrayList <ProductDemo>> category_products;

    //Public constructor.
    public Seller055(String id){
        super(id);  // Call to constructor of parent class.
        
        // Initialising data members.
        this.categories = new ArrayList <String> (0);
        this.categories.add("Book");
        this.categories.add("Mobile");

        this.category_products = new HashMap <String, ArrayList <ProductDemo>> (0);
        this.category_products.put("Book", new ArrayList <ProductDemo> (0));
        this.category_products.put("Mobile", new ArrayList <ProductDemo> (0));

        // Product information (Hard-Coded)
        // Books:
        this.category_products.get("Book").add(new Book("A_Christmas_Carol", "seller055-book1", Seller055.decidePrice(), Seller055.decideQuantity()));
        this.category_products.get("Book").add(new Book("Endless_Night", "seller055-book2", Seller055.decidePrice(), Seller055.decideQuantity()));
        this.category_products.get("Book").add(new Book("Alice_in_Wonderland", "seller055-book3", Seller055.decidePrice(), Seller055.decideQuantity()));
        this.category_products.get("Book").add(new Book("Charlie_and_The_Chocolate_Factory", "seller055-book4", Seller055.decidePrice(), Seller055.decideQuantity()));
        // Mobiles.
        this.category_products.get("Mobile").add(new Mobile("Nokia_3310", "seller055-mobile1", Seller055.decidePrice(), Seller055.decideQuantity()));
        this.category_products.get("Mobile").add(new Mobile("Motorola_Razor", "seller055-mobile2", Seller055.decidePrice(), Seller055.decideQuantity()));
        this.category_products.get("Mobile").add(new Mobile("Sony_Ericsson_Xperia", "seller055-mobile3", Seller055.decidePrice(), Seller055.decideQuantity()));
    }

    // List products of a particular category.
    public ArrayList<Product> findProducts(Globals.Category whichOne){
        // Get category name as a string.
        String cat_string = global_helper.getCategoryName(whichOne);
        
        ArrayList <Product> temp_list = new ArrayList <Product> (0);
        for(int i = 0; i < this.category_products.get(cat_string).size(); i++){
            Product temp_prod = this.category_products.get(cat_string).get(i);
            temp_list.add(temp_prod);
        }
        return(temp_list);
    }

    // Buy a product.
    public boolean buyProduct(String productID, int quantity){
        ArrayList <ProductDemo> books = this.category_products.get("Book");
        ArrayList <ProductDemo> mobiles = this.category_products.get("Mobile");
        
        // Search for product, if found, check quantity.
        // If sufficient quantity is available, decrease it and return true.
        for(int i = 0; i < books.size(); i++){
            ProductDemo temp_prod = books.get(i);
            if(temp_prod.getProductID().equals(productID)){
                if(temp_prod.getQuantity() >= quantity){
                    temp_prod.setQuantity(temp_prod.getQuantity() - quantity);
                    return(true);
                }
            }
        }
        
        // Search for product, if found, check quantity.
        // If sufficient quantity is available, decrease it and return true.
        for(int i = 0; i < mobiles.size(); i++){
            ProductDemo temp_prod = mobiles.get(i);
            if(temp_prod.getProductID().equals(productID)){
                if(temp_prod.getQuantity() >= quantity){
                    temp_prod.setQuantity(temp_prod.getQuantity() - quantity);
                    return(true);
                }
            }
        }

        // If product not found or insufficient quantity available, return false.
        return(false);
    }


    // ADD PLATFORM.
    public void addPlatform(Platform thePlatform){
        // Add this seller to the platform.
        thePlatform.addSeller(this);
        return;
    }

    // HELPER METHODS, used only within this class.
    static private float decidePrice(){
        return((float)((1 + Math.random())*550));
    }

    static private int decideQuantity(){
        return((5 + (int)Math.floor(5*Math.random())));
    }
}
