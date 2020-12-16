package user_interaction;
import entities.OrderToArrive;
import entities.OrderToShip;
import managers.DBManager;
import managers.OrdersManager;
import java.sql.SQLException;
import java.util.List;
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
            //then we must give all information about this order (place, customer etc.)
            List<OrderToShip> ordersToShip = orders.getOrdersToShip();
            ordersToShip.forEach((orderToShip) -> {
                System.out.println(orderToShip.toString());
            });
            //here we must get information about what exact order worker want to execute
            System.out.println("Enter the orderID of order you had executed: ");
            int orderID = scanner.nextInt();
            scanner.nextLine();

            //then we must delete information about this order to ship
            //change product unit status to shipped
            //and free space ( somewhere )
            manager.deleteOrderByID(orderID);
            ordersToShip.forEach((orderToShip) -> {
                if(orderToShip.getOrderID() == orderID) {
                    orderToShip.getProducts().forEach((product) -> {
                        try {
                            manager.freePlace(product.getPlaceID());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    });
                    orderToShip.getProducts().forEach((product) -> {
                        try {
                            manager.setProductUnitStatus(product.getProductID(), "shipped");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    });
                }
            });
            System.out.println("Database stats successfully updated!");
            System.out.println();
        } else if(input.equals("arrival")) {
            //here we must give all information about this order (place, provider etc.)
            List<OrderToArrive> ordersToArrive = orders.getOrdersToArrive();
            ordersToArrive.forEach((orderToArrive) -> {
                System.out.println(orderToArrive.toString());
            });
            System.out.println("Enter the orderID of order you had executed: ");
            int orderID = scanner.nextInt();
            scanner.nextLine();

            //generate places for each product
            //set statuses to arrived
            ordersToArrive.forEach((orderToArrive) -> {
                if(orderToArrive.getOrderID() == orderID) {
                    orderToArrive.getProducts().forEach((product) -> {
                        try {
                            manager.setProductUnitStatus(product.getProductID(), "arrived");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                        int placeID = -1;

                        try {
                            placeID = manager.generatePlace(product.getHeight_cm(), product.getWidth_cm(), product.getLength_cm());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                        try {
                            manager.setPlace(product.getProductID(), placeID);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    });
                }
            });

            //delete information about this order to arrive
            manager.deleteOrderByID(orderID);

            System.out.println("Database stats successfully updated!");
            System.out.println();
        } else { System.out.println("There is no service of this kind"); }
    }
}
