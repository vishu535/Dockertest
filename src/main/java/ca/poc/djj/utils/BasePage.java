package ca.poc.djj.utils;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class BasePage extends PageObject {

	public final String panelMessages = "//div[contains(@id,'messagePanelHomePage')]";

	public final String panelProjects = "//div[contains(@id,'projectPanelHomePage')]";

	public final String panelDocketHeader = "//div[contains(@id,'docketHeaderPanel')]";

	public final String adminPanel = "//div[contains(@id,'adminPanel')]";

	public final String containerInstrumentBranch = "//div[@id='edit-inst-editor-widget']";

	private final int WAIT_ELEMENT_PRESENT_SEC = 5;

	@FindBy(xpath = "//embed[@type='application/pdf']")
	public WebElementFacade embedded_PDF;

	@FindBy(xpath = "//table[@class='gwt-DatePicker']")
	public WebElementFacade pickerDate;

	@FindBy(xpath = "//div[contains(@class,'datePickerDay') and contains(@class,'datePickerDayIsToday')]")
	public WebElementFacade currentDayFromDatePicker;

	
	@FindBy(xpath = "//*[contains(@class, 'spinningWheel')]")
	private WebElementFacade spinningWheel;

	// @FindBy(xpath = "//cycle[contains(@style, 'mat-progress-spinner')]")
	@FindBy(xpath = "//mat-progress-spinner")
	private WebElementFacade spinner;

	@FindBy(xpath = "//*[@class='systemWaitGlass']")
	private WebElementFacade systemWaitGlass;

	public BasePage() {
	}

	public BasePage(WebDriver driver) {
		super(driver);
	}

	// when no page re-load this function works, when page re-load, please use waitUntilElementsAppear
	public void waitUntilElementDisplayed(WebElement we) {
		final long watch = System.currentTimeMillis();
		while ((System.currentTimeMillis() - watch) < 20000) // change it to a constant somewhere please{
			try {
				we.isDisplayed();
				return;
			} catch (Exception e) {
				System.out.println("waitUntilElementDisplayed exception is: " + e.toString());
			}
	}

	// query an webElement appear in a page within miliSeconds
	// when page refresh(re-load) and webElement obsolete
	public WebElement waitUntilElementsAppear(String xpathString, int miliSeconds) {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String currentStr = variables.getProperty("webdriver.timeouts.implicitlywait");
		int current = Integer.parseInt(currentStr);
		this.setImplicitTimeout(2000, ChronoUnit.MILLIS);
		// ThucydidesWebDriverSupport.useDriver(this.getDriver());
		// WaitUtil.waitMSeconds(2000);

		WebElement returnElements = null;
		final long watch = System.currentTimeMillis();
		while ((System.currentTimeMillis() - watch) < miliSeconds) {
			// System.out.println("waitUntilElementsAppear start to wait: " + (System.currentTimeMillis() - watch));
			try {
				returnElements = this.getDriver().findElement(By.xpath(xpathString));
				if (returnElements != null) {
					this.setImplicitTimeout(current, ChronoUnit.MILLIS);
					System.out.println("element displayed within timer!");
					return returnElements;
				} else {
					System.out.println("waitUntilElementsAppear element not yet ready: " + (System.currentTimeMillis() - watch));
					continue;
				}
			} catch (NoSuchElementException e) {
				System.out.println("waitUntilElementsAppear exception: \n" + e.toString() + (System.currentTimeMillis() - watch));
				continue;
			}
		}
		System.out.println("element not displayed within timer, timeout!");
		// set timer back before return
		this.setImplicitTimeout(current, ChronoUnit.MILLIS);
		return null;
	}

	public void clickSafelyByWaiting(WebElement we) {
		final long watch = System.currentTimeMillis();
		while ((System.currentTimeMillis() - watch) < 20000)// change it to a constant somewhere please{
			try {
				we.click();
				return;
			} catch (Exception e) {
				System.out.println("clickSafelyByWaiting exception is: " + e.toString());
			}
	}

	// keep clicking button until webElemnt(usually popup/dialog) appear
	public void clickUntilWebElementAppear(WebElement we, String xpathString) {
		final long watch = System.currentTimeMillis();
		int i = 0;
		while ((System.currentTimeMillis() - watch) < 120000)// change it to a constant somewhere please{
			try {
				System.out.println("try to click " + System.currentTimeMillis());
				we.click();
				i++;
				System.out.println("click " + i + " time(s)" + System.currentTimeMillis());
				// here need to change default implicitly wait time to be shorter
				if (waitUntilElementsAppear(xpathString, 20000) != null) {
					return;
				}
			} catch (Exception e) {
				System.out.println("clickUntilWebElementAppear exception is: " + e.toString());
			}
	}

	public WebElement getActiveElement() {
		return getDriver().switchTo().activeElement();
	}

	public int getQuantityOfErrors(String path) {
		int count = 0;
		List<WebElementFacade> list = findAll(
				path + "//img[(@class='wave4tip' or @class='wave5icon') and contains(@alt,'ERROR') and not(contains(@alt,"
						+ "'CONTRAST'))]");
		for (WebElementFacade error : list) {
			if (error.isVisible()) {
				count++;
			}
		}
		return count;
	}

	public WebElementFacade getCheckBoxByLabel(String value) {
		return findBy("//input[@type='checkbox' and contains(@aria-label,'" + value + "')]");
	}

	public WebElementFacade getSpinningWheel() {
		waitABit(500);
		return spinningWheel;
	}

	public WebElementFacade getSpinner() {
		waitABit(500);
		return spinner;
	}

	public WebElementFacade getSystemWaitGlass() {
		waitABit(500);
		return systemWaitGlass;
	}

	// this function should be removed from BasePage
	public String getRowNumber(String rowNumber) {
		String result = "";
		int row;
		if (rowNumber != null) {
			row = Integer.parseInt(rowNumber) - 1;
			result = new Integer(row).toString();
		}
		return result;
	}

	public void setArttribute(WebElement element, String attName, String attValue) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
		js.executeScript(scriptSetAttrValue, element, attName, attValue);
	}

	public boolean isElementPresent(WebElementFacade element) {
		// Default timeout 5 seconds
		return isElementPresent(element, WAIT_ELEMENT_PRESENT_SEC);
	}

	public boolean isElementPresent(WebElementFacade element, int timeout) {
		if (element == null)
			return false;
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String currentStr = variables.getProperty("webdriver.timeouts.implicitlywait");
		// System.out.println("current implicit wait time: " + currentStr);
		int current = Integer.parseInt(currentStr);
		this.setImplicitTimeout(1000, ChronoUnit.MILLIS);

		final long watch = System.currentTimeMillis();
		while ((System.currentTimeMillis() - watch) < timeout * 1000) {
			// System.out.println("IsWebElementPresent start to wait: " + (System.currentTimeMillis() - watch));
			try {
				if (element.isPresent()) {
					this.setImplicitTimeout(current, ChronoUnit.MILLIS);
					return true;
				}
			} catch (NoSuchElementException e) {
				System.out.println("waitUntilElementsAppear exception: \n" + e.toString() + (System.currentTimeMillis() - watch));
			}
		}
		System.out.println("element not displayed within timer, timeout!");
		// set timer back before return
		this.setImplicitTimeout(current, ChronoUnit.MILLIS);
		return false;
	}

	public boolean isElementNotVisible(WebElementFacade element, int timeout) {
		if (element == null)
			return false;
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String currentStr = variables.getProperty("webdriver.timeouts.implicitlywait");
		// System.out.println("current implicit wait time: " + currentStr);
		int current = Integer.parseInt(currentStr);
		this.setImplicitTimeout(1000, ChronoUnit.MILLIS);

		final long watch = System.currentTimeMillis();
		while ((System.currentTimeMillis() - watch) < timeout * 1000) {
			// System.out.println("IsWebElementPresent start to wait: " + (System.currentTimeMillis() - watch));
			try {
				if (!element.isCurrentlyVisible()) {
					this.setImplicitTimeout(current, ChronoUnit.MILLIS);
					return true;
				}
			} catch (NoSuchElementException e) {
				System.out.println("waitUntilElementsAppear exception: \n" + e.toString() + (System.currentTimeMillis() - watch));
			}
		}
		System.out.println("element not displayed within timer, timeout!");
		// set timer back before return
		this.setImplicitTimeout(current, ChronoUnit.MILLIS);
		return false;
	}

	public boolean isElementVisible(WebElementFacade element, int timeout) {
		if (element == null)
			return false;
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String currentStr = variables.getProperty("webdriver.timeouts.implicitlywait");
		// System.out.println("current implicit wait time: " + currentStr);
		int current = Integer.parseInt(currentStr);
		this.setImplicitTimeout(1000, ChronoUnit.MILLIS);

		final long watch = System.currentTimeMillis();
		while ((System.currentTimeMillis() - watch) < timeout * 1000) {
			// System.out.println("IsWebElementPresent start to wait: " + (System.currentTimeMillis() - watch));
			try {
				if (element.isCurrentlyVisible()) {
					this.setImplicitTimeout(current, ChronoUnit.MILLIS);
					return true;
				}
			} catch (NoSuchElementException e) {
				System.out.println("waitUntilElementsAppear exception: \n" + e.toString() + (System.currentTimeMillis() - watch));
			}
		}
		System.out.println("element not displayed within timer, timeout!");
		// set timer back before return
		this.setImplicitTimeout(current, ChronoUnit.MILLIS);
		return false;
	}

	public WebElementFacade isModaldialogConfirm() {
		return findBy("//*[@class='confirm-dialog']");
	}

	public WebElementFacade button_save() {
		return findBy("//*[@class='confirm-dialog']//*[@id='unsavedChangesOK']");
	}

	public WebElementFacade button_dontsave() {
		return findBy("//*[@class='confirm-dialog']//button[@id='btnDontSave']");
	}


}
