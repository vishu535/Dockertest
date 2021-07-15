package ca.poc.djj.steps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ca.poc.djj.utils.LogSteps;
import ca.poc.djj.utils.WaitUtil;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class CommonSteps {
	@Steps
	LogSteps logsteps;
	public static String writFileNumber = null;
	public static String refile_WritFileNumber = null;
	public static String alertText = null;
	ArrayList<String> windowHandles;
	public static List<String> WritFileNumber_list = new ArrayList<String>();
	public static List<String> debtor_list = new ArrayList<String>();
	public static List<String> valueP = new ArrayList<String>();
	public static List<String> valueC = new ArrayList<String>();
	public static Map<String, List<String>> hmP = new HashMap<String, List<String>>();
	public static Map<String, List<String>> hmC = new HashMap<String, List<String>>();
	// public static boolean jqueryReady = false;

	@Step
	public void debug_log(String myMessage) {
	}

	@Step
	public void execution_log(String myMessage) {
	}

	// @Then("user sees $winName window opened with title $winTitle")
	// public void user_see_window_open_with_title(String winName, String winTitle)
	// {
	// windowHandles = new ArrayList<String>(getDriver().getWindowHandles());
	// assertEquals(2, windowHandles.size());
	// WaitUtil.waitMSeconds(10000);
	// getDriver().switchTo().window(windowHandles.get(1));
	// assertEquals(winTitle.trim(), getDriver().getTitle().trim());
	// }

	// @Then("user sees $winName window opened with no title")
	// public void user_see_window_open_no_title(String winName) {
	// user_see_window_open_with_title(winName, "");
	// }

	// @Then("user sees $winName window closed")
	// public void user_see_window_close() {
	// ArrayList<String> windowHandles = new
	// ArrayList<String>(getDriver().getWindowHandles());
	// assertEquals(1, windowHandles.size());
	// getDriver().switchTo().window(windowHandles.get(0));
	// }

	static String leftrotate(String str, int d) {
		String ans = str.substring(d) + str.substring(0, d);
		return ans;
	}

	// function that rotates s towards right by d
	static String rightrotate(String str, int d) {
		return leftrotate(str, str.length() - d);
	}

	// public void jsClick(WebElementFacade WebElement) {
	// JavascriptExecutor executor = (JavascriptExecutor)
	// searchwritspage.getDriver();
	// executor.executeScript("arguments[0].click();", WebElement);
	// }
	//
	// public void jsClear(WebElementFacade WebElement) {
	// JavascriptExecutor executor = (JavascriptExecutor)
	// searchwritspage.getDriver();
	// executor.executeScript("arguments[0].value ='';", WebElement);
	//
	// }
	//
	// public void jsType(WebElementFacade WebElement, String text) {
	// JavascriptExecutor executor = (JavascriptExecutor)
	// searchwritspage.getDriver();
	// executor.executeScript("arguments[0].value='" + text + "';", WebElement);
	// }

	public boolean isAlertPresent(PageObject pagename) {
		try {
			pagename.getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public void clickSafelyByWait(WebElement we) {
		WaitUtil.waitMSeconds(180000);
		try {
			we.click();
			return;
		} catch (Exception e) {
			System.out.println("clickSafelyByWait exception is: " + e.toString());
		}
	}


	public void waitClick(WebElement we) {
		WaitUtil.waitMSeconds(20000);
		try {
			we.click();
			return;
		} catch (Exception e) {
			System.out.println("clickSafelyByWait exception is: " + e.toString());
		}
	}

	public void elementToBeClickable(WebElement we, PageObject po, long timeout) {
		WebDriverWait wait = new WebDriverWait(po.getDriver(), timeout);
		wait.until(ExpectedConditions.elementToBeClickable(we));
	}

	public void elementToBeVisible(WebElement we, PageObject po, long timeout) {
		WebDriverWait wait = new WebDriverWait(po.getDriver(), timeout);
		wait.until(ExpectedConditions.visibilityOf(we));
	}

	public static String validateHoliday() throws ParseException {
		String effectiveDate;
		effectiveDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().plusDays(1)).toString();
		String input_date = effectiveDate;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dt1 = format1.parse(input_date);
		DateFormat format2 = new SimpleDateFormat("EEEE");
		String finalDay = format2.format(dt1);
		if (finalDay.contains("Sat") || finalDay.contains("Sun")) {
			effectiveDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().plusDays(2)).toString();
		}
		return effectiveDate;
	}

	public static String validateHolidayForExpiry() throws ParseException {
		String expiryDate;
		expiryDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().plusYears(6).plusDays(1))
				.toString();
		String input_date = expiryDate;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dt1 = format1.parse(input_date);
		DateFormat format2 = new SimpleDateFormat("EEEE");
		String finalDay = format2.format(dt1);
		if (finalDay.contains("Sat") || finalDay.contains("Sun")) {
			expiryDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().plusYears(6).plusDays(2))
					.toString();
		}
		return expiryDate;
	}

	public static String validateWeekend(String inputDate) throws ParseException {
		String input_date = inputDate;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dt1 = format1.parse(input_date);
		DateFormat format2 = new SimpleDateFormat("EEEE");
		String finalDay = format2.format(dt1);
		return finalDay;

	}
	
	public boolean JSClick(WebElement we, PageObject po, long timeout) throws Exception {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(po.getDriver(), timeout);
			wait.until(ExpectedConditions.elementToBeClickable(we));
			// WebElement element =
			JavascriptExecutor executor = (JavascriptExecutor) we;
			executor.executeScript("arguments[0].click();", we);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();

		}

		return flag;
	}
/**
 * 
 * @param we
 * @param po
 * @param date should be in format DD/MM/YYYY
 */
	public void calendarDate(PageObject po, String date) {
	//	we.click();
		String[] dt ;
		if(date.contains("-")) {
		//	date.replace("-", "/");
			dt = date.split("-");
			System.out.println("replaced"+date);
		}else {
			dt = date.split("/");
		}
		System.out.println("date-"+date);
		System.out.println("date:"+dt[0]+"month:"+dt[1]+"year:"+dt[2]);
		Select selectmonth = new Select(po.getDriver().findElement(By.cssSelector("#ui-datepicker-div [class=ui-datepicker-month]")));
		selectmonth.selectByValue(String.valueOf(Integer.parseInt(dt[0])-1));
		Select selectyear = new Select(po.getDriver().findElement(By.cssSelector("#ui-datepicker-div [class=ui-datepicker-year]")));
		if(dt[2].length()==2) {
			dt[2]="20"+dt[2];
		}
		selectyear.selectByValue(dt[2]);
		po.getDriver().findElement(By.id("idDay-"+dt[1])).click();	
	}
	
	public void enterTextAndClick(String Field, PageObject po, String text) throws InterruptedException {
		po.getDriver().findElement(By.xpath("//label[text()='" + Field + "']/following-sibling::input")).click();
		po.getDriver().findElement(By.xpath("//label[text()='" + Field + "']/following-sibling::input")).clear();
		for (int i = 0; i < text.length(); i++){
	        char c = text.charAt(i);
	        Thread.sleep(100);
	        String str = new StringBuilder().append(c).toString();
	        po.getDriver().findElement(By.xpath("//label[text()='" + Field + "']/following-sibling::input")).sendKeys(str);    
	    }   
		Thread.sleep(500);
		po.withAction().sendKeys(Keys.chord(Keys.ENTER)).build().perform();
		Thread.sleep(1000);
		po.withAction().sendKeys(Keys.chord(Keys.TAB)).build().perform();
		/*
		 * try { WebDriverWait wait = new WebDriverWait(po.getDriver(), 60000);
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * "//form[contains(@class,'popupShadow')]")));
		 * jsClick(po.getDriver().findElement(By.
		 * xpath("//form[contains(@class,'popupShadow')]//div[@class='rowTemplate listItem']/div[3] //input[@title='"
		 * +text+"']"))); }catch(ElementNotInteractableException ex) {
		 * Thread.sleep(3000);
		 * po.withAction().sendKeys(Keys.chord(Keys.ARROW_RIGHT)).build().perform();
		 * po.withAction().sendKeys(Keys.chord(Keys.TAB)).build().perform();
		 * po.withAction().sendKeys(Keys.chord(Keys.ENTER)).build().perform(); }
		 */
	}
	
	
	
	public void waitForInVisibilityOfElement(WebElement we, PageObject po, long timeout) {
		WebDriverWait wait = new WebDriverWait(po.getDriver(), timeout);
		try {
			wait.until(ExpectedConditions.invisibilityOf(we));

		} catch (Exception e) {
			// As the name indicates, this method is intended NOT to throw an exception,
			// even when there is one.
		}
	}
	
	
	
		public String getDate() {
		SimpleDateFormat ft = new SimpleDateFormat("M/dd/YYYY");

		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar);
		calendar.add(Calendar.DATE, -45);
		System.out.println(calendar.getTime());
		Date d = calendar.getTime();
		return ft.format(d);

	}

	public String getTicketDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyy");
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.MONTH, -1);
		int lastDay = gc.getActualMaximum(Calendar.DATE);
		gc.set(Calendar.DATE, lastDay - 6);
		return sdf.format(gc.getTime());

	}

	
	private static final String NUMERIC_STRING = "0123456789";
	private static final String ALPHABET_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String randomAlphaNumeric(int strLength, int alphabetStart) {
		StringBuilder builder = new StringBuilder();
		double randValue;
		int character;
		while (strLength-- != 0) {
			randValue = Math.random();
			if (builder.length() >= alphabetStart) {
				character = (int) (randValue * NUMERIC_STRING.length());
				builder.append(NUMERIC_STRING.charAt(character));
			} else {
				character = (int) (randValue * ALPHABET_STRING.length());
				builder.append(ALPHABET_STRING.charAt(character));
			}
		}
		return builder.toString();
	}

	public static String RandNumeric(int size) {
		long rand = 0;
		rand = Math.round(Math.random() * (Math.pow(10, size)));
		// System.out.println(rand);
		return Long.toString(rand);
	}
	public static String numberdatetime() {
		Date date = new Date();
		long time = date.getTime();
		return (Long.toString(time)).substring(7);
	}

	public static String getExpirationDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyy");
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.MONTH, -1);
		int lastDay = gc.getActualMaximum(Calendar.DATE);
		gc.set(Calendar.DATE, lastDay);
		return sdf.format(gc.getTime());
	}

	public static String getTktDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyy");
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.MONTH, -1);
		int firstDay = gc.getActualMinimum(Calendar.DATE);
		gc.set(Calendar.DATE, firstDay + 6);
		return sdf.format(gc.getTime());
	}

	public static String getEffectiveDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyy");
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.MONTH, -1);
		int firstDay = gc.getActualMinimum(Calendar.DATE);
		gc.set(Calendar.DATE, firstDay);
		return sdf.format(gc.getTime());
	}

	public static String getShippedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyy");
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.MONTH, -1);
		int firstDay = gc.getActualMinimum(Calendar.DATE);
		gc.set(Calendar.DATE, firstDay + 17);
		return sdf.format(gc.getTime());
	}

	
		/*
	 * String source = "C:/--/source"; File srcDir = new File(source);
	 * 
	 * String destination = "C:/--/destination"; File destDir = new
	 * File(destination);
	 * 
	 * try { FileUtils.copyDirectory(srcDir, destDir); } catch (IOException e) {
	 * e.printStackTrace(); }
	 */
	
	

}
