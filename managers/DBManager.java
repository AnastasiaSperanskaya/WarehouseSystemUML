package managers;

import java.sql.*;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

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

    public void setOrderToShip(int productID, int orderID) throws SQLException {
        Connection connection = connectDB();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO orders VALUES (?, ?, ?)");
        statement.setString(1, "ship");
        statement.setInt(2, orderID);
        statement.setInt(3, productID);
        statement.executeUpdate();
    }

    public static int getAvailableOrderID() throws SQLException {
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

    public static void main(String[] args) throws SQLException {
        Connection connection = connectDB();

        int id = getAvailableOrderID();
        System.out.println(id);
    }
}
