// Written by Vidhish Trivedi - IMT2021055
// Main file for Platform.
import demo.PlatformDemo;
import demo.Seller040;
import demo.Seller055;
import demo.Seller524;


public class PlatformMain {
    public static void main(String[] args) {
        // Create instance of PlatformDemo.
        PlatformDemo pd = new PlatformDemo();

        // Create instances of Sellers.
        Seller040 s1 = new Seller040("1");
        Seller055 s2 = new Seller055("2");
        Seller524 s3 = new Seller524("3");

        // Add seller instances to platform.
        s1.addPlatform(pd);
        s2.addPlatform(pd);
        s3.addPlatform(pd);

        // Process incoming requests.
        pd.processRequests();
    }
}
