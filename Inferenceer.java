import java.util.*;

public class Inferenceer {
	public Inferenceer() {

	}
	
	public static Distribution enumerationAsk(RandomVariable X, Assignment e, BayesianNetwork bn) {
		Distribution Q = new Distribution(X);
		for (Object elem : X.getDomain()) {
			Assignment ePrime = new Assignment();
			ePrime = e.copy();
			ePrime.set(X, elem);
			Q.put(elem, enumerateAll(bn, bn.getVariableListTopologicallySorted(), ePrime));
		}
		Q.normalize();
		return Q;
	}
	
	public static double enumerateAll(BayesianNetwork bn, List<RandomVariable> vars, Assignment e) {
		if (vars.isEmpty()) {
			return 1.0;
		}
		RandomVariable Y = vars.remove(0);
		if (e.variableSet().contains(Y)) {
			double x = bn.getProb(Y, e);
			double y = enumerateAll(bn, vars, e);
			return x*y;
		} else {
			double sum = 0;
			for (Object elem : Y.getDomain()) {
				List<RandomVariable> varsPrime = new ArrayList<RandomVariable>();
				varsPrime.addAll(vars);
				Assignment ePrime = new Assignment();
				ePrime = e.copy();
				ePrime.set(Y, elem);
				double z = bn.getProb(Y,ePrime);
				double zz = z * enumerateAll(bn, varsPrime, ePrime);
				sum += zz;

			}
			return sum;
		}

	}
	
}