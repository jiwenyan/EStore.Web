package EStore.Web.Utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class Lock implements java.util.concurrent.locks.Lock {

	private boolean locked=false;
	private Thread lockedBy;

	@Override
	public synchronized void lock() {
		try {
			Thread currentThread = Thread.currentThread();
			if (locked && lockedBy != currentThread) {
				this.wait();
			}
			locked = true;
			lockedBy = currentThread;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long arg0, TimeUnit arg1)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized void unlock() {
		if(locked&&
			lockedBy == Thread.currentThread()){
			locked = false;
			lockedBy = null;
			this.notify();
		}

	}

}
