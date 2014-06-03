

package controllers.orders;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.orders.*;
import models.orderProducts.*;
import models.products.Cart;

public class DiscardCart extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user
        HttpSession session = request.getSession();
        // Get all user's orders
        try {
            Cart shoppingCart = (Cart)session.getAttribute("shoppingCart");
            shoppingCart.emptyCart();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Send request
        response.sendRedirect(request.getContextPath() + "/indexPage");
    }

}
