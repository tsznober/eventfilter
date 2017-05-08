import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class JsonEventLoader {
	private ArrayList<Report> reportList;
	
	public JsonEventLoader() {
		reportList = new ArrayList<Report>();
	}
	
	public ArrayList<Report> load(String filePath) {
		try {
			Gson gson = new Gson();
			reportList = gson.fromJson(new FileReader(filePath), new TypeToken<ArrayList<Report>>(){}.getType());
			
			// Since the request-time node in the JSON is formatted
			// as milliseconds, we need to convert it back to a
			// formatted date time string.
			for (Report rep : reportList) {
				long milliseconds = Long.parseLong(rep.getRequestTime());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
				Date dRequestTime = new Date(milliseconds);
				rep.setRequestTime(sdf.format(dRequestTime));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return reportList;
	}
}
