/**
 * 
 */
package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		Cart shoppingCart = (Cart)session.getAttribute("shoppingCart");
		shoppingCart.emptyCart();

		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/SearchForm");
	}
}
