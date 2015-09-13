package EStore.Web.DAL.Test;

import java.math.BigDecimal;
import java.util.List;

import javassist.NotFoundException;

import org.hibernate.AnnotationException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.ICategoryRepository;
import EStore.Web.DAL.IProductRepository;
import EStore.Web.DAL.Impl.CategoryRepository;
import EStore.Web.DAL.Impl.ProductRepository;
import EStore.Web.Model.Category;
import EStore.Web.Model.Product;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="/Resources/Beans.xml")
public class ProductRepositoryTest {
	private IProductRepository repository;
	private UnitOfWork unitOfWork;
	
	//@Mock
	private ICategoryRepository categoryRepository;
	
	//@Autowired
	//private ApplicationContext context;
	
	@Before
	public void init() throws NotFoundException{
		unitOfWork = new UnitOfWork();
		repository = new ProductRepository(unitOfWork);
		categoryRepository = new CategoryRepository(unitOfWork);
		//MockitoAnnotations.initMocks(this);
		
		//categoryRepository = Mockito.mock(CategoryRepository.class);
		//Mockito.when(categoryRepository.ReadById(Mockito.anyInt())).thenReturn(getAttachedCategoy());
	}
	
	private Category getAttachedCategoy(){
		Category category = new Category();
		category.setId(1);
		return category;
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
	public void test() throws NotFoundException{
		//given
		Product product = new Product();// context.getBean(Product.class);
		product.setName("football");
		product.setDescription("product testing");
		//product.setPrice(BigDecimal.valueOf(12.5));
		
		//when
		unitOfWork.beginTransaction();
		repository.Create(product);
		unitOfWork.commit();
		//then
	}
	
	
}
