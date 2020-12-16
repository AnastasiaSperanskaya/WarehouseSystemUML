package user_interaction;

import entities.ProductUnit;
import managers.DBManager;

import java.sql.SQLException;
import java.util.Scanner;

public class Arrival {
    public static void run() throws SQLException {
        //this class was made to leave applications for arrival
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("How many items will arrive?");
            System.out.println();
            int count = scanner.nextInt();

            DBManager dbmanager = new DBManager();
            int orderID = dbmanager.getAvailableOrderID();

            System.out.println("Enter provider's id: ");
            int providerID = scanner.nextInt();

            for(int i = 0; i < count; i++) {
                String str_input;
                int int_input;
                ProductUnit product = new ProductUnit();
                product.setStatus("Waiting for arrival");

                System.out.println("Enter product's id: ");
                int_input = scanner.nextInt();
                product.setProductID(int_input);

                System.out.println("Enter product's height in cm: ");
                int_input = scanner.nextInt();
                product.setHeight_cm(int_input);

                System.out.println("Enter product's width in cm: ");
                int_input = scanner.nextInt();
                product.setWidth_cm(int_input);

                System.out.println("Enter product's length in cm: ");
                int_input = scanner.nextInt();
                product.setLength_cm(int_input);

                dbmanager.setProductUnit(product);
                dbmanager.setOrderToArrive(product.getProductID(), orderID, providerID);
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
