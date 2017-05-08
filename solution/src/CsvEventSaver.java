import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvEventSaver {

	public void save(String filePath, ArrayList<Report> reportList) {
		FileWriter fileWriter = null;
		CSVPrinter csvPrinter = null;
		CSVFormat recordFormat = CSVFormat.RFC4180.withHeader("client-address",
				"client-guid",
				"request-time",
				"service-guid",
				"retries-request",
				"packets-requested",
				"packets-serviced",
				"max-hole-size");
		try {
			fileWriter = new FileWriter(filePath);
			csvPrinter = new CSVPrinter(fileWriter, recordFormat);
			for (Report rep : reportList) {
				List<Object> eventRecord = new ArrayList<Object>();
				eventRecord.add(rep.getClientAddress());
				eventRecord.add(rep.getClientGUID());
				eventRecord.add(rep.getRequestTime());
				eventRecord.add(rep.getServiceGUID());
				eventRecord.add(String.valueOf(rep.getRetriesRequest()));
				eventRecord.add(String.valueOf(rep.getPacketsRequested()));
				eventRecord.add(String.valueOf(rep.getPacketsServiced()));
				eventRecord.add(String.valueOf(rep.getMaxHoleSize()));
				csvPrinter.printRecord(eventRecord);
			}
		} catch (IOException e1) {
			System.out.println("Failed to write CSV output file.");
			e1.printStackTrace();
		} finally {
			try {
				csvPrinter.flush();
				fileWriter.flush();
				csvPrinter.close();
				fileWriter.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
