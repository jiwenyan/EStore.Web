package EStore.Web.DAL.Test;

import java.util.List;

import org.hibernate.StaleStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.IMarkRepository;
import EStore.Web.DAL.Impl.MarkRepository;
import EStore.Web.Model.Mark;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/Resources/Spring/Beans.xml")
public class MarkRepositoryTest {
	
	private IMarkRepository markRepository;
	private UnitOfWork unitOfWork;
	
	@Autowired
	public void initRepository(UnitOfWork uow){
		unitOfWork = uow;
		markRepository = new MarkRepository(uow);
	}
	
	
	@Test
	public void shouldReadAllMarksFromDatabase(){
		//given
		
		//when
		List<Mark> marks =  markRepository.Read();
		//then
		Assert.assertNotNull(marks);
		Assert.assertNotEquals(marks.size(), 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldGetExceptionIfTryToUpdateANull(){
		//given
		
		//when
		markRepository.Update(null);
		
		//then
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldGetExceptionIfCreateNull(){
		//given
		
		//when
		markRepository.Create(null);
		
		//then
	}
	
	@Test(expected = StaleStateException.class)
	public void shouldGetExceptionIfUpdateAnNotExistingMark(){
		//given
		Mark mark = new Mark();
		
		//when
		unitOfWork.beginTransaction();
		markRepository.Update(mark);
		unitOfWork.commit();
		
		//then
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void shouldGetExceptionIfObjectToCreateInEmpty(){
		//given
		Mark mark = new Mark();
		//when
		unitOfWork.beginTransaction();
		markRepository.Create(mark);
		unitOfWork.commit();
		//then
	}
	
	@Test
	public void shouldUpdateTheMark(){		
		List<Mark> marks =  markRepository.Read();
		Mark toUpdate = marks.get(0);
		
		toUpdate.setName("test");
		
		unitOfWork.beginTransaction();
		markRepository.Update(toUpdate);
		unitOfWork.commit();
	}
	
	@Test
	public void shouldCreateTheMark(){
		Mark mark = new Mark();
		mark.setName("unit test created");
		mark.setUrl("test url");
		
		unitOfWork.beginTransaction();
		markRepository.Create(mark);
		unitOfWork.commit();
	}
	
	@Test
	public void shouldDoMultiDelete(){
		List<Mark> marks = markRepository.Read();
		
		unitOfWork.beginTransaction();
		for(int i = 1 ; i<marks.size();i++){
			Mark todelete = marks.get(i);
			markRepository.Delete(todelete);
		}
		unitOfWork.commit();
	}
	
	@Test
	public void shouldGetMarkByCategoryId(){
		List<Mark> marks = markRepository.getMarkByCategoryId(1);
		for(Mark mark : marks){
			System.out.println(mark.getName());
		}
	}
}
