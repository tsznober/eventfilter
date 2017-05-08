import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Enumeration;

public class EventFilter {
	private ArrayList<Report> allReports;
	private XmlEventLoader xmlEventLoader;
	private CsvEventLoader csvEventLoader;
	private JsonEventLoader jsonEventLoader;
	private CsvEventSaver csvEventSaver;
	
	public EventFilter() {
		allReports = new ArrayList<Report>();
		xmlEventLoader = new XmlEventLoader();
		csvEventLoader = new CsvEventLoader();
		jsonEventLoader = new JsonEventLoader();
		csvEventSaver = new CsvEventSaver();
	}

	private void Run() {
		String xmlFilePath = "../reports.xml";
		String csvFilePath = "../reports.csv";
		String jsonFilePath = "../reports.json";
		String outFilePath = "../output.csv";
		
		// Load all of the event files
		allReports.addAll(xmlEventLoader.load(xmlFilePath));
		allReports.addAll(csvEventLoader.load(csvFilePath));
		allReports.addAll(jsonEventLoader.load(jsonFilePath));
		
		// Exclude reports with packets-serviced equal to zero
		allReports.removeIf((Report r) -> r.getPacketsServiced() == 0);
		
		// Sort the reports by request-time in ascending order
		Collections.sort(allReports, new Comparator<Report>() {
		 	@Override
		 	public int compare(Report r1, Report r2) {
		 		return r1.getRequestTime().compareTo(r2.getRequestTime());
		 	}
		});

		csvEventSaver.save(outFilePath, allReports);

		printSummary();
	}
	
	private void printSummary() {
		// Compute number of records associated with each service-guid
		Hashtable<String, Integer> serviceGuids = new Hashtable<String, Integer>();
		for (Report rep : allReports) {
			String serviceGuid = rep.getServiceGUID();
			if (serviceGuids.containsKey(serviceGuid)) {
				Integer count = (Integer)(serviceGuids.get(serviceGuid));
				count++;
				serviceGuids.put(serviceGuid, new Integer(count));
			} else {
				serviceGuids.put(serviceGuid, new Integer(0));
			}
		}
		
		// Print out service guids and count for each
		Enumeration<String> keys = serviceGuids.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			Integer count = serviceGuids.get(key);
			System.out.println("Service GUID " + key + " has " + count + " records.");
		}
	}
	
	public static void main(String[] args) {
		EventFilter ef = new EventFilter();
		ef.Run();
	}
}
