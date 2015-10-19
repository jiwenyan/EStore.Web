package EStore.Web.BusinessLayer;

import java.util.List;

import EStore.Web.Model.Category;
import EStore.Web.Model.Mark;
import EStore.Web.Model.Product;

public interface ICategoryService {
	public List<Category> getAllCategories();
	public void createNewCategory(Category toCreate);
	public void addProductToCategory(Category category,Product toAdd);
	public List<Product> getProductByCategory(Category category);
	public List<Mark> getMarkByCategory(Category category);
}
