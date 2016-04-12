public class BayesNode {
	
	protected RandomVariable variable;
	protected ConditionalProbabilityTable table;
	protected LinkedList<BayesNode> children;
	
	public BayesNode(RandomVariable variable, Distribution distribution) {
		this.variable = variable;
		table = new ConditionalProbabilityTable();
		children = new LinkedList<Node>();
	}
	
	
	public void addChild(BayesNode child) {
		children.add(child);
	}
	
	public BayesNode duplicate() {
		BayesNode n = new BayesNode(variable.duplicate(), distribution.duplicate());
		for (BayesNode child: children)
			n.addChild(child.duplicate());
		return n;
	}
	
	public void addToDomain(String value) {
		variable.addToDomain(value);
	}
}