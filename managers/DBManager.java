package managers;
import java.sql.*;
import com.mysql.fabric.jdbc.FabricMySQLDriver;
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

    public static void main(String[] args) throws SQLException {
        Connection connection = connectDB();

        DBManager dbmanager = new DBManager();
        int id = dbmanager.getAvailableOrderID();
        System.out.println(id);
    }
}
