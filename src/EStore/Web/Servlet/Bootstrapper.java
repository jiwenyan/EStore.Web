package EStore.Web.Servlet;

import javax.servlet.http.HttpServlet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrapper extends HttpServlet {
	public Bootstrapper(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Resouces.Spring/Beans.xml");	
	}
}
