package EStore.Web.DAL.Impl;

import DAL.Framework.Repository;
import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.ICategoryRepository;
import EStore.Web.Model.Category;

public class CategoryRepository extends Repository<Category> implements ICategoryRepository {

	public CategoryRepository(UnitOfWork unitOfWork) {
		super(Category.class, unitOfWork);
	}
}
