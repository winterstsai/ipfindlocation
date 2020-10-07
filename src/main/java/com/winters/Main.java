package com.winters;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import com.ip2location.IP2Location;
import com.ip2location.IPResult;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * @author winterstsai 
 * 
 * <pre>
 * ip反查地理位置 
 * 須下載資料庫
 * 說明頁
 * https://github.com/winterstsai/com.winters
 * </pre>
 */
public class Main {
	private static String ipCsvPath;
	private static String locationDBPath;
	private static String locationCsvPath;

	public static void main(String[] args) {
		if (args.length < 3 || args[0] == null || args[1] == null || args[2] == null) {
			System.out.println("Proper Usage is: java program locationDBPath ipCsvPath locationCsvPath");
			System.exit(0);
		}
		ipCsvPath = args[1];// ip csv檔案位置
		locationDBPath = args[0];// ip地理資料庫位置
		locationCsvPath = args[2];// 產出的location位置
		try (var fos = new FileOutputStream(locationCsvPath);
				var osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				var writer = new CSVWriter(osw);
				CSVReader reader = new CSVReader(new FileReader(ipCsvPath));) {
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				String ip = nextLine[0];
				String location = queryLocation(ip);
				writer.writeNext(new String[] { ip, location });
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace(System.out);
		}
	}

	/**
	 * 參考https://github.com/ip2location/ip2location-java
	 * 
	 * @param ip
	 * @return location string
	 */
	public static String queryLocation(String ip) {
		IP2Location loc = new IP2Location();
		String message = "Error";
		try {
			String binfile = locationDBPath;

			loc.Open(binfile, true);

			IPResult rec = loc.IPQuery(ip);
			if ("OK".equals(rec.getStatus())) {
				System.out.println(rec);
				message = rec.getCountryShort();
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
