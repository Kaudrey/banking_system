package rca.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/banking_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static final String SQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection connection = dbConnection.getConnection();
            if (connection != null) {
                System.out.println("Connected to the database.");
                
                System.out.println("Data inserted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(SQL_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public String insertData(Customer customer) {
        String insertDataSQL = "INSERT INTO customers (name, email,age,password,phoneNumber) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection=getConnection();
        		PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setInt(3,customer.getAge());
            statement.setString(4, customer.getPassword());
            statement.setString(5,customer.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
	        e.printStackTrace();
	        return "Failed to insert data!";
	    }
		return  "Successfully added";
    }
    
    public static Customer getUserByEmailAndPassword(String email, String password) {
        String selectUserSQL = "SELECT * FROM customers WHERE email = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(selectUserSQL)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // User found, create and return a Customer object
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    return new Customer(id,name, email, password, phoneNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // User not found
    }
    
    public static int getCustomerIdByEmail(String email) {
        int customerId = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM customers WHERE email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customerId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerId;
    }
}


