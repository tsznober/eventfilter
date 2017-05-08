import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CsvEventLoader {
	private ArrayList<Report> reportList;
	
	public CsvEventLoader() {
		reportList = new ArrayList<Report>();
	}
	
	public ArrayList<Report> load(String filePath) {
		try {
			FileReader fileReader = new FileReader(filePath);
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(fileReader);
			for (CSVRecord record : records) {
				Report rep = new Report();
				rep.setClientAddress(record.get("client-address"));
				rep.setClientGUID(record.get("client-guid"));
				rep.setRequestTime(record.get("request-time"));
				rep.setServiceGUID(record.get("service-guid"));
				int iVal = Integer.parseInt(record.get("retries-request"));
				rep.setRetriesRequest(iVal);
				iVal = Integer.parseInt(record.get("packets-requested"));
				rep.setPacketsRequested(iVal);
				iVal = Integer.parseInt(record.get("packets-serviced"));
				rep.setPacketsServiced(iVal);
				iVal = Integer.parseInt(record.get("max-hole-size"));
				rep.setMaxHoleSize(iVal);
				reportList.add(rep);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return reportList;
	}
}
