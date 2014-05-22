/**
 * 
 */
package controllers.products;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.products.Cart;
import models.products.FlickrPhoto;

/**
 * @author Mojtaba Karami
 *
 */
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public ShoppingCart() {
		super();
	}
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String photoId = request.getParameter("photoId");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		List<FlickrPhoto> photos = (List<FlickrPhoto>)session.getAttribute("photos");
		Cart shoppingCart = (Cart)session.getAttribute("shoppingCart");
	
		if(action.equals("addToCart")){
			synchronized(shoppingCart){
				shoppingCart.addItem(photoId, photos);
				session.setAttribute("shoppingCart", shoppingCart);
			}	
			response.setStatus(response.SC_OK);

		}else if(action.equals("removeFromCart")){
			synchronized(shoppingCart){
				shoppingCart.removeItem(photoId);
				session.setAttribute("shoppingCart", shoppingCart);
				session.setAttribute("items", shoppingCart.getItems());
				session.setAttribute("total", shoppingCart.getTotal());
			}	
			
			RequestDispatcher view = request.getRequestDispatcher("shoppingCart.jsp");
			view.forward(request,response);			
				//response.setStatus(response.SC_OK);
		}else if(action.equals("showCart")){
			response.setStatus(response.SC_OK);
			session.setAttribute("items", shoppingCart.getItems());
			RequestDispatcher view = request.getRequestDispatcher("shoppingCart.jsp");
			session.setAttribute("total", shoppingCart.getTotal());
			view.forward(request,response);			
		}else{
			response.sendError(response.SC_NOT_FOUND,"Action Not Found");
		}
	}
}
