package DAL.Framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PredicateParameter {
	private String parameterName;
	private Object parameterValue;
	private List<Object> parameterValues;
	
	public PredicateParameter(String name){
		this.parameterName = name;
	}
	public PredicateParameter(String name, Object value){
		this.parameterName = name;
		this.parameterValue = value;
		this.parameterValues = new ArrayList<Object>();
		this.parameterValues.add(value);
	}
	public PredicateParameter(String name, Object... values){
		this.parameterName = name;
		if(values==null){}
		else if(values.length==0){
			this.parameterValues = new ArrayList<Object>();
		}else{
			this.parameterValue = values[0];
			this.parameterValues = Arrays.asList(values);
		}
	}

	public String getParameterName() {
		return parameterName;
	}
	public Object getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(Object parameterValue) {
		this.parameterValue = parameterValue;
	}
	public void setParameterValues(List<Object> values){
		//todo to continue 
		this.parameterValues = values;
	}
	public List<Object> getParameters(){
		return parameterValues;
	}
}
