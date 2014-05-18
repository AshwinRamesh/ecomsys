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

import products.model.*;	
import FlickrAPI.*;

/**
 * @author Mojtaba
 *
 */
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SearchResult() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String q = request.getParameter("q");
		
		FlickrRestApi flAPI = new FlickrRestApi();
		List<FlickrPhoto> photos = flAPI.getPhotos(q);
		getServletContext().setAttribute("photos", photos);
		
		RequestDispatcher view = request.getRequestDispatcher("searchResult.jsp");
		view.forward(request,response);
	}


}
