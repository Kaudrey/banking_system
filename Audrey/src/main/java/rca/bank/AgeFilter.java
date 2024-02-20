package rca.bank;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

@WebFilter("/register") // Add the URL pattern for the registration servlet
public class AgeFilter extends HttpFilter implements Filter {

    public AgeFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        int age = Integer.parseInt(request.getParameter("age"));
        
        if (age >= 18) {
            chain.doFilter(request, response); // Passes the request along the filter chain
        } else {
           
            response.getWriter().write("You must be 18 years or older to access this content.");
        }
    }

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}