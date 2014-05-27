import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
 */

/**
 * @author Mojtaba
 *
 */
public class AppServletContextListener implements ServletContextListener {

	/**
	 * 
	 */
	public AppServletContextListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Bye Bye!");
	}
 
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Starting...");	
	}
}
