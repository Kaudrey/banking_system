package rca.bank;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");

        // Validate email, password, and phone number
        String emailErrorMessage = InputValidator.getEmailErrorMessage(email);
        String passwordErrorMessage = InputValidator.getPasswordErrorMessage(password);
        String phoneNumberErrorMessage = InputValidator.getPhoneNumberErrorMessage(phoneNumber);
        
        String phoneFormatMessage;
        if (phoneNumber.startsWith("07")) {
            phoneFormatMessage = "Local number format";
        } else if (phoneNumber.startsWith("2507")) {
            phoneFormatMessage = "International number format";
        } else {
            phoneFormatMessage = "";
        }
        
        request.setAttribute("phoneFormatMessage", phoneFormatMessage);

        if (emailErrorMessage == null && passwordErrorMessage == null && phoneNumberErrorMessage == null) {
            try {
                Customer customer = new Customer(name, email, age, password, phoneNumber);
                DatabaseConnection dbConnection = new DatabaseConnection();
                Connection connection = dbConnection.getConnection();
                if (connection != null) {
                    dbConnection.insertData(customer);
                    connection.close();
                    response.sendRedirect("login.jsp");
                    return;
                } else {
                    request.setAttribute("error", "Database connection error.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Database error: " + e.getMessage());
            }
        } else {
            // Set error messages in request attributes
            request.setAttribute("emailError", emailErrorMessage);
            request.setAttribute("passwordError", passwordErrorMessage);
            request.setAttribute("phoneNumberError", phoneNumberErrorMessage);
        }

        // Forward the request back to the registration page
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    // Generate a unique user ID (you can implement your own logic)
    private int generateUserId() {
        return (int) (Math.random() * 1000); // Example: Generate a random ID
    }
}


