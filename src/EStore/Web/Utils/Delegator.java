package EStore.Web.Utils;

import java.lang.reflect.Method;

public class Delegator<T,R> {
	private Class<T> type;
	private String methodName;
	
	public Delegator(Class<T> type, String methodName){
		this.type=type;
		this.methodName = methodName;
	}
	
	public MethodProxy<T,R> BuildProxy(T object){
		Method[] methods = this.type.getMethods();
		Method foundMethod=null;
		for(Method mtd : methods){
			if(mtd.getName().equals(methodName)){
				foundMethod = mtd;
				break;
			}
		}
		return new MethodProxy<T,R>(foundMethod,object);
	}
}
