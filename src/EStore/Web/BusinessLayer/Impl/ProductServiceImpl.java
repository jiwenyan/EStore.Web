package EStore.Web.BusinessLayer.Impl;

import java.util.List;

import DAL.Framework.UnitOfWork;
import EStore.Web.BusinessLayer.IProductService;
import EStore.Web.DAL.IProductRepository;
import EStore.Web.DAL.Impl.ProductRepository;
import EStore.Web.Model.Category;
import EStore.Web.Model.Mark;
import EStore.Web.Model.Product;

public class ProductServiceImpl implements IProductService {

	private IProductRepository productRepository;
	private UnitOfWork unitOfWork;
	
	public ProductServiceImpl(UnitOfWork unitOfWork){
		this.unitOfWork = unitOfWork;
		this.productRepository = new ProductRepository(unitOfWork);
	}
	
	@Override
	public void CreateProduct(Product toCreate, Category categoryOfProduct,Mark markOfProduct) {
		if(markOfProduct.getId()==0){
			throw new IllegalArgumentException("The id of the mark for the product is 0");
		}
		if(categoryOfProduct.getId()==0){
			throw new IllegalArgumentException("The id of the category for the product is 0");
		}
		
		toCreate.setCategory(categoryOfProduct);
		toCreate.setMark(markOfProduct);
		unitOfWork.beginTransaction();
		
		this.productRepository.Create(toCreate);
		
		unitOfWork.commit();
	}

	@Override
	public List<Product> getProductList() {
		return this.productRepository.Read();
	}

	@Override
	public List<Product> getProductList(Category category) {
		if(category.getId()==0){
			throw new IllegalArgumentException("Id of the category is 0");
		}
		return this.productRepository.getProductListByCategoryId(category.getId());
	}

	@Override
	public List<Product> getProductList(Mark mark) {
		if(mark.getId()==0){
			throw new IllegalArgumentException("Id of the mark is 0");
		}
		return this.productRepository.getProductListByMarkId(mark.getId());
	}

}
