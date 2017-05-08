import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlEventLoader {
	private ArrayList<Report> reportList;
	
	public XmlEventLoader() {
		reportList = new ArrayList<Report>();
	}
	
	public ArrayList<Report> load(String filePath) {
		File xmlFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList nodeList = doc.getElementsByTagName("report");
			for (int i=0; i < nodeList.getLength(); i++) {
				reportList.add(getReport(nodeList.item(i)));
			}
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (ParserConfigurationException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		
		return reportList;
	}
	
	private Report getReport(Node node) {
		Report rep = new Report();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element)node;
			rep.setClientAddress(getTagValue("client-address", element));
			rep.setClientGUID(getTagValue("client-guid", element));
			rep.setRequestTime(getTagValue("request-time", element));
			rep.setServiceGUID(getTagValue("service-guid", element));
			int iVal = Integer.parseInt(getTagValue("retries-request", element));
			rep.setRetriesRequest(iVal);
			iVal = Integer.parseInt(getTagValue("packets-requested", element));
			rep.setPacketsRequested(iVal);
			iVal = Integer.parseInt(getTagValue("packets-serviced", element));
			rep.setPacketsServiced(iVal);
			iVal = Integer.parseInt(getTagValue("max-hole-size", element));
			rep.setMaxHoleSize(iVal);
		}
		return rep;
	}
	
	private String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node)nodeList.item(0);
		return node.getNodeValue();
	}
}
