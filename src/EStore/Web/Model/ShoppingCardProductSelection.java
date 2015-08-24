package EStore.Web.Model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import DAL.Framework.Entity;

public class ShoppingCardProductSelection extends Entity {
	@ManyToOne
	@Cascade(value=CascadeType.SAVE_UPDATE)
	@JoinColumn(name="shoppingCard_id")
	private ShoppingCard shoppingCardId;

	@ManyToOne
	@Cascade(value=CascadeType.SAVE_UPDATE)
	@JoinColumn(name="product_id")
	private Product product;
	private int quantity;
	
	public ShoppingCard getShoppingCardId() {
		return shoppingCardId;
	}
	public void setShoppingCardId(ShoppingCard shoppingCardId) {
		this.shoppingCardId = shoppingCardId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
