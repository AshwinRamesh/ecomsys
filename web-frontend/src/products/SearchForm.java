/**
 * 
 */
package products;
import orders.model.Order;
import orders.dao.OrderDAO;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import orderProducts.dao.OrderProductDAO;
import orderProducts.model.OrderProduct;

/**
 * @author Mojtaba
 *
 */
public class SearchForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
			System.out.println("HELLO POOPY");
			OrderDAO dao;
			try {
				dao = new OrderDAO();
				//List<Order> a = dao.getOrders();
				Order order = new Order(2, "processing", "3dfgsg", "fdgdfh", "syd", "5462", 33.50);
				dao.insertOrder(order);
				OrderProduct p = new OrderProduct("dsgsd34", order.getOrderId(), "TEST", "hello world", 2);
				System.out.println("HELLO POOP");
				OrderProductDAO dao2 = new OrderProductDAO();
				dao2.insertOrderProudct(p);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher view = request.getRequestDispatcher("search.html");
			view.forward(request,response);
	}


}
