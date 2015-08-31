package EStore.Web.DAL.Impl;

import DAL.Framework.Repository;
import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.IShoppingCardRepository;
import EStore.Web.Model.ShoppingCard;

public class ShoppingCardRepository extends Repository<ShoppingCard> implements IShoppingCardRepository {

	public ShoppingCardRepository(Class<ShoppingCard> typeArgument,UnitOfWork unitOfWork) {
		super(typeArgument, unitOfWork);
	}

}
