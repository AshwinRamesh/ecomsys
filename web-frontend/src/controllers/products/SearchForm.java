/**
 * 
 */
package controllers.products;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import models.products.Cart;
import ShippingAPI.ShipRestApi;

/**
 * @author Mojtaba
 *
 */
public class SearchForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected Cart shoppingCart = new Cart(); 
	
	/**
	 * 
	 */
	public SearchForm() {
		super();
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Get current session object and sets limit to 30 minutes*/
		HttpSession session = request.getSession();
		String sessId = session.getId();
		session.setMaxInactiveInterval(60*30);
		shoppingCart.setSessId(sessId);
		ShipRestApi a = new ShipRestApi();
		try {
			JSONObject jObj = a.getShippingCost("sydney", 3);
			System.out.println(jObj.get("status"));
			System.out.println(jObj.get("cost"));
			System.out.println(jObj.get("message"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("shoppingCart", shoppingCart);
		
		RequestDispatcher view = request.getRequestDispatcher("search.html");
		view.forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// expire session
		HttpSession session = request.getSession();
		session.invalidate();
		// TODO - empty the servlet context
		// redirect to SearchForm
		response.sendRedirect("logout.html");
	}


}
