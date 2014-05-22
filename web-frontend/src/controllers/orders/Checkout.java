

package controllers.orders;
import models.orders.*;
import models.orderProducts.*;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Checkout extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Show order form
        // Get user
        HttpSession session = request.getSession();
        // Get all user's orders
        try {
           
        } catch (Exception e) {

        }
        // Send request
        RequestDispatcher view = request.getRequestDispatcher("checkout.jsp");
        view.forward(request,response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Try place order
	}

}
