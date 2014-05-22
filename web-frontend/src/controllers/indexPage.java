/**
 * 
 */
package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Mojtaba
 *
 */
public class indexPage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	public indexPage() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String remoteUser = request.getRemoteUser();
		System.out.println(remoteUser);
		if(remoteUser == null){
			RequestDispatcher view = request.getRequestDispatcher("login.html");
			view.forward(request,response);
		}else if(remoteUser.equals("admin")){
			response.sendRedirect("AllOrders");
		}else{
			response.sendRedirect("SearchForm");
		}
		
	}

}
