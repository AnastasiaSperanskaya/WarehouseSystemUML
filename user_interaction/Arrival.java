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

            for(int i = 0; i < count; i++) {
                String str_input;
                int int_input;
                ProductUnit product = new ProductUnit();

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

                System.out.println("Is the product unique or we must change amount? ");
                System.out.println("[ yes / no ]");
                str_input = scanner.nextLine();
                str_input = scanner.nextLine();
                if (str_input.equals("yes")) {
                    product.setUnique(true);
                } else {
                    product.setUnique(false);
                    System.out.println("Enter product's amount in kg: ");
                    double dbl_input = scanner.nextDouble();
                    product.setAmount_kg(dbl_input);
                }

                System.out.println("Enter provider's id: ");
                int providerID = scanner.nextInt();

                dbmanager.setProductUnit(product);
                dbmanager.setOrderToArrive(product.getProductID(), orderID, providerID);
                //in arrival vice-versa: first we will be adding product in product_unit table and ALSO in orders
                //table with type = arrival
                //product.productID = Integer.parseInt(input);
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
