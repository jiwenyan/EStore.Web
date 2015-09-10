package EStore.Web.BusinessLayer.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import EStore.Web.BusinessLayer.ICategoryBL;
import EStore.Web.DAL.ICategoryRepository;
import EStore.Web.Model.Category;
import EStore.Web.Model.Product;

public class CategoryBLImpl implements ICategoryBL {
	@Autowired
	private ICategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewCategory(Category toCreate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProductToCategory(Category category, Product toAdd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> getProductByCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
