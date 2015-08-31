package EStore.Web.Model;

import javax.persistence.Column;
import javax.persistence.Table;

import DAL.Framework.Entity;

@javax.persistence.Entity
@Table(name="category")
public class Category extends Entity {
	@Column(name="name",columnDefinition="char(255)")
	private String Name;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
}
