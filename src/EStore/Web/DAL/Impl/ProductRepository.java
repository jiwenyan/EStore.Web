package EStore.Web.DAL.Impl;

import DAL.Framework.Repository;
import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.IProductRepository;
import EStore.Web.Model.Product;

public class ProductRepository extends Repository<Product> implements IProductRepository {

	public ProductRepository(UnitOfWork unitOfWork) {
		super(Product.class, unitOfWork);
		// TODO Auto-generated constructor stub
	}

}
