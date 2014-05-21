

package orders;

import java.io.IOException;
import java.io.PrintWriter;
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


public class UpdateOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonStatus;
		try {
			String status = request.getParameter("status");
			int order_id = Integer.parseInt(request.getParameter("order_id"));			
			OrderDAO oDAO = new OrderDAO();
			boolean res = oDAO.updateStatusOrder(order_id, status);
			if (res == true) {
				jsonStatus = "true";
			} else {
				throw new Exception("Error updating order");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonStatus = "false";
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print("{\"status\":"+ jsonStatus +"}"); // add json object here
		out.flush();
		
	}
    
}
