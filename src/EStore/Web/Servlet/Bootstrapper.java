package EStore.Web.Servlet;

import javax.servlet.http.HttpServlet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrapper extends HttpServlet {
	private static ClassPathXmlApplicationContext context;
	public Bootstrapper(){
		Bootstrapper.context = new ClassPathXmlApplicationContext("Beans.xml");	
	}
	
	public static Object getObject(String className) {
		return context.getBean(className);
	}
}
