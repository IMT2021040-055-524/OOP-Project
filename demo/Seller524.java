package demo;
import ecomm.Product;
import ecomm.Seller;
import ecomm.Globals;
import ecomm.Platform;
import ecomm.*;
import java.util.*;


public class Seller524 extends Seller{
	private String myID;
	private ArrayList<Product> products;
    private Globals global_helper = new Globals();

    // id is passed in by the class that instantiates sub-type of seller
	public Seller524(String id) {
        super(id);
		myID = id;
        this.products = new ArrayList<Product>(0);
		Book book1 = new Book("The_Great_Gatsby","B1",(float)250.50,22);
		Book book2 = new Book("The_Lion_King","B2",(float)450.00,18);
		Book book3 = new Book("The_Jungle_Book","B3",(float)200.00,15);
		Book book4 = new Book("Farenheit_451","B4",(float)500.00,20);
		Mobile mobile1 = new Mobile("Nokia_4410","M1",(float)10000.00,10);
		Mobile mobile2 = new Mobile("Pixel_4A","M2",(float)30000.00,24);
		Mobile mobile3 = new Mobile("Iphone_13","M3",(float)50000.00,30);
		products.add(book1);
		products.add(book2);
		products.add(book3);
		products.add(book4);
		products.add(mobile1);
		products.add(mobile2);
		products.add(mobile3);
	}

	// ID of seller.
	public String getID()
	{
		return myID;
	}

	// Platform it is being added to.
	// Should in turn add itself to the Platform (with addSeller)
	public void addPlatform(Platform thePlatform){
		thePlatform.addSeller(this);
        return;
	}

	// Seller to return listing of Products of specified Category
	public ArrayList<Product> findProducts(Globals.Category whichOne){
		ArrayList<Product> listofall = new ArrayList<Product>();
		for(int i=0;i<products.size();i++){
			if(this.global_helper.getCategoryName(products.get(i).getCategory()).equals(this.global_helper.getCategoryName(whichOne))){
				listofall.add(products.get(i));
			}
		}
		return listofall;
	}
	// User wants to buy specified quantity of productID
	// Return true if transaction succeeds, false otherwise.
	// Transaction fails if incorrect productID or quantity exceeds available inventory
	public boolean buyProduct(String productID, int quantity){
		for(int i=0;i<products.size();i++){
			if(products.get(i).getProductID().equals(productID) && products.get(i).getQuantity()>=quantity){
                products.get(i).setQuantity(products.get(i).getQuantity() - quantity);
				return true;
			}
		}
		return false;
	}
}
