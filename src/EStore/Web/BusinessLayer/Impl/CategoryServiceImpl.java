package EStore.Web.BusinessLayer.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import DAL.Framework.UnitOfWork;
import EStore.Web.BusinessLayer.ICategoryService;
import EStore.Web.DAL.ICategoryRepository;
import EStore.Web.DAL.IMarkRepository;
import EStore.Web.DAL.IProductRepository;
import EStore.Web.DAL.Impl.CategoryRepository;
import EStore.Web.DAL.Impl.MarkRepository;
import EStore.Web.DAL.Impl.ProductRepository;
import EStore.Web.Model.Category;
import EStore.Web.Model.Mark;
import EStore.Web.Model.Product;

public class CategoryServiceImpl implements ICategoryService {
	private ICategoryRepository categoryRepository;
	private IMarkRepository markRepository;
	private IProductRepository productRepository;
	
	private UnitOfWork unitOfWork;
	
	public CategoryServiceImpl(UnitOfWork unitOfWork){
		this.unitOfWork = unitOfWork;
		this.categoryRepository = new CategoryRepository(unitOfWork);
		this.markRepository = new MarkRepository(unitOfWork);
		this.productRepository = new ProductRepository(unitOfWork);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.Read();
	}

	@Override
	public void createNewCategory(Category toCreate) {
		categoryRepository.Create(toCreate);
	}

	@Override
	public void addProductToCategory(Category category, Product toAdd) {
		
		if(category.getId()==0){
			throw new IllegalArgumentException("The category id is not set");
		}
		
		category.getProducts().add(toAdd);
		
		this.unitOfWork.beginTransaction();
		
		this.categoryRepository.Update(category);
		
		this.unitOfWork.commit();
	}

	@Override
	public List<Product> getProductByCategory(Category category) {
		return this.productRepository.getProductListByCategoryId(category.getId());
	}

	@Override
	public List<Mark> getMarkByCategory(Category category) {
		return this.markRepository.getMarkByCategoryId(category.getId());
	}
	
	
}
