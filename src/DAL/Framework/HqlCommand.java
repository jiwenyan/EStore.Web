package DAL.Framework;


public class HqlCommand extends Predicate{
	private String root;
	private String joins;
	private String footer;
	private boolean count;
	private String distinct;
	
	private boolean isDelete;
	private boolean isSelect;
	
	private HqlCommand(boolean isDelete){
		this.isDelete = isDelete;
		this.isSelect = !isDelete;
		joins = "";
		footer="";
	}
	
	public HqlCommand(String entityName) {
		super();
		root = "from "+entityName+" as obj";
		joins = "";
		footer="";
		isSelect = true;
		isDelete = false;
	}
	
	@SuppressWarnings("rawtypes")
	public HqlCommand(Class entityType){
		super();
		root = " from "+getEntityName(entityType)+" as obj ";
		joins = "";
		footer="";
		isSelect = true;
		isDelete = false;
	}
	
	public HqlCommand innerJoin(String toJoin){
		joins += "inner join "+toJoin+"";
		return this;
	}
	public HqlCommand innerJoinFetch(String toJoin){
		joins += "inner join fetch "+toJoin+" ";
		return this;
	}
	
	
	public HqlCommand leftOuterJoin(String toJoin){
		joins+="left outer join "+toJoin+" ";
		return this;
	}
	public HqlCommand leftOuterJoinFetch(String toJoin){
		joins+="left outer join fetch "+toJoin+" ";
		return this;
	}
	
	
	public HqlCommand rightOuterJoin(String toJoin){
		joins+="right outer join "+toJoin+" ";
		return this;
	}
	public HqlCommand rightOuterJoinFetch(String toJoin){
		joins+="right outer join fech "+toJoin+" ";
		return this;
	}
	
	
	/**
	 * @param orderKey
	 * @return
	 */
	public HqlCommand orderBy(String orderKey){
		if(footer.contains("order by")){}
		else{
			footer += "order by "+orderKey+" ";
		}
		return this;
	}
	/**
	 * @param orderKeys
	 * @return
	 */
	public HqlCommand orderBy(String... orderKeys){
		if(footer.contains("order by")){}
		else{
			if(orderKeys==null){}
			else if(orderKeys.length>0){
				footer += "order by ";
				for(String orderKey : orderKeys){
					footer += orderKey+" ";		
				}
			}
		}
		return this;
	}
	
	public HqlCommand Count(){
		count = true;
		return this;
	}
	public HqlCommand Distinct(String... toDistincts) throws Exception{
		if(distinct!=null){
			throw new Exception("Distinct can be used only once");
		}
		
		
		if(distinct!=null && 
		   distinct.length()>0){
			throw new IllegalStateException( "There cannot be two distincts in one query." );
		}
		
		StringBuilder distinctQuery = new StringBuilder();
		
		if(toDistincts==null || 
		   toDistincts.length==0){
			distinctQuery.append("obj");
		}
		else{
			for(Object distinctClmn : toDistincts){
				distinctQuery.append(distinctClmn.toString());
				distinctQuery.append(",");
			}
			distinctQuery.delete(distinctQuery.length()-1, distinctQuery.length());
		}
		
		this.distinct = "distinct "+distinctQuery.toString();
				
		return this;
	}
	
	public HqlCommand groupBy(String orderKey){
		if(footer.contains("group by")){}
		else{
			footer += "group by "+orderKey+" ";
		}
		return this;
	}
	public HqlCommand groupBy(String... orderKeys){
		if(footer.contains("group by")){}
		else{
			if(orderKeys==null){}
			else if(orderKeys.length>0){
				footer += "group by ";
				for(String orderKey : orderKeys){
					footer += orderKey+" ";		
				}
			}
		}
		return this;
	}
	
	public String GetQueryString(){
		
		if(isDelete){
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(root+" ");
			queryBuilder.append(joins+" ");
			queryBuilder.append(super.getPredicate()+" ");
			queryBuilder.append(footer);
			
			return queryBuilder.toString();
		}
		else if(isSelect){
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select ");
			queryBuilder.append(count?"count(":" ");
			queryBuilder.append(distinct == null ? "obj " : distinct+" ");
			queryBuilder.append(count?")":" ");
			queryBuilder.append(root+" ");
			queryBuilder.append(joins+" ");
			queryBuilder.append(super.getPredicate()+" ");
			queryBuilder.append(footer);
			
			return queryBuilder.toString();
		}
		else{
			return "";
		}

	}
	
	
	private static String getEntityName(@SuppressWarnings("rawtypes") Class entityType){
		String name = entityType.getName();
		
		@SuppressWarnings("unchecked")
		javax.persistence.Entity annoEntity = (javax.persistence.Entity) entityType.getAnnotation(javax.persistence.Entity.class);
		if(annoEntity!=null){
			if(!annoEntity.name().isEmpty()){
				name = annoEntity.name();
			}
		}
		return name;
	}
	
	public static HqlCommand Delete(@SuppressWarnings("rawtypes") Class entityType, Predicate predicate){
		String entityName = getEntityName(entityType);
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("delete from "+entityName);
		HqlCommand toReturn = new HqlCommand(true);
		toReturn.root = queryBuilder.toString();
		toReturn.And(predicate);
		return toReturn;
	}

}
