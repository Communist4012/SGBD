import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParserXPath {
	public ParserXPath() {
	}

	public ArrayList<String> getInfo() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		ArrayList<String> str = new ArrayList<String>();
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse("param.xml");
		XPath xpath = XPathFactory.newInstance().newXPath();

		XPathExpression expr = xpath.compile("//VLV/Parameter");
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getAttributes().getNamedItem("value"));
			str.add(nodes.item(i).getAttributes().getNamedItem("value").toString().split("\"")[1].split("\"")[0]);
		}
		return str;
		
	}

	public static void main(String[] args) throws Exception {
		ParserXPath xpath = new ParserXPath();
		System.out.println(xpath.getInfo().get(1));
	}
}
