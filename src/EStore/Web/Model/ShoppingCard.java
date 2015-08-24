package EStore.Web.Model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import DAL.Framework.Entity;

public class ShoppingCard extends Entity {
	private Timestamp createdAt;
	private String createdBy;
	private int status;
	@OneToMany
	@Cascade(value=CascadeType.SAVE_UPDATE)
	@JoinColumn(name="shoppingCard_id")
	private Set<ShoppingCardProductSelection> selectedProductList;
	
	public ShoppingCard(){
		selectedProductList = new HashSet<ShoppingCardProductSelection>();
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public ShoppingCardStatus getStatus() {
		return ShoppingCardStatus.values()[this.status];
	}
	public void setStatus(ShoppingCardStatus status) {
		this.status = status.ordinal();
	}
	public Set<ShoppingCardProductSelection> getSelectedProductList() {
		return selectedProductList;
	}
	public void setSelectedProductList(
			Set<ShoppingCardProductSelection> selectedProductList) {
		this.selectedProductList = selectedProductList;
	}
	
}
