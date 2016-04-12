import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class XMLParser {
	
	public BayesNet createBayesNet(String filename) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new File(filename));
		return processDocument(doc);
	}
	
	protected BayesNet processDocument(Document doc) {
		Element networkElement = doc.getDocumentElement();
		final BayesNet network = new BayesNet();
		
		processElements(doc, "VARIABLE", new ElementTaker() {
			public void element(Element e) {
				processElementVariable(e, network);
			}
		});
		
		processElements(doc, "DEFINITION", new ElementTaker() {
			public void element(Element e) {
				processElementDefinition(e, network);
			}
		});
		
		return network;
	}
	
	protected void processElements(Document doc, String tag, ElementTaker taker) {
		NodeList nodes = doc.getElementsByTagName(tag);
		if (nodes != null && nodes.getLength() > 0) {
			for (int i=0; i<nodes.getLength(); i++) {
				Node node = nodes.item(i);
				taker.element((Element)node);
			}
		}
	}
	
	protected void processElementVariable(Element e, BayesNet network) {
		Element nameElement = getChildWithTagName(e, "NAME");
		String name = getChildText(nameElt);
		BayesNode variable = new BayesNode(new RandomVariable(name), null);
//		RandomVariable variable = new RandomVariable(name);
		processChildren(e, "OUTCOME", new ElementTaker() {
			public void element(Element e) {
				String value = getChildText(e);
				variable.addToDomain(value);
			}
		});
		
		network.add(variable);
	}
	
	protected void processElementDefinition(Element e, final BayesNet network) {
		Element forElement = getChildWithTagName(e, "FOR");
		String forName = getChildText(forElement);
		RandomVariable variable = network.getVariable(forName);
		final List<RandomVariable> givens = new LinkedList<RandomVariable>();
		processChildren(e, "GIVEN", new ElementTaker() {
			public void element(Element e) {
				String value = getChildText(e);
				givens.add(network.getVariable(value));
			}
		});
		
		ConditionalProbabilityTable table = new ConditionalProbabilityTable(variable, givens);
		Element tableElement = getChildWithTagName(e, "TABLE");
		String tableString = getChildText(tableElement);
		initializeCPT(table, tableString);
		network.addNode(variable, givens, table);
	}
	
	protected void initializeCPT(ConditionalProbabilityTable table, String string) {
		StringTokenizer tokens = new StringTokenizer(string);
		// Assume we have a value iterator or something
		
		Iterator<ConditionalProbabilityTable.ProbabilityValue> values = table.valueIterator();
		while (tokens.hasMoreTokens()) {
			String token = token.nextToken();
			ConditionalProbabilityTable.ProbabilityValue pv = values.next();
			pv.value = Double.parseDouble(token);
		}
		
		if (values.hasNext()) {
			System.err.println("CPT Parsing Exception");
		}
	}
	
	protected Element getChildWithTagName(Element element, String tagname) {
		NodeList children = element.getChildNodes();
		if (children != null && children.getLength() > 0) {
			for (int i=0; i<children.getLength(); i++) {
				Node node = children.item(i);
			}
		}
	}
	
	
}