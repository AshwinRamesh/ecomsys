import java.io.IOException;
import java.io.PrintWriter;



// import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Order Recieved.");
    	String resultString = "{\"status\": %s, \"message\": \"%s\", \"cost\": %s}";
    	PrintWriter out = response.getWriter();
    	try{
    		// receive params (city name, number of items) -- in this format?
    		int quantity = Integer.parseInt(request.getParameter("quantity"));
    		String city = request.getParameter("city").toLowerCase();  // convert to lower case
    		System.out.println("Order Details: City - " + city + " Quantity - " + quantity);
    		// load XML, both in order
        	String cities = getServletConfig().getInitParameter("Cities");
        	String[] cityList = cities.split(",");
        	String prices = getServletConfig().getInitParameter("Prices");
        	String[] priceList = prices.split(",");
        	String basePrices = getServletConfig().getInitParameter("BasePrices");
        	String[] basePriceList = basePrices.split(","); 
        	// get corresponding city from cityList
        	Integer pos = null;
        	for (int i = 0; i < cityList.length; i++) {
        		if (city.equals(cityList[i])){
        			pos = i;
        			//break;
        		}
        	}	
        	/** return error if not found */
        	if(pos == null){
        		// set content type for response
            	response.setContentType("application/json");
            	// Return with a printwriter
            	out.print(String.format(resultString, "false", "City not available for shipping.", "0.0"));
            	System.out.println("Invalid shipping city address.");
        	} else {
            	/** Otherwise, calculate shippping price and return */
            	// get corresponding price from priceList and convert
            	String shippingPriceString = priceList[pos];
            	String baseShippingPriceString = basePriceList[pos];
            	Double shippingPriceVal = Double.parseDouble(shippingPriceString);
            	Double baseShippingPriceVal = Double.parseDouble(baseShippingPriceString);
        		// multiply price by number of items
            	Double result = quantity*shippingPriceVal + baseShippingPriceVal;
        		// set content type for response
            	response.setContentType("application/json");
            	// Return with a printwriter
            	out.print(String.format(resultString, "true", "Success", result.toString()));
            	System.out.println("Shipping cost for order: " + result);
        	}
    	} catch (Exception e) {
    		// set content type for response
        	response.setContentType("application/json");
        	// Return with a printwriter
        	out.print(String.format(resultString, "false", "Error parsing parameters", "0.0"));
        	System.out.println("Server Error - invalid params.");	
    	}

    	System.out.println("Finished processing order's shipping cost.");
    	out.flush();
	}
	
}