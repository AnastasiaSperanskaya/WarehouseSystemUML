package managers;
import java.awt.*;
import java.sql.*;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import entities.Client;
import entities.OrderToArrive;
import entities.OrderToShip;
import entities.ProductUnit;

public class DBManager {
    private static final String URL = "jdbc:mysql://localhost:3306/warehouse?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootroot";

    public static Connection connectDB() throws SQLException {
        Connection connection;
        Driver driver = new FabricMySQLDriver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        return connection;
        //connection.close();
    }

    public void setOrderToShip(int productID, int orderID, int customerID) throws SQLException {
        Connection connection = connectDB();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (type, orderID, productID, customerID) VALUES (?, ?, ?, ?)");
        statement.setString(1, "ship");
        statement.setInt(2, orderID);
        statement.setInt(3, productID);
        statement.setInt(4, customerID);
        statement.executeUpdate();
    }

    public void setOrderToArrive(int productID, int orderID, int providerID) throws SQLException {
        Connection connection = connectDB();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (type, orderID, productID, providerID) VALUES (?, ?, ?, ?)");
        statement.setString(1, "arrive");
        statement.setInt(2, orderID);
        statement.setInt(3, productID);
        statement.setInt(4, providerID);
        statement.executeUpdate();
    }

    public int getAvailableOrderID() throws SQLException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        int orderID = 0;
        String sql = "SELECT MAX(orderID) from orders;";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            orderID = rs.getInt(1);
        }
        orderID++;
        return orderID;
    }

    public void setProductUnit(ProductUnit product) throws SQLException {
        Connection connection = connectDB();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO product_unit (productID, height_cm, width_cm, length_cm, is_unique, amount_kg) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setInt(1, product.getProductID());
        statement.setInt(2, product.getHeight_cm());
        statement.setInt(3, product.getWidth_cm());
        statement.setInt(4, product.getLength_cm());
        statement.setBoolean(5, product.isUnique());
        statement.setDouble(6, product.getAmount_kg());
        statement.executeUpdate();
    }

    public OrdersManager getOrders() throws SQLException {

        int customerID;
        int productID;
        int providerID;
        OrdersManager orders = new OrdersManager();
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        String sql = "SELECT DISTINCT orderID from orders;";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            int i = rs.getInt("orderID");

            PreparedStatement shipments = null;
            shipments = connection.prepareStatement("SELECT * FROM orders where orderID = ? and type = 'ship' ");
            shipments.setInt(1, i);
            ResultSet ordersToShip = shipments.executeQuery();
            while (ordersToShip.next()) {
                OrderToShip orderToShip = new OrderToShip();

                customerID = ordersToShip.getInt("customerID");
                productID = ordersToShip.getInt("productID");

                Client customer = getCustomerByID(customerID);
                ProductUnit product = getProductUnitByID(productID);

                orderToShip.setCustomer(customer);
                orderToShip.setProduct(product);

                orders.setOrderToShip(orderToShip);
            }

            PreparedStatement arrivals = null;
            arrivals = connection.prepareStatement("SELECT * FROM orders where orderID = ? and type = 'arrive' ");
            arrivals.setInt(1, i);
            ResultSet ordersToArrive = arrivals.executeQuery();
            while (ordersToArrive.next()) {
                OrderToArrive orderToArrive = new OrderToArrive();

                providerID = ordersToArrive.getInt("providerID");
                productID = ordersToArrive.getInt("productID");

                Client provider = getCustomerByID(providerID);
                ProductUnit product = getProductUnitByID(productID);

                orderToArrive.setProvider(provider);
                orderToArrive.setProduct(product);

                orders.setOrderToArrive(orderToArrive);
            }
        }

        return orders;
    }

    public ProductUnit getProductUnitByID(int productId) {
        ProductUnit product = new ProductUnit();

        return product;
    }

    public Client getCustomerByID(int customerId) {
        Client customer = new Client();

        return customer;
    }

    public Client getProviderByID(int providerId) {
        Client provider = new Client();

        return provider;
    }

    public static void main(String[] args) throws SQLException {
        DBManager manager = new DBManager();
        OrdersManager orders = manager.getOrders();
    }
}
