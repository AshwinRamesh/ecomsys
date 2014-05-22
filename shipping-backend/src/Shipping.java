import java.io.IOException;
import java.io.PrintWriter;



import java.util.Enumeration;

// import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Shipping
 */
@WebServlet("/Shipping")
public class Shipping extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shipping() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Order Recieved.");
		// receive params (city name, number of items) -- in this format?
		Enumeration<String> name = request.getParameterNames();
		while (name.hasMoreElements()) {
			System.out.println(name.nextElement());
		}
		String city = request.getParameter("city").toLowerCase();  // convert to lower case
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println("Order Details: City - " + city + " Quantity - " + quantity);
		// load XML, both in order
    	String cities = getServletConfig().getInitParameter("Cities");
    	String[] cityList = cities.split(",");
    	String prices = getServletConfig().getInitParameter("Prices");
    	String[] priceList = prices.split(",");
    	// get corresponding city from cityList
    	int pos = 0;
    	for (int i = 0; i < cityList.length; i++) {
    		if (city == cityList[i]){
    			pos = i;
    			break;
    		}
    	}
    	
    	String resultString = "{\"status\": %s, \"message\": %s, \"cost\": %/.2f}";
    	
    	/** return error if not found */
    	PrintWriter out = response.getWriter();
    	if(pos == 0){
    		// set content type for response
        	response.setContentType("application/json");
        	// Return with a printwriter
        	out.print(String.format(resultString, "false", "City not available for shipping.", 0.0));
        	System.out.println("Invalid shipping city address.");
    	} else {
        	/** Otherwise, calculate shippping price and return */
        	// get corresponding price from priceList and convert
        	String shippingPriceString = priceList[pos];
        	int shippingPriceVal = Integer.parseInt(shippingPriceString);
    		// multiply price by number of items
        	int result = quantity*shippingPriceVal;
    		// set content type for response
        	response.setContentType("application/json");
        	// Return with a printwriter
        	out.print(String.format(resultString, "true", "Success", result));	
        	System.out.println("Shipping cost for order: " + result);
    	}
    	System.out.println("Finished processing order's shipping cost.");
    	out.flush();
	}

}