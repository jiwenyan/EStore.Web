package EStore.Web.BusinessLayer;

import java.util.List;

import EStore.Web.Model.Category;
import EStore.Web.Model.Product;

public interface ICategoryBL {
	public List<Category> getAllCategories();
	public void createNewCategory(Category toCreate);
	public void addProductToCategory(Category category,Product toAdd);
	public List<Product> getProductByCategory(Category category);
}
