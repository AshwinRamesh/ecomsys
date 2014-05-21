import java.io.IOException;
import java.io.PrintWriter;



// import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// not needed
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// receive params (city name, number of items) -- in this format?
		String city = request.getParameter("city");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
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
    	/** return error if not found */
    	if(pos == 0){
    		String result = "Error";
    		// convert to JSON
    		JSONObject resultObj = new JSONObject();
    		try {
				resultObj.put("shipping", result);
			} catch (JSONException e) {
				e.printStackTrace();
			}
    		// set content type for response
        	response.setContentType("application/json");
        	// Return with a printwriter
        	PrintWriter out = response.getWriter();
        	out.print(result);
        	out.flush();
    	}
    	/** Otherwise, calculate shippping price and return */
    	// get corresponding price from priceList and convert
    	String shippingPriceString = priceList[pos];
    	int shippingPriceVal = Integer.parseInt(shippingPriceString);
		// multiply price by number of items
    	int result = quantity*shippingPriceVal;
		// convert to JSON
		JSONObject resultObj = new JSONObject();
		try {
			resultObj.put("shipping", result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// set content type for response
    	response.setContentType("application/json");
    	// Return with a printwriter
    	PrintWriter out = response.getWriter();
    	out.print(result);
    	out.flush();
    	
	}

}