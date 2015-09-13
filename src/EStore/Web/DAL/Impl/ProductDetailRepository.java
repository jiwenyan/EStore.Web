package EStore.Web.DAL.Impl;

import DAL.Framework.Repository;
import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.IProductDetailRepository;
import EStore.Web.Model.ProductDetail;

public class ProductDetailRepository extends Repository<ProductDetail> implements IProductDetailRepository{

	public ProductDetailRepository(UnitOfWork unitOfWork) {
		super(ProductDetail.class, unitOfWork);
	}

}
