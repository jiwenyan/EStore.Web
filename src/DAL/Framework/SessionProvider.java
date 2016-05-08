package DAL.Framework;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jndi.JndiException;
import org.hibernate.service.ServiceRegistry;

import EStore.Web.Utils.Lock;

//import EStore.Web.Utils.Lock;

public final class SessionProvider {
	private static Lock _lock = new Lock();
	private static Lock sessionDirectoryLock = new Lock();
	private static boolean _configured;
	private static Configuration configuration;
	private static SessionFactory sessionFactory;
	private static Map<String,Session> sessionDirectory;
	
	public static Session getSession(){
		_lock.lock();
		Connection conn=null;
		if(!_configured){
			Configure();
			sessionFactory = getSessionFactory();
			Context initContext;
			try {
				initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource)envContext.lookup("jdbc/ecomData");
				conn = ds.getConnection();
			} catch (NamingException e) {
				System.err.println(e.getMessage());
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
			
		}
		_lock.unlock();
		

		SessionBuilder sBuilder = sessionFactory.withOptions();
		
		
		return  sBuilder.connection(conn).openSession();
	}
	
	public static Session getSessionById(String sessionId){
		sessionDirectoryLock.lock();
		try{
			if(sessionId==null||sessionId.isEmpty()){
				return getSession();
			}
			if(sessionDirectory == null){
				sessionDirectory = new HashMap<String,Session>();
			}
			if(sessionDirectory.containsKey(sessionId)){
				return sessionDirectory.get(sessionId);
			}
			else{
				Session newSession = getSession();
				sessionDirectory.put(sessionId, newSession);
				return newSession;
			}
		}
		finally{
			sessionDirectoryLock.unlock();
		}
	}
	
	private static SessionFactory getSessionFactory(){
		SessionFactory factory=null;
		try{
			factory= configuration.buildSessionFactory(getServiceRegistry());
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}		
		return factory;
	}
	private static ServiceRegistry getServiceRegistry(){
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
												.applySettings(configuration.getProperties()).build();
		return serviceRegistry;
	}
	private static void Configure(){
		configuration = new Configuration();
		configuration.configure();
		_configured=true;
	}
	
}
