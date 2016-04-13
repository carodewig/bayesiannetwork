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
		System.out.print("vars: ");
		for (RandomVariable rv : vars) {
			rv.print();
		}
		RandomVariable Y = vars.remove(0);
		System.out.println();
		Y.print();
		System.out.println();
		if (e.variableSet().contains(Y)) {
			//Node n = bn.getNodeForVariable(Y);
			//CPT cpt = n.getCPT();
			//double x = cpt.get(e);
			double x = bn.getProb(Y, e);
			//System.out.println(e.toString());
			System.out.println("x: " + x);
			double y = enumerateAll(bn, vars, e);
			//System.out.println("y: " + y);
			System.out.println(x*y);
			return x*y;
		} else {
			double sum = 0;
			System.out.println("Entering sum with Y:");
			System.out.println();
			for (Object elem : Y.getDomain()) {
				List<RandomVariable> varsPrime = new ArrayList<RandomVariable>();
				varsPrime.addAll(vars);
				Assignment ePrime = new Assignment();
				ePrime = e.copy();
				ePrime.set(Y, elem);
				double z = bn.getProb(Y,ePrime);
				System.out.println("z: " + z);
				double zz = z * enumerateAll(bn, varsPrime, ePrime);
				System.out.println("zz: " + zz);
				sum += zz;

			}
			System.out.println("sum: " + sum);
			return sum;
		}

	}
	/*
	private static boolean containsRandomVar(A e, RandomVariable Y) {
		for (Assignment ass : e) {
			if (ass.variableSet().contains(Y)) {
				return true;
			}
		}
		return false;
	}
*/
}