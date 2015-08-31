package EStore.Web.DAL.Impl;

import DAL.Framework.Repository;
import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.IProductRepository;
import EStore.Web.Model.Product;

public class ProductRepository extends Repository<Product> implements IProductRepository {

	public ProductRepository(Class<Product> typeArgument, UnitOfWork unitOfWork) {
		super(typeArgument, unitOfWork);
		// TODO Auto-generated constructor stub
	}

}
