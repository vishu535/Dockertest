package ca.poc.djj.utils;

import java.util.Iterator;
import java.util.jar.JarException;
import java.util.logging.Level;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

public class TestResponseCode {
	private static WebDriver TRdriver = null;

	public static void DownloadPage(String url) throws JarException {
		DownloadPage(url, "chrome", true);
	}

	public static void DownloadPage(String url, String browser, Boolean quitDriver) throws JarException {
		try {
			switch (browser) {
			case "chrome":
				ChromeOptions opt = new ChromeOptions();
				// set performance logger
				// this sends Network.enable to chromedriver
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
				opt.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
				TRdriver = new ChromeDriver(opt);
				break;
			case "ie":
				TRdriver = new InternetExplorerDriver();
				break;
			}

			// navigate to the page
			System.out.println("Navigate to " + url);
			TRdriver.navigate().to(url);

			// and capture the last recorded url (it may be a redirect, or the original url)
			String currentURL = TRdriver.getCurrentUrl();

			switch (browser) {
			case "chrome":
				// then ask for all the performance logs from this request
				// one of them will contain the Network.responseReceived method
				// and we shall find the "last recorded url" response
				LogEntries logs = TRdriver.manage().logs().get("performance");
				int status = -1;
				System.out.println("\nList of log entries:\n");

				for (Iterator<LogEntry> it = logs.iterator(); it.hasNext();) {
					LogEntry entry = it.next();
					JSONObject json = new JSONObject(entry.getMessage());

					System.out.println(json.toString());

					JSONObject message = json.getJSONObject("message");
					String method = message.getString("method");

					if (method != null
							&& "Network.responseReceived".equals(method)) {
						JSONObject params = message.getJSONObject("params");

						JSONObject response = params.getJSONObject("response");
						String messageUrl = response.getString("url");

						if (currentURL.equals(messageUrl)) {
							status = response.getInt("status");
							System.out.println("---------- bingo !!!!!!!!!!!!!! returned response for " + messageUrl + ": " + status);
							System.out.println("---------- bingo !!!!!!!!!!!!!! headers: " + response.get("headers"));
						}
					}
				}
				System.out.println("\nstatus code: " + status);
				break;
			}

		} finally {
			if (quitDriver.equals(true)) {
				if (TRdriver != null) {
					TRdriver.quit();
				}
			}
		}
	}

	public static void CloseDownloadPage() {
		if (TRdriver != null) {
			TRdriver.quit();
		}
	}

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "c:/CSP/Drivers/chromedriver.exe");
		System.setProperty("webdriver.ie.driver", "c:/CSP/Drivers/IEDriverServer3.141.5_win32.exe");

		DownloadPage("https://www.cspuat.ca/content/support_en/pdf/ROSCO_Pricing_Guide_20150921.pdf", "ie", false);
		CloseDownloadPage();
	}
}
