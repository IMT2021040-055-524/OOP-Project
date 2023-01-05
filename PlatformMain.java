import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import demo.PlatformDemo;
import demo.Seller040;
import demo.Seller055;
import demo.Seller524;


public class PlatformMain {
    public static void main(String[] args) {
        PlatformDemo pd = new PlatformDemo();

        Seller040 s1 = new Seller040("1");
        Seller055 s2 = new Seller055("2");
        Seller524 s3 = new Seller524("3");

        s1.addPlatform(pd);
        s2.addPlatform(pd);
        s3.addPlatform(pd);

        pd.processRequests();
    }
}
