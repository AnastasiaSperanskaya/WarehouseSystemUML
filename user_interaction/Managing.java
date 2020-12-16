package user_interaction;
import managers.DBManager;
import managers.OrdersManager;

import java.sql.SQLException;
import java.util.Scanner;

public class Managing {
    public static void run() throws SQLException {
        //this class was made to ship products or receive delivered ones

        System.out.println("What kind of order do you want to execute?");
        System.out.println("[ shipment / arrival ]");
        System.out.println();

        DBManager manager = new DBManager();
        //here we must make new OrderManager class and get lists of orders to arrive and to ship
        OrdersManager orders = manager.getOrders();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.toLowerCase();

        if(input.equals("shipment")) {
            //here we must get information about what exact order worker want to execute

            //then we must give all information about this order (place, customer etc.)

            //then we must delete all products from database and information about this order to ship
            //and free space ( somewhere )

        } else if(input.equals("arrival")) {
            //here we must give all information about this order (place, provider etc.)

            //generate places for each product

            //delete information about this order to arrive

        } else { System.out.println("There is no service of this kind"); }
    }
}
