package EStore.Web.BusinessLayer;

import java.util.List;

import EStore.Web.Model.Category;
import EStore.Web.Model.Mark;

public interface IMarkService {
	public List<Mark> getMarkList();
	public List<Mark> getMarkListByCategory(Category category);
}
