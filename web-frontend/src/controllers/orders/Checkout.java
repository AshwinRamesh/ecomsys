

package controllers.orders;
import models.orders.*;
import models.orderProducts.*;
import models.products.Cart;
import models.products.FlickrPhoto;

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
        HttpSession session = request.getSession();
        Cart shoppingCart = (Cart)session.getAttribute("shoppingCart");
		session.setAttribute("items", shoppingCart.getItems());
		session.setAttribute("total", shoppingCart.getTotal());
        RequestDispatcher view = request.getRequestDispatcher("checkout.jsp");
        view.forward(request,response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Try place order
		// sent REST request to check if city exists
		// if true: create order, create products, return details
		// if false: return fail json
	}

}
