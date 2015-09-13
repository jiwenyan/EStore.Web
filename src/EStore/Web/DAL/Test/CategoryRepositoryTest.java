package EStore.Web.DAL.Test;

import java.util.List;

import javassist.NotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.ICategoryRepository;
import EStore.Web.DAL.Impl.CategoryRepository;
import EStore.Web.Model.Category;
import EStore.Web.Utils.Comparator;
import EStore.Web.Utils.Delegator;
import EStore.Web.Utils.ListUtils;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="/Resources/Beans.xml")
public class CategoryRepositoryTest {
	private ICategoryRepository repository;
	private UnitOfWork uow;
	
	//@Autowired
	//private ApplicationContext appContext;
	
	@Before
	public void init(){
		uow = new UnitOfWork();
		repository = new CategoryRepository(uow);
		//repository = (ICategoryRepository)appContext.getBean("categoryRepository");
	}
	
	@Test
	public void shouldGetTheListOfCategory(){
		List<Category> category = repository.Read();
		Assert.assertTrue(category.size()>0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void shouldGetIllegalArgumentExceptionIfTheObjectToCreateIsNull(){
		repository.Create(null);
	}
	@Test
	public void shouldInsertIntoDB(){
		//given
		int expected = repository.Read().size()+1;
		Category toCreate = createCategory();
		//when
		uow.beginTransaction();
		Category created = repository.Create(toCreate);
		uow.commit();
		//then
		int actual = repository.Read().size();
		Assert.assertEquals(expected, actual);
		System.out.println(created.getId());
	}
	
	@Test
	public void shoudUpdateTheEntity() throws NotFoundException{
		//given
		int id = 1;
		Category beforeUpdate = repository.ReadById(id);
		String expected = beforeUpdate.getName()+"s";
		//when
		beforeUpdate.setName( expected );
		uow.beginTransaction();
		repository.Update(beforeUpdate);
		uow.commit();
		//then
		Category after = repository.ReadById(id);
		Assert.assertEquals(expected, after.getName());
	}
	
	@Test
	public void shoudlSuccessToDelete() throws NotFoundException{
		Category toCreate = createCategory();
		uow.beginTransaction();
		Category created = repository.Create(toCreate);
		uow.commit();
		int idToDelete = created.getId();
		Assert.assertNotEquals(idToDelete, 0);
		Category toDelete = repository.ReadById(idToDelete);
		Assert.assertNotNull(toDelete);
		uow.beginTransaction();
		repository.Delete(idToDelete);
		uow.commit();
		//int idToDelete = 19;
		List<Category> categories = repository.Read();
		List<Category> found = ListUtils.filterByPredicate(categories,new Delegator<Comparator,Boolean>(Comparator.class,"EqualsToId")
																			.BuildProxy(new Comparator())
																			.registerPrimitiveParameters(idToDelete));
		Assert.assertEquals(found.size(), 0);
	}
	
	
	private Category createCategory(){
		Category obj = new Category();
		
		obj.setName("Shoes");
		
		return obj;
	}
}
