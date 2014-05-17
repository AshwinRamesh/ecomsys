import java.io.IOException;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    	// get corresponding price from priceList and convert
    	String shippingPriceString = priceList[pos];
    	int shippingPriceVal = Integer.parseInt(shippingPriceString);
		// multiply price by number of items
    	int result = quantity*shippingPriceVal;
		// return this price -- what format do we want this in? We should probably leave
    	// it in the user's session data when it gets back to the main tomcat server
    	HttpSession session = request.getSession();
    	session.setAttribute("somevariable", result);
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// not needed
	}

}