package DAL.Framework;

import java.util.LinkedList;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UnitOfWork {
	private Session _session;
	public Session getSession() {
		return _session;
	}

	private LinkedList<Transaction> transactionList;
	
	public UnitOfWork(){
		_session = SessionProvider.getSession();
		transactionList = new LinkedList<Transaction>();
	}
	
	public void beginTransaction(){
		Transaction transaction  = _session.beginTransaction();
		transactionList.addLast(transaction);
	}
	
	
	public void commit(){
		transactionList.getLast().commit();
		transactionList.removeLast();
	}
	
	public void CommitAll(){
		int size = transactionList.size();
		for(int i = 0 ; i<size;i++){
			commit();
		}
	}
}
