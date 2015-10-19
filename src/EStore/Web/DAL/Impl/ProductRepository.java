package EStore.Web.DAL.Impl;

import java.util.List;

import org.hibernate.Query;

import DAL.Framework.HqlCommand;
import DAL.Framework.Predicate;
import DAL.Framework.Repository;
import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.IProductRepository;
import EStore.Web.Model.Product;

public class ProductRepository extends Repository<Product> implements IProductRepository {

	public ProductRepository(UnitOfWork unitOfWork) {
		super(Product.class, unitOfWork);
	}

	@Override
	public List<Product> getProductListByCategoryId(int categoryId) {
		HqlCommand command = new HqlCommand(Product.class);
		command.innerJoin("category",null);
		command.And(Predicate.Eq("category_id", categoryId));
		
		Query query = this.getQuery(command);
		
		@SuppressWarnings("unchecked")
		List<Product> products = query.list();
		
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductListByMarkId(int markId) {
		HqlCommand command = new HqlCommand(Product.class);
		command.And(Predicate.Eq("mark_id",markId));
		Query query = this.getQuery(command);
		return query.list();
	}

}
