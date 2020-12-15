package user_interaction;
import managers.DBManager;

import java.sql.SQLException;
import java.util.Scanner;

public class Shipment {
    public static void run() throws SQLException {

        //this class was made to leave applications for shipping
        Scanner ship_scan = new Scanner(System.in);

        while (true) {
            System.out.println("How many items will be in the shipment?");
            System.out.println();
            int count = ship_scan.nextInt();

            DBManager dbmanager = new DBManager();
            int orderID = dbmanager.getAvailableOrderID();

            for(int i = 0; i < count; i++) {
                System.out.println("Enter product's id: ");
                int productID = ship_scan.nextInt();
                //then add a record in orders table with generated order number for each product
                dbmanager.setOrderToShip(productID, orderID);
                //in arrival vice-versa: firs we will be adding product in product_unit table and ALSO in orders
                //table with type = arrival
                //product.productID = Integer.parseInt(input);
            }

            System.out.println("Are there more applications to leave?");
            System.out.println("[ yes / no ]");
            String input1;
            input1 = ship_scan.nextLine();
            input1 = ship_scan.nextLine();

            if(input1.equals("no")) {
                break;
            }
        }
    }
}
