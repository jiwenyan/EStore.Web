package EStore.Web.Utils;

import EStore.Web.Model.Category;

public class Comparator {
	public boolean EqualsToId(Integer a, Category b){
		return a==b.getId();
	}
}
