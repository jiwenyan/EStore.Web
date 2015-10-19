package EStore.Web.DAL.Test;

import java.math.BigDecimal;
import java.util.List;

import javassist.NotFoundException;

import org.hibernate.AnnotationException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.ICategoryRepository;
import EStore.Web.DAL.IProductRepository;
import EStore.Web.DAL.Impl.CategoryRepository;
import EStore.Web.DAL.Impl.ProductRepository;
import EStore.Web.Model.Category;
import EStore.Web.Model.Mark;
import EStore.Web.Model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/Resources/Spring/Beans.xml")
public class ProductRepositoryTest {
	private IProductRepository repository;
	private UnitOfWork unitOfWork;
	
	//@Mock
	private ICategoryRepository categoryRepository;
	
	//@Autowired
	//private ApplicationContext context;
	
	@Autowired
	public void initRepositories(UnitOfWork unitOfWork){
		this.unitOfWork=unitOfWork;
		repository = new ProductRepository(unitOfWork);
		categoryRepository = new CategoryRepository(unitOfWork);
	}
		
	@Test
	public void readTest(){
		//given
		
		//when
		List<Product> products = repository.Read();
		//then
		Assert.assertNotNull(products);
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void shouldFailIfMarkIsNotDefinedWhenCreate() throws NotFoundException{
		//given
		Product product = new Product();// context.getBean(Product.class);
		product.setName("football");
		product.setDescription("product testing");
		product.setPrice(BigDecimal.valueOf(12.5));
		product.setCategory(getAttachedCategoy());
		
		//when
		unitOfWork.beginTransaction();
		repository.Create(product);
		unitOfWork.commit();
		//then
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void shouldFailIfCategoryInNotDefineWhenCreate(){
		//given
		Product product = new Product();// context.getBean(Product.class);
		product.setName("football");
		product.setDescription("product testing");
		product.setPrice(BigDecimal.valueOf(12.5));
		product.setMark(getAttachedMark());
		
		//when
		unitOfWork.beginTransaction();
		repository.Create(product);
		unitOfWork.commit();
		//then
	}
	
	@Test
	public void shouldInsertProductInAllForeignKeyAreDefined(){
		//given
		Product product = new Product();// context.getBean(Product.class);
		product.setName("football");
		product.setDescription("product testing");
		product.setPrice(BigDecimal.valueOf(12.5));
		product.setMark(getAttachedMark());
		product.setCategory(getAttachedCategoy());
		
		//when
		unitOfWork.beginTransaction();
		repository.Create(product);
		unitOfWork.commit();
		//then
	}
	
	@Test
	public void sholdGetProductByCategoryId(){
		List<Product> products = repository.getProductListByCategoryId(1);
		for(Product product : products){
			System.out.println(product.getId()+";");
			System.out.println(product.getName()+";");	
			System.out.println(product.getPrice()+";");	
		}
		
	}
	
	@Test
	public void test(){
		List<Product> products = repository.getProductListByMarkId(1);
		for(Product product : products){
			System.out.println(product.getId()+";");
			System.out.println(product.getName()+";");	
			System.out.println(product.getPrice()+";");	
		}
	}
	
	private Product createProduct(){
		Product product = new Product();// context.getBean(Product.class);
		product.setName("football");
		product.setDescription("product testing");
		product.setPrice(BigDecimal.valueOf(12.5));
		return product;
	}
	
	private Category getAttachedCategoy(){
		Category category = new Category();
		category.setId(1);
		return category;
	}
	
	private Mark getAttachedMark(){
		Mark mark = new Mark();
		mark.setId(1);
		return mark;
	}
	
}
