package EStore.Web.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public final class ListUtils {
	public static <T> List<T> filterByPredicate(List<T> toFilter, MethodProxy<Comparator,Boolean> predicate){
		List<T> filterd = new ArrayList<T>();
		
		for(T obj : toFilter){
			try {
				if(predicate.execute(obj)){
					filterd.add(obj);
				}
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getStackTrace());
			}
		}
		return filterd;
	}
}
