import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class Part1 {
	
    public static void main(String[] argv) throws IOException, ParserConfigurationException, SAXException {
		BIFParser parserB;
		XMLBIFParser parserX;
		BayesianNetwork network = new BayesianNetwork();
		if (argv[0].contains(".bif")) {
	    	if (argv.length == 0) {
	    	    parserB = new BIFParser(System.in);
	    	} else {
	    	    parserB = new BIFParser(new FileInputStream(argv[0]));
	    	}
	    	network = parserB.parseNetwork();
    	
		} else if (argv[0].contains(".xml")) {
			parserX = new XMLBIFParser();
			network = parserX.readNetworkFromFile(argv[0]);
		} else {
			System.exit(0);
		}
		
		RandomVariable testing = network.getVariableByName(argv[1]);
		Inferenceer inf = new Inferenceer();

		Assignment ass = new Assignment();
		for (int i = 2; i < argv.length; i+=2) {
			ass.set(network.getVariableByName(argv[i]), argv[i+1]);
		}

		Distribution dist = inf.enumerationAsk(testing, ass, network);
		for (Object obj : dist.keySet()) {
  			System.out.println(obj + ": " + dist.get(obj));
		}
    	
			
    }
	
}