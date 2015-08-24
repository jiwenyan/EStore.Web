package EStore.Web.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodProxy<T, R> {

	private Method toExecute;
	private T target;
	private List<Object> primitiveParameters;

	public MethodProxy(Method toExecute, T target) {
		this.toExecute = toExecute;
		this.target = target;
		primitiveParameters = new ArrayList<Object>();
	}

	@SuppressWarnings("unchecked")
	public R execute(Object... arguments) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		List<Object> parameters = new ArrayList<Object>(primitiveParameters);
		parameters.addAll(Arrays.asList(arguments));
		R invoke = (R) toExecute.invoke(target, parameters.toArray());
		return invoke;
	}
	
	public MethodProxy<T,R> registerPrimitiveParameters(Object param){
		primitiveParameters.add(param);
		return this;
	}

}
