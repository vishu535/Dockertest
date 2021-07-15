package ca.poc.djj.utils;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.scheduling.NormalFluentWait;

public final class WaitUtil {

	private static final long TIMEOUT = 120000;

	private static final int INTERVAL = 500;

	private WaitUtil() {
	}

	@SuppressWarnings({ "unchecked" })
	
	
	public static void waitMSeconds(int mseconds) {
		try {
			Thread.sleep(mseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
