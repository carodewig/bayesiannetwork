import java.io.*;
import java.util.*;

public class Node implements Printable {

	public RandomVariable variable;
	public List<Node> parents;
	public Set<Node> children = new ArraySet<Node>();
	public CPT cpt;

	public Node(RandomVariable variable) {
	    this.variable = variable;
	}

	public CPT getCPT() {
		return cpt;
	}

	// Printable

	/**
	 * Print this Node to the given PrintWriter.
	 */
	public void print(PrintWriter out) {
	    print(out, 0);
	}

	/**
	 * Print this Node to the given PrintWriter at the given
	 * indent level.
	 */
	protected void print(PrintWriter out, int indent) {
	    for (int i=0; i < indent; i++) {
		out.print(" ");
	    }
	    out.print("[");
	    out.print(variable.toString());
	    if (children != null && !children.isEmpty()) {
		out.println(":");
		for (Node child : children) {
		    child.print(out, indent+2);
		    out.println();
		}
	    }
	    for (int i=0; i < indent; i++) {
		out.print(" ");
	    }
	    out.print("]");
	}

	/**
	 * Print this Node to the given PrintStream.
	 */
	public void print(PrintStream out) {
	    PrintWriter writer = new PrintWriter(out, true);
	    print(writer);
	    writer.flush();
	}

	/**
	 * Print this Node to System.out.
	 */
	public void print() {
	    print(System.out);
	}

	/**
	 * Return the string representation of this Node.
	 */
	public String toString() {
	    StringWriter writer = new StringWriter();
	    PrintWriter out = new PrintWriter(writer);
	    print(out);
	    out.flush();
	    return writer.toString();
	}

    }