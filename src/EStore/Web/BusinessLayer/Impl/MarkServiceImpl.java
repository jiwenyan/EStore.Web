package EStore.Web.BusinessLayer.Impl;

import java.util.List;

import DAL.Framework.UnitOfWork;
import EStore.Web.BusinessLayer.IMarkService;
import EStore.Web.DAL.IMarkRepository;
import EStore.Web.DAL.Impl.MarkRepository;
import EStore.Web.Model.Category;
import EStore.Web.Model.Mark;

public class MarkServiceImpl implements IMarkService {

	private IMarkRepository markRepository;
	private UnitOfWork unitOfWork;
	
	public MarkServiceImpl(UnitOfWork unitOfWork){
		markRepository = new MarkRepository(unitOfWork);
		this.unitOfWork = unitOfWork;
	}
	
	@Override
	public List<Mark> getMarkList() {
		return this.markRepository.Read();
	}

	@Override
	public List<Mark> getMarkListByCategory(Category category) {
		return this.markRepository.getMarkByCategoryId(category.getId());
	}

}
