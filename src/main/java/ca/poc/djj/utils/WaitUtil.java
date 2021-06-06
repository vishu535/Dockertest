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
	public static boolean waitUntil(final Supplier<Boolean> condition, final long timeoutMs, final int pollIntervalMs) {
		return (Boolean) new NormalFluentWait<String>("wait").withTimeout(timeoutMs, TimeUnit.MILLISECONDS)
				.pollingEvery(pollIntervalMs).milliseconds().until(magic -> condition.get());
	}

	public static boolean waitUntil(final Supplier<Boolean> condition, final long timeoutMs) {
		return waitUntil(condition, timeoutMs, INTERVAL);
	}

	public static boolean waitUntil(final Supplier<Boolean> condition) {
		return waitUntil(condition, TIMEOUT, INTERVAL);
	}

	public static WebElementFacade waitUntilElementAppears(WebElementFacade element) {
		return waitUntilElementAppears(element, TIMEOUT);
	}

	public static WebElementFacade waitUntilElementAppears(WebElementFacade element, final long timeoutMs) {
		waitUntil(() -> element.isCurrentlyVisible(), timeoutMs);
		return element;
	}

	public static void waitUntilElementDisappears(WebElementFacade element) {
		waitUntil(() -> !element.isPresent());
	}

	public static boolean waitUntilElementFocused(WebElement element) {
		return waitUntil(() -> getDriver().switchTo().activeElement().equals(element), 15000);
	}

	public static boolean waitUntilElementTextEquals(WebElementFacade element, final String text) {
		waitUntil(() -> text.equals(element.getText()));
		return true;
	}

	public static void waitMSeconds(int mseconds) {
		try {
			Thread.sleep(mseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
