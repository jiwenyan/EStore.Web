package EStore.Web.DAL.Impl;

import DAL.Framework.Repository;
import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.IMarkRepository;
import EStore.Web.Model.Mark;

public class MarkRepository extends Repository<Mark> implements IMarkRepository {

	public MarkRepository(UnitOfWork unitOfWork) {
		super(Mark.class, unitOfWork);
	}

}
