package EStore.Web.DAL.Test;

import org.junit.Before;
import org.junit.Test;

import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.IProductRepository;
import EStore.Web.DAL.Impl.ProductRepository;

public class ProductRepositoryTest {
	private IProductRepository repository;
	private UnitOfWork unitOfWork;
	
	@Before
	public void init(){
		unitOfWork = new UnitOfWork();
		repository = new ProductRepository(unitOfWork);
	}
	
	@Test
	public void test(){
		
	}
}
