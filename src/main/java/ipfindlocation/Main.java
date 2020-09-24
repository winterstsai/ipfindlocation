package ipfindlocation;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import com.ip2location.IP2Location;
import com.ip2location.IPResult;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Main {

	public static void main(String[] args) {
		try (
				var fos = new FileOutputStream("/Users/winterstsai/IpFindLocation/src/main/resources/ip_location.csv");
				var osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				var writer = new CSVWriter(osw);
				CSVReader reader = new CSVReader(
						new FileReader("/Users/winterstsai/IpFindLocation/src/main/resources/ip.csv"));){
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				String ip = nextLine[0];
				String location = queryLocation(ip);
				writer.writeNext(new String[] {ip,location});
				}
			} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace(System.out);
		}
	}

	/**
	 * 參考https://github.com/ip2location/ip2location-java
	 * @param ip
	 * @return location string
	 */
	public static String queryLocation(String ip) {
		IP2Location loc = new IP2Location();
		String message = "Error";
		try{
			String binfile = "/Users/winterstsai/IpFindLocation/src/main/resources/IP2LOCATION-LITE-DB11.BIN";
			
			loc.Open(binfile, true);

			IPResult rec = loc.IPQuery(ip);
			if ("OK".equals(rec.getStatus())) {
				System.out.println(rec);
				message = rec.toString();
			} else if ("EMPTY_IP_ADDRESS".equals(rec.getStatus())) {
				System.out.println("IP address cannot be blank.");
				message = "IP address cannot be blank.";
			} else if ("INVALID_IP_ADDRESS".equals(rec.getStatus())) {
				System.out.println("Invalid IP address.");
				message = "Invalid IP address.";
			} else if ("MISSING_FILE".equals(rec.getStatus())) {
				System.out.println("Invalid database path.");
				message = "Invalid database path.";
			} else if ("IPV6_NOT_SUPPORTED".equals(rec.getStatus())) {
				System.out.println("This BIN does not contain IPv6 data.");
				message = "This BIN does not contain IPv6 data.";
			} else {
				System.out.println("Unknown error." + rec.getStatus());
				message = "Unknown error." + rec.getStatus();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace(System.out);
		} finally {
			loc.Close();

		}
		
		return message;
	}

}
