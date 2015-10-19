package EStore.Web.DAL;

import java.util.List;

import DAL.Framework.IRepository;
import EStore.Web.Model.Product;

public interface IProductRepository extends IRepository<Product> {
	public List<Product> getProductListByCategoryId(int categoryId);
	public List<Product> getProductListByMarkId(int markId);
}
