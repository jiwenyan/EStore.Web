package DAL.Framework;


import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
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
		if(!_configured){
			Configure();
			sessionFactory = getSessionFactory();
		}
		_lock.unlock();
		return sessionFactory.openSession();
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
		SessionFactory factory= configuration.buildSessionFactory(getServiceRegistry());
		return factory;
	}
	private static ServiceRegistry getServiceRegistry(){
		//Configure();
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
