

package orders;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import orderProducts.dao.OrderProductDAO;
import orders.dao.OrderDAO;
import orders.model.Order;

public class MyOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get user
		HttpSession session = request.getSession();
		Enumeration<String> attributes = session.getAttributeNames();
		System.out.println(request.getUserPrincipal().getName());
		// Get all user's orders
		try {
			String user = request.getUserPrincipal().getName();
			OrderDAO oDAO = new OrderDAO();
			OrderProductDAO opDAO = new OrderProductDAO();
			List<Order> orders = oDAO.getUserOrders(user);
			if (orders.size() > 0) {
				request.setAttribute("orders_exist", true);
				for (int i=0; i<orders.size(); i++) {
					orders.get(i).setProducts(opDAO.getOrderProductsForOrder(orders.get(i).getOrderId()));
					System.out.println(orders.get(i).getProducts().size());
				}
				request.setAttribute("orders", orders);
			} else {
				request.setAttribute("orders_exist", false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("orders_exist", false);
		}
		// Send request
		RequestDispatcher view = request.getRequestDispatcher("myorders.jsp");
		view.forward(request,response);
	
	
	
	}
	
}