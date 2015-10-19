package EStore.Web.Model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import DAL.Framework.Entity;

@javax.persistence.Entity
@Table(name="mark")
public class Mark extends Entity {
@Column(columnDefinition="char(255)")
private String name;
@Column(columnDefinition="char(50)")
private String country;
@Column(name="logo",columnDefinition="varchar(1024)")
private String url;
@OneToMany(targetEntity=Product.class,mappedBy="mark",fetch=FetchType.LAZY)
private Set<Product> products;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
}
