package EStore.Web.DAL;

import java.util.List;

import DAL.Framework.IRepository;
import EStore.Web.Model.Mark;

public interface IMarkRepository extends IRepository<Mark> {
	/**
	 * gets the list of mark by category id.
	 * @param categoryId
	 * @return
	 */
	public List<Mark> getMarkByCategoryId(int categoryId);
}
