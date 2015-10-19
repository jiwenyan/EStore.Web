package EStore.Web.BusinessLayer;

import java.util.List;

import EStore.Web.Model.Category;
import EStore.Web.Model.Mark;
import EStore.Web.Model.Product;

public interface IProductService {
	public void CreateProduct(Product toCreate, Category categoryOfProduct, Mark markOfProduct);
	public List<Product> getProductList();
	public List<Product> getProductList(Category category);
	public List<Product> getProductList(Mark mark);
}
