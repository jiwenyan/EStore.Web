package DAL.Framework;

import java.util.ArrayList;
import java.util.List;

public class Predicate {
	private String predicate;
	private List<PredicateParameter> parameters;

	public Predicate (){
		this.predicate = "";
		this.parameters = new ArrayList<PredicateParameter>();
	}
	
	public Predicate(String predicate) {
		this.predicate = predicate;
		parameters = new ArrayList<PredicateParameter>();
	}


	public Predicate(String predicate, List<PredicateParameter> parameters) {
		this.predicate = predicate;
		if (parameters != null) {
			this.parameters = parameters;
		} else {
			parameters = new ArrayList<PredicateParameter>();
		}
	}

	public Predicate(String predicate, String parameterName,
			Object... parameterValues) {
		this.predicate = predicate;
		parameters = new ArrayList<PredicateParameter>();
		parameters.add(new PredicateParameter(parameterName, parameterValues));
	}

	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public List<PredicateParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<PredicateParameter> parameters) {
		this.parameters = parameters;
	}

	/**
	 * Do and with another predicate.
	 * 
	 * @param predicate
	 * @return the predicate after the and.
	 */
	public Predicate And(Predicate predicate) {
		if (this.predicate.length() == 0) {
			// if this is a fresh predicate
			this.predicate += "where " + predicate.predicate;
		} else {
			this.predicate += "and " + predicate.predicate;
		}
		this.parameters.addAll(predicate.parameters);
		return this;
	}

	/**
	 * Do or with another predicate.
	 * 
	 * @param predicate
	 * @return the predicate after the or.
	 */
	public Predicate Or(Predicate predicate) {
		if (this.predicate.length() == 0) {
			// if this is a fresh predicate
			this.predicate += "where " + predicate.predicate;
		} else {
			this.predicate += "or " + Enclose(predicate.predicate);
		}
		this.parameters.addAll(predicate.parameters);
		return this;
	}

	// private Predicate Enclose(Predicate toEnclose){
	// return new Predicate(Enclose(toEnclose.predicate),toEnclose.parameters);
	// }

	private String Enclose(String toEnclose) {
		return "(" + toEnclose + ")";
	}

	/**
	 * Gets a like query on upper case.
	 * 
	 * @param predicate
	 * @param value
	 * @return
	 */
	public static Predicate ilike(String predicate, String value) {
		if (value == null) {
			value = "%%";
		}
		return new Predicate("UPPER(" + predicate + ") like UPPER(:"
				+ predicate + ")", predicate, value);
	}

	/**
	 * Get a like query on normal case.
	 * @param predicate
	 * @param value
	 * @return
	 */
	public static Predicate like(String predicate, String value) {
		if (value == null) {
			value = "";
		}
		return new Predicate(predicate + " like :" + predicate, predicate,
				value);
	}
	
	/**
	 * Get a like query on normal case.
	 * @param predicate
	 * @param value
	 * @return
	 */
	public static Predicate like(String predicate, Object value) {
		if (value == null) {
			value = "";
		}
		return new Predicate(predicate + " like :" + predicate, predicate,
				value);
	}

	/**
	 * Get a like query with a prefix
	 * @param predicate
	 * @param prefix
	 * @return
	 */
	public static Predicate startsWith(String predicate, String prefix) {
		if (prefix == null) {
			prefix = "";
		} else {
			prefix = prefix + "%";
		}
		return new Predicate(predicate + " like :" + predicate, predicate,
				prefix);
	}

	/**
	 * Get like query with a suffix.
	 * @param predicate
	 * @param suffix
	 * @return
	 */
	public static Predicate endWith(String predicate, String suffix) {
		if (suffix == null) {
			suffix = "";
		} else {
			suffix = "%"+suffix;
		}
		return new Predicate(predicate + " like :" + predicate, predicate,
				suffix);
	}

	/**
	 * Get a query of equal.
	 * @param predicate
	 * @param value
	 * @return
	 */
	public static Predicate Eq(String predicate, Object value) {
		if (value == null) {
			return new Predicate(predicate + " is null");
		} else {
			return new Predicate(predicate + " = :" + predicate, predicate,
					value);
		}
	}

	/**
	 * Get a query of not equal.
	 * @param predicate
	 * @param value
	 * @return
	 */
	public static Predicate NotEq(String predicate, Object value) {
		if (value == null) {
			return new Predicate(predicate + " is not null");
		} else {
			return new Predicate(predicate + " != :" + predicate, predicate,
					value);
		}
	}
}
