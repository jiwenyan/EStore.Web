package EStore.Web.Model;

import DAL.Framework.Entity;

public class Mark extends Entity {
private String name;
private String country;
private String url;

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
