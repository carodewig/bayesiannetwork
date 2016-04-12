import java.util.LinkedList;

public class RandomVariable {
	
	protected String name;
	protected LinkedList<String> domain;
	protected boolean observed;
	protected String value;
	
	public RandomVariable(String name, LinkedList<String> domain, boolean observed, String value) {
		this.name = name;
		this.domain = domain;
		this.observed = observed;
		
		if (observed)
			this.value = value;
		else
			this.value = null;
	}
	
	public RandomVariable(String name, LinkedList<String> domain) {
		this.name = name;
		this.domain = domain;
	}
	
	public RandomVariable(String name) {
		this.name = name;
		domain = new LinkedList<String>();
	}
	
	public String getName() {
		return name;
	}
	
	public LinkedList<String> getDomain() {
		return domain;
	}
	
	public boolean getObserved() {
		return observed;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setObserved(boolean obs) {
		observed = obs;
	}
	
	public void setValue(String val) {
		value = val;
	}
	
	public void addToDomain(String var) {
		domain.add(var);
	}
	
	public RandomVariable duplicate() {
		return new RandomVariable(name, domain, observed, value);
	}
	
}