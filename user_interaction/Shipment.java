package user_interaction;
import managers.DBManager;

import java.sql.SQLException;
import java.util.Scanner;

public class Shipment {
    public static void run() throws SQLException {

        //this class was made to leave applications for shipping
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("How many items will be in the shipment?");
            System.out.println();
            int count = scanner.nextInt();

            DBManager dbmanager = new DBManager();
            int orderID = dbmanager.getAvailableOrderID();

            System.out.println("Enter customer's id: ");
            int customerID = scanner.nextInt();

            for(int i = 0; i < count; i++) {
                System.out.println("Enter product's id: ");
                int productID = scanner.nextInt();
                dbmanager.setProductUnitStatus(productID, "waiting for shipment");
                dbmanager.setOrderToShip(productID, orderID, customerID);
            }

            System.out.println("Are there more applications to leave?");
            System.out.println("[ yes / no ]");
            String input;
            input = scanner.nextLine();
            input = scanner.nextLine();

            if(input.equals("no")) {
                break;
            }
        }
    }
}
