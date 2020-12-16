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
    private static Connection connection;

    public static void setConnection(Connection connection) {
        DBManager.connection = connection;
    }

    public void connectDB() throws SQLException {
        Connection connection;
        Driver driver = new FabricMySQLDriver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        setConnection(connection);
        //connection.close();
    }

    public DBManager() throws SQLException {
        connectDB();
    }

    public void setOrderToShip(int productID, int orderID, int customerID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (type, orderID, productID, customerID) VALUES (?, ?, ?, ?)");
        statement.setString(1, "ship");
        statement.setInt(2, orderID);
        statement.setInt(3, productID);
        statement.setInt(4, customerID);
        statement.executeUpdate();
    }

    public void setOrderToArrive(int productID, int orderID, int providerID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (type, orderID, productID, providerID) VALUES (?, ?, ?, ?)");
        statement.setString(1, "arrive");
        statement.setInt(2, orderID);
        statement.setInt(3, productID);
        statement.setInt(4, providerID);
        statement.executeUpdate();
    }

    public int getAvailableOrderID() throws SQLException {
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
        int orderID;
        OrdersManager orders = new OrdersManager();
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
                orderID = ordersToShip.getInt("orderID");

                Client customer = getCustomerByID(customerID);
                ProductUnit product = getProductUnitByID(productID);

                orderToShip.setCustomer(customer);
                orderToShip.setProduct(product);
                orderToShip.setOrderID(orderID);

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
                orderID = ordersToArrive.getInt("orderID");

                Client provider = getProviderByID(providerID);
                ProductUnit product = getProductUnitByID(productID);

                orderToArrive.setProvider(provider);
                orderToArrive.setProduct(product);
                orderToArrive.setOrderID(orderID);

                orders.setOrderToArrive(orderToArrive);
            }
        }

        return orders;
    }

    public ProductUnit getProductUnitByID(int productId) throws SQLException {
        ProductUnit product = new ProductUnit();

        PreparedStatement statement = null;
        statement = connection.prepareStatement("SELECT * FROM product_unit where productID = ?");
        statement.setInt(1, productId);
        ResultSet productUnit = statement.executeQuery();

        while(productUnit.next()) {
            product.setAmount_kg(productUnit.getInt("amount_kg"));
            product.setHeight_cm(productUnit.getInt("height_cm"));
            product.setLength_cm(productUnit.getInt("length_cm"));
            product.setWidth_cm(productUnit.getInt("width_cm"));
            product.setProductID(productUnit.getInt("productID"));
            product.setPlaceID(productUnit.getInt("placeID"));
            product.setUnique(productUnit.getBoolean("is_unique"));
        }

        return product;
    }

    public Client getCustomerByID(int customerId) throws SQLException {
        Client customer = new Client();

        PreparedStatement statement = null;
        statement = connection.prepareStatement("SELECT * FROM customers where customerID = ?");
        statement.setInt(1, customerId);
        ResultSet customer_rs = statement.executeQuery();

        while(customer_rs.next()) {
            customer.setAddress(customer_rs.getString("address"));
            customer.setClientID(customer_rs.getInt("customerID"));
            customer.setMail(customer_rs.getString("email"));
            customer.setName(customer_rs.getString("name"));
            customer.setPhone(customer_rs.getString("phone_number"));
        }

        return customer;
    }

    public Client getProviderByID(int providerId) throws SQLException {
        Client provider = new Client();

        PreparedStatement statement = null;
        statement = connection.prepareStatement("SELECT * FROM providers where providerID = ?");
        statement.setInt(1, providerId);
        ResultSet provider_rs = statement.executeQuery();

        while(provider_rs.next()) {
            provider.setAddress(provider_rs.getString("address"));
            provider.setClientID(provider_rs.getInt("providerID"));
            provider.setMail(provider_rs.getString("email"));
            provider.setName(provider_rs.getString("name"));
            provider.setPhone(provider_rs.getString("phone_number"));
        }

        return provider;
    }

    public void deleteProductUnitByID(int productID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM product_unit WHERE productID = ?");
        statement.setInt(1, productID);
        statement.executeUpdate();
    }

    public void deleteOrderByID(int orderID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE orderID = ?");
        statement.setInt(1, orderID);
        statement.executeUpdate();
    }

    public void freePlace(int productID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE place SET productID = NULL WHERE productID = ?");
        statement.setInt(1, productID);
        statement.executeUpdate();
    }

    public void setProductUnitStatus(int productID, String status) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE product_unit SET status = ? WHERE productID = ?");
        statement.setInt(2, productID);
        statement.setString(1, status);
        statement.executeUpdate();
    }

    public void setPlace(int productID, int placeID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE product_unit SET placeID = ? WHERE productID = ?");
        statement.setInt(1, placeID);
        statement.setInt(2, productID);
        statement.executeUpdate();

        statement = connection.prepareStatement("UPDATE place SET productID = ? WHERE placeID = ?");
        statement.setInt(2, placeID);
        statement.setInt(1, productID);
        statement.executeUpdate();
    }

    public static int generatePlace(int height_cm, int width_cm, int length_cm) throws SQLException {
        int placeID = -1;
        DBManager manager = new DBManager();

        PreparedStatement statement = null;
        statement = connection.prepareStatement("SELECT * FROM place where height_cm >= ? and width_cm >= ? and length_cm >= ? and productID IS NULL");
        statement.setInt(1, height_cm);
        statement.setInt(2, width_cm);
        statement.setInt(3, length_cm);
        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            placeID = rs.getInt("placeID");
        }

        return placeID;
    }

    public static void main(String[] args) throws SQLException {
        DBManager manager = new DBManager();
        OrdersManager orders = manager.getOrders();

        System.out.println(manager.generatePlace(1, 1, 1));
    }
}
