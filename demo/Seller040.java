package demo;
import ecomm.Product;
import ecomm.Seller;
import ecomm.Globals;
import ecomm.Platform;
import ecomm.*;
import java.util.*;

public class Seller040 extends Seller{
	private ArrayList<ProductDemo> prods;
    private Globals global_helper = new Globals();

	public Seller040(String id){
        super(id);
        this.prods = new ArrayList<ProductDemo>();
		Book b1 = new Book("A_Study_in_Scarlet","SH_ID",(float)309.98,10000);
		Book b2 = new Book("And_Then_There_were_None","TWN_ID",(float)295.55,30000);
		Mobile m1 = new Mobile("Pixel_6A","PXL",(float) 31999.95,5000);
		Mobile m2 = new Mobile("Oneplus_Nord_2","ONP_ID",(float) 24999.99,2000);
		prods.add(b1);
		prods.add(b2);
		prods.add(m1);
		prods.add(m2);
    }
	// Platform it is being added to.
	// Should in turn add itself to the Platform (with addSeller)
	public void addPlatform(Platform thePlatform){
        thePlatform.addSeller(this);
        return;
    }
	// Seller to return listing of Products of specified Category
	public ArrayList<Product> findProducts(Globals.Category whichOne){
        ArrayList<Product> v = new ArrayList<Product>();
        for(int i=0;i<prods.size();i++){
            if(this.global_helper.getCategoryName(prods.get(i).getCategory()).equals(this.global_helper.getCategoryName(whichOne))){
                v.add(prods.get(i));
            }
        }
        return v;
    }
	// User wants to buy specified quantity of productID
	// Return true if transaction succeeds, false otherwise. 
	// Transaction fails if incorrect productID or quantity exceeds available inventory
	public boolean buyProduct(String productID, int quantity){
		boolean if_success=false;
        for(Integer i=0;i<prods.size();i++){
			if(prods.get(i).getProductID().equals(productID)){
				if(prods.get(i).getQuantity()>=quantity){
					if_success= true;
                    prods.get(i).setQuantity(prods.get(i).getQuantity() - quantity);
				}else{
					if_success=false;
				}
			}
		}
		return if_success;
    }
}
