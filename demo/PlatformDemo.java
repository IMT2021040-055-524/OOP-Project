// IMT2021055
package demo;

import ecomm.Platform;
import ecomm.Seller;
import java.util.ArrayList;
import ecomm.*;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class PlatformDemo extends Platform {
    private ArrayList<Seller> seller_list;
    private int processed_count;
    private int prev_processed_count;
    private Globals global_helper = new Globals();

    public PlatformDemo() {
        this.seller_list = new ArrayList<Seller>(0);
        this.processed_count = 0; // Tracks (line) count of requests which have been processed
        this.prev_processed_count = 0;
    }

    public boolean addSeller(Seller aSeller) {
        if (this.seller_list.contains(aSeller)) {
            System.out.println("Seller already attached to platform.");
            return (false);
        } else {
            this.seller_list.add(aSeller);
            System.out.println("Seller " + aSeller.getID() + " has been added.");
            return (true);
        }
    }

    public void processRequests() {
        ArrayList<String> requests = new ArrayList<String>(0);
        ArrayList<String> responses = new ArrayList<String>(0);

        Scanner scannerobj = new Scanner(System.in);

        while (true) {
            String line = scannerobj.nextLine();

            if (line.equals("Check")) {
                try {
                    // READ
                    File myFile = new File("PortalToPlatform.txt");
                    if (myFile.createNewFile()) {
                        System.out.println("Read File created: " + myFile.getName());
                    } else {
                        System.out.println("Read File already exists.");
                        Scanner readerobj = new Scanner(myFile);

                        for (int i = 0; i < this.processed_count; i++) {
                            String temp_String = readerobj.nextLine();
                        }

                        while (readerobj.hasNextLine()) {
                            String data = readerobj.nextLine();
                            requests.add(data);
                        }
                        readerobj.close();
                    }

                    // PROCESS REQUESTS.
                    for (int pq = this.processed_count; pq < requests.size(); pq++) {

                        String[] req = requests.get(this.processed_count).split(" ");
                        // Start...
                        if (req[2].equals("Start")) {
                            ArrayList<String> temp_list = new ArrayList<String>(0);

                            for (Globals.Category cat : Globals.Category.values()) {

                                temp_list.add(this.global_helper.getCategoryName(cat));
                            }

                            String temp_response = req[0] + " " + req[1];
                            for (int i = 0; i < temp_list.size(); i++) {
                                temp_response = temp_response + " " + temp_list.get(i);
                            }

                            responses.add(temp_response);
                            this.processed_count += 1;
                        }

                        // List <category>
                        else if (req[2].equals("List")) {

                            String req_category = req[3];

                            ArrayList<Product> temp_list = new ArrayList<Product>(0);

                            for (int i = 0; i < this.seller_list.size(); i++) {
                                Seller tempSeller = this.seller_list.get(i);

                                for (Globals.Category c : Globals.Category.values()) {
                                    if (this.global_helper.getCategoryName(c).equals(req_category)) {
                                        temp_list.addAll(tempSeller.findProducts(c));
                                    }
                                }
                            }

                            String temp_response_head = req[0] + " " + req[1];

                            for (int i = 0; i < temp_list.size(); i++) {
                                String temp_response = temp_response_head + " " + temp_list.get(i).getName() + " "
                                        + temp_list.get(i).getProductID() + " " + temp_list.get(i).getPrice() + " "
                                        + temp_list.get(i).getQuantity();
                                responses.add(temp_response);
                            }

                            this.processed_count += 1;
                        }

                        // Buy <product_id> <num_items>
                        else if (req[2].equals("Buy")) {
                            String p_id = req[3];
                            int p_quantity = Integer.parseInt(req[4]);

                            boolean is_available = false;

                            // Prioritising first available valid seller.
                            for (int i = 0; i < this.seller_list.size(); i++) {
                                Seller tempSeller = this.seller_list.get(i);

                                for (Globals.Category c : Globals.Category.values()) {
                                    for (int j = 0; j < tempSeller.findProducts(c).size(); j++) {
                                        Product temp_Product = tempSeller.findProducts(c).get(j);
                                        if (temp_Product.getProductID().equals(p_id)) {
                                            is_available = tempSeller.buyProduct(p_id, p_quantity);
                                            if (is_available) {
                                                String temp_response = req[0] + " " + req[1] + " Success";
                                                responses.add(temp_response);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                            if (!is_available) {
                                String temp_response = req[0] + " " + req[1] + " Failure";
                                responses.add(temp_response);
                            }

                            this.processed_count += 1;
                        }
                    }

                    // WRITE.
                    File writeobj = new File("PlatformToPortal.txt");
                    if (writeobj.createNewFile()) {
                        System.out.println("Write File created: " + writeobj.getName());
                        FileWriter writer = new FileWriter(writeobj, true);

                        for (int i = this.prev_processed_count; i < responses.size(); i++) {
                            writer.append(responses.get(i) + "\n");
                        }
                        this.prev_processed_count = responses.size();
                        writer.close();

                    } else {
                        System.out.println("Write File already exists.");
                        FileWriter writer = new FileWriter(writeobj, true);
                        for (int i = this.prev_processed_count; i < responses.size(); i++) {
                            writer.append(responses.get(i) + "\n");
                        }
                        this.prev_processed_count = responses.size();
                        writer.close();
                    }
                }

                catch (Exception e) {
                    System.out.println("An error occurred." + e.getMessage());
                }
            }

            else if (line.equals("End")) {
                break;
            }
        }
        scannerobj.close();
        return;
    }
}
