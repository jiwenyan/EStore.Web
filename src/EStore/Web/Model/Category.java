package EStore.Web.Model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import DAL.Framework.Entity;

@javax.persistence.Entity
@Table(name="category")
public class Category extends Entity {
	@Column(name="name",columnDefinition="char(255)")
	private String Name;
	
	@OneToMany(targetEntity=Product.class,fetch=FetchType.LAZY,mappedBy="category")
	private Set<Product> products;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
