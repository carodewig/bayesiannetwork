import java.util.LinkedList;

public class BayesNet {
	
	LinkedList<BayesNode> nodeSet;
	LinkedList<RandomVariable> variables;
	
	public BayesNet() {
		parentNodes = new LinkedList<BayesNode>();
		variables = new LinkedList<RandomVariable>();
	}
	
	public BayesNet(LinkedList<RandomVariable> vars) {
		parentNodes = new LinkedList<BayesNode>();
		variables = new LinkedList<RandomVariable>();
		for (RandomVariable element: vars)
			variables.add(element);
	}
	
	public RandomVariable getVariable(String name) {
		for (RandomVariable element: variables) {
			if (name.equals(element.getName()))
				return element;
		}
		return null;
	}
	
	public void add(BayesNode node) {
		
	}
	
}