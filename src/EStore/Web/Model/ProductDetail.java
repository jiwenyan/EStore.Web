package EStore.Web.Model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import DAL.Framework.Entity;

public class ProductDetail extends Entity {
	private String color;
	private int size;
	private int stock;
	@ManyToOne
	@Cascade(value=CascadeType.SAVE_UPDATE)
	@JoinColumn(name="product_id")
	private Product product;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setProduct(Product product){
		this.product=product;
	}
	public Product getProduct(){
		return this.product;
	}
}
