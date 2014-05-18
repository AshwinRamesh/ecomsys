/**
 * 
 */
package products;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import products.model.Cart;
import products.model.FlickrPhoto;

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
		List<FlickrPhoto> photos = (List<FlickrPhoto>)getServletContext().getAttribute("photos");
		Cart shoppingCart = (Cart)getServletContext().getAttribute("shoppingCart");
	
		if(action.equals("addToCart")){
			shoppingCart.addItem(photoId, photos);
			getServletContext().setAttribute("shoppingCart", shoppingCart);
			response.setStatus(response.SC_OK);

		}else if(action.equals("removeFromCart")){
				shoppingCart.removeItem(photoId, photos);
				getServletContext().setAttribute("shoppingCart", shoppingCart);
				getServletContext().setAttribute("items", shoppingCart.getItems());
				RequestDispatcher view = request.getRequestDispatcher("shoppingCart.jsp");
				view.forward(request,response);			
				response.setStatus(response.SC_OK);
		}else if(action.equals("showCart")){
			response.setStatus(response.SC_OK);
			getServletContext().setAttribute("items", shoppingCart.getItems());
			RequestDispatcher view = request.getRequestDispatcher("shoppingCart.jsp");
			view.forward(request,response);			
		}else{
			response.sendError(response.SC_NOT_FOUND,"Action Not Found");
		}
	}
}
