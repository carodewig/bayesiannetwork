import java.util.HashMap;
import java.util.LinkedList;

public class Distribution {
	
	protected HashMap<LinkedList<String>, Double> distribution;
	
	public Distribution() {
		distribution = new HashMap<LinkedList<String>, Double>();
	}
	
	public void addValue(LinkedList<String> conditions, Double value) {
		distribution.put(conditions, value);
	}
	
	public Distribution duplicate() {
		HashMap<LinkedList<String>, Double> newDist = new HashMap<LinkedList<String>, Double>();
		newDist.putAll(distribution);
		return newDist;
	}
}