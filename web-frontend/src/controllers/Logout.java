/**
 * 
 */
package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import products.model.Cart;

/**
 * @author Mojtaba
 *
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public Logout() {
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");

		Cart shoppingCart = (Cart)getServletContext().getAttribute("shoppingCart");
		shoppingCart.emptyCart();

		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/SearchForm");
	}
}
