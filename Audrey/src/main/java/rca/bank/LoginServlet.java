package rca.bank;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException; 


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if (!InputValidator.isValidEmail(email)) {
            response.sendRedirect("login.jsp?error=InvalidEmail");
            return;
        }
        
        Customer user = DatabaseConnection.getUserByEmailAndPassword(email, password);
        int customerId = DatabaseConnection.getCustomerIdByEmail(email); 


        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // for storing user info in the session
            session.setAttribute("customerId", customerId);
            Cookie emailCookie = new Cookie("email", email);  // Add a cookie to remember user's email
            emailCookie.setMaxAge(3600); //valid for 1hr 
            response.addCookie(emailCookie);

            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp?error=InvalidCredentials");
        }
    }
}
