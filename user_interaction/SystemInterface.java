package user_interaction;

import java.sql.SQLException;
import java.util.Scanner;

public class SystemInterface {
    public static void main (String[] args) throws SQLException {
        Scanner my_scan = new Scanner(System.in);

        while (true) {
            System.out.println("What type of service would you like to use?");
            System.out.println("[ shipment / arrival / managing / exit ]");
            System.out.println();

            String input = my_scan.nextLine();
            input = input.toLowerCase();

            if (input.equals("shipment")) {
                System.out.println("Switched to [ shipment ] mode");
                System.out.println();
                Shipment.run();
            } else if (input.equals("arrival")) {
                System.out.println("Switched to [ arrival ] mode");
                System.out.println();
                Arrival.run();
            } else if (input.equals("managing")) {
                System.out.println("Switched to [ managing ] mode");
                System.out.println();
                Managing.run();
            } else if (input.equals("exit")) {
                System.out.println("exiting...");
                break;
            } else {
                System.out.println("There is no service of this kind");
            }
        }
    }
}
