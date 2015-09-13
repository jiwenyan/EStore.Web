package EStore.Web.Model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import DAL.Framework.Entity;

@javax.persistence.Entity
@Table(name = "productDetails")
public class ProductDetail extends Entity {
	@Column(columnDefinition="varchar(15)")
	private String color;
	@Column(columnDefinition="varchar(15)")
	private String size;
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
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
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
