

package controllers.orders;
import models.orders.*;
import models.orderProducts.*;
import models.products.Cart;
import models.products.FlickrPhoto;

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

import ShippingAPI.ShipRestApi;
import ShippingAPI.Shipping;

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
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String outString;
		try {
			HttpSession session = request.getSession();
	        Cart shoppingCart = (Cart)session.getAttribute("shoppingCart");
		        // Get shipping cost
		        String city = request.getParameter("city");
		        int quantity = shoppingCart.getItems().size();
		        ShipRestApi ship = new ShipRestApi();
				Shipping costObj = ship.getShippingCost(city, quantity);
				if (costObj.getStatus()) {  // true - cost retrieved
					OrderDAO oDao = new OrderDAO();
					Order o = new Order(oDao.getUserId(request.getUserPrincipal().getName()), "processing", request.getParameter("add1"),
									request.getParameter("add2"), city, request.getParameter("postcode"), shoppingCart.getTotal(),
									costObj.getCost(), shoppingCart.getTotal()+costObj.getCost());
					boolean res = oDao.insertOrder(o);  // attempt to insert order
					OrderProductDAO opDao = new OrderProductDAO();
					if (res) { // insert order products
						for (int i=0; i < shoppingCart.getItems().size(); i++) {
							OrderProduct op = new OrderProduct(shoppingCart.getItems().get(i).getPhotoId(),
															   o.getOrderId(),
															   shoppingCart.getItems().get(i).getPhotoTitle(),
															   shoppingCart.getItems().get(i).getDescription(),
															   1,
															   shoppingCart.getItems().get(i).getPrice());
							opDao.insertOrderProudct(op);
						}
						Double totalCost = shoppingCart.getTotal()+costObj.getCost();
						outString = "{\"status\":true, \"shipCost\":" + costObj.getCost() + ", \"finalCost\": " + totalCost + "}";
					} else {  // issue inserting order
						outString = "{\"status\":false}";
					}
				} else { // error with city
					outString = "{\"status\":false}";
				}
			} catch (Exception e) {	outString = "{\"status\":false}";}

		out.print(outString); // add json object here
		out.flush();
	}
}
