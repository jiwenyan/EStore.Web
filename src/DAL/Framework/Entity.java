package DAL.Framework;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entity {
	@Id
	@Column(name="id", unique=true, nullable=false, precision=18, scale=0)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int _id;
	
	public int getId(){
		return this._id;
	}
	public void setId(int id){
		this._id=id;
	}

}
