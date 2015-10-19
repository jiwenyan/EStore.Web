package EStore.Web.DAL.Impl;

import java.util.List;

import org.hibernate.Query;

import DAL.Framework.HqlCommand;
import DAL.Framework.Predicate;
import DAL.Framework.Repository;
import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.IMarkRepository;
import EStore.Web.Model.Mark;

public class MarkRepository extends Repository<Mark> implements IMarkRepository {

	public MarkRepository(UnitOfWork unitOfWork) {
		super(Mark.class, unitOfWork);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mark> getMarkByCategoryId(int categoryId) {
		HqlCommand command = new HqlCommand(Mark.class);
		command.rightOuterJoin("products as prd",null);
		command.And(Predicate.Eq("category_Id", categoryId));
		
		Query query = this.getQuery(command);
		return query.list();
	}

}
