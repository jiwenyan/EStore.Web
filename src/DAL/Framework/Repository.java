package DAL.Framework;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javassist.NotFoundException;

import org.hibernate.Query;
import org.hibernate.Transaction;

public abstract class Repository <T extends Entity> implements IRepository<T> {
	protected UnitOfWork _unitOfWork;
	protected Class<T> typeArgument;
	
	public Repository(Class<T> typeArgument,UnitOfWork unitOfWork){
		this._unitOfWork = unitOfWork;
		this.typeArgument = typeArgument;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T Create(T toCreate) {
		T created = (T)this._unitOfWork.getSession().merge(toCreate);
		return created;
	}

	@Override
	public List<T> Read() {
		HqlCommand command = new HqlCommand(typeArgument);
		
		Transaction transaction = this._unitOfWork.getSession().beginTransaction();
		
		Query query = getQuery(command);
		@SuppressWarnings("unchecked")
		List<T> result = query.list();
		
		transaction.commit();
		return result;
	}

	@Override
	public T ReadById(int id) throws NotFoundException {
		// TODO Auto-generated method stub
		HqlCommand command = new HqlCommand(typeArgument);
		command.And(Predicate.like("Id", id));
		Query query = getQuery(command);
		@SuppressWarnings("unchecked")
		List<T> result = query.list();
		if(result.size()==0){
			throw new NotFoundException("The data corresponding to the given id is not found in database.");
		}
		return result.get(0);
	}

	@Override
	public void Update(T toUpdate) {
		this._unitOfWork.getSession().update(toUpdate);
	}

	@Override
	public boolean Delete(T toDelete) {
		try{
		this._unitOfWork.getSession().delete(toDelete);
		return true;
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public boolean Delete(int idToDelete) {
		HqlCommand deleteQuery = HqlCommand.Delete(typeArgument, Predicate.Eq("Id", idToDelete));
		Query query = getQuery(deleteQuery);
		query.executeUpdate();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	protected Class<T> getType(){
		return (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private Query getQuery(HqlCommand command){
		Query query = this._unitOfWork.getSession().createQuery(command.GetQueryString());
		
		for(PredicateParameter parameter : command.getParameters()){
			query.setParameter(parameter.getParameterName(), parameter.getParameterValue());
		}
		
		return query;
	}

}
