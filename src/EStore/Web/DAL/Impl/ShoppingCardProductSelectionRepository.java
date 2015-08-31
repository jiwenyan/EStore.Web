package EStore.Web.DAL.Impl;

import DAL.Framework.Repository;
import DAL.Framework.UnitOfWork;
import EStore.Web.DAL.IShoppingCardProductSelectionRepository;
import EStore.Web.Model.ShoppingCardProductSelection;

public class ShoppingCardProductSelectionRepository extends Repository<ShoppingCardProductSelection> implements IShoppingCardProductSelectionRepository {

	public ShoppingCardProductSelectionRepository(
			Class<ShoppingCardProductSelection> typeArgument,UnitOfWork unitOfWork) {
		super(typeArgument, unitOfWork);
	}

}
