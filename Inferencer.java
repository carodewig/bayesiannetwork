import java.util.HashMap;

public class Inferencer {
	
	public static Distribution rejectionSampling(RandomVariable X, Assignment e, BayesianNetwork bn, int samples) {
		
		int counter = 0;
		while (counter < samples) {
			boolean checker = true;
			Assignment ass = priorSample(bn);
			for (RandomVariable given: e.variableSet()) {
				if (!(e.get(given)).equals(ass.get(given))) {
					checker = false;
					break;
				}
			}
			if (checker) {
				X.increment(ass.get(X));
				counter++;
			}	
		}
		
		HashMap<Object, Integer> counted = X.getCounts();
		Distribution dist = new Distribution();
		for (Object obj: counted.keySet()) {
			dist.put(obj, counted.get(obj));
		}
		dist.normalize();
		return dist;
	}
	
	public static Assignment priorSample(BayesianNetwork bn) {
		Assignment ass = new Assignment();
		Assignment asss = new Assignment();
		for (RandomVariable elem: bn.getVariableListTopologicallySorted()) {
			double coinToss = Math.random();
			double prob = 0;
			
			for (Object obj: elem.getDomain()) {
				asss.set(elem, obj);
				prob += bn.getProb(elem, asss);
				if (coinToss < prob) {
					ass.set(elem, obj);
					break;
				}
			}
			
			asss = ass.copy();	
		}
		
		return ass;
	}
	
}