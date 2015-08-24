package EStore.Web.Model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import DAL.Framework.Entity;

public class Product extends Entity{
	private String name;
	private BigDecimal price;
	private String description;
	@ManyToOne
	@Cascade(value=CascadeType.SAVE_UPDATE)
	@JoinColumn(name="category_id")
	private Category category;
	@ManyToOne
	@Cascade(value=CascadeType.SAVE_UPDATE)
	@JoinColumn(name="mark_id")
	private Mark mark;
	@OneToMany
	@Cascade(value=CascadeType.SAVE_UPDATE)
	@JoinColumn(name="product_id")
	private Set<ProductDetail> productDetails;
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade(value=CascadeType.SAVE_UPDATE)
	@JoinColumn(name="product_id")
	private Set<ShoppingCardProductSelection> selections;

	public Product(){
		productDetails = new HashSet<ProductDetail>();
		selections = new HashSet<ShoppingCardProductSelection>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Mark getMark() {
		return mark;
	}
	public void setMark(Mark mark) {
		this.mark = mark;
	}
	public void setProductDetails(Set<ProductDetail> productDetails){
		this.productDetails=productDetails;
	}
	public Set<ProductDetail> getProductDetails(){
		return this.productDetails;
	}
	public Set<ShoppingCardProductSelection> getSelections() {
		return selections;
	}
	public void setSelections(Set<ShoppingCardProductSelection> selections) {
		this.selections = selections;
	}
}
