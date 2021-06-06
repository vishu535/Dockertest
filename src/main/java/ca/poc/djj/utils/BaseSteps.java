package ca.poc.djj.utils;

import static ca.poc.djj.utils.WaitUtil.waitUntil;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.script.ScriptException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.WebDriverFacade;

public class BaseSteps {

	@Steps(shared = true)
	public LogSteps logSteps;
	public static Workbook workbook;
	public static int verificationLevel = 0;

	public static boolean proceedPayment = false;

	public static String paymentType = "";

	public static String purchaseOptions = "";
	public static boolean needDelivery = false;
	public static boolean hasCertifiedCopy = false;

	private static boolean isFrenchSession = false;

	private static String currentWinCaption = "";

	private static final String PROPERTIES_NGTV_LOCALE = "properties/ngtv";

	protected BasePage basePage;

	private final String ACTION_ARROW_UP = Keys.chord(Keys.ARROW_UP);

	private final String ACTION_ARROW_DOWN = Keys.chord(Keys.ARROW_DOWN);

	private final String ACTION_ARROW_RIGHT = Keys.chord(Keys.ARROW_RIGHT);

	private final String ACTION_ARROW_LEFT = Keys.chord(Keys.ARROW_LEFT);

	private final String ACTION_ENTER = Keys.chord(Keys.ENTER);

	private final String ACTION_TAB = Keys.chord(Keys.TAB);

	public static int customercarereport = 0;

	public static int gwhreport = 0;

	// private final String ACTION_SHIFT = Keys.chord(Keys.LEFT_SHIFT);

	private final String ACTION_SPACE = Keys.chord(Keys.SPACE);

	private final String ACTION_DELETE = Keys.chord(Keys.DELETE);

	private final String ACTION_ESCAPE = Keys.chord(Keys.ESCAPE);

	private final int FULLSCREEN_WIDTH = 1920;

	private final int FULLSCREEN_HEIGHT = 1080;

	public static int report = 0;

	public static String matterNo = null;

	public static String globalfileNumber = null;

	public static String surveyor = null;

	public static String surName = null;

	public static String rand = RandomStringUtils.randomNumeric(5);

	public static String emailidrand = "Auto" + RandomStringUtils.randomNumeric(3);

	public static String location_unit_level_popUp = null;

	public final static String TABLE_PATH = "testData";

	public final static String CSV_PATH = "\\csvdata\\";

	public final static String CMDOW_EXE = "C:\\COMMON-LIB\\selenium\\projects\\common-lib\\cmdow.exe";

	public final static String CMDOW_FILE = "cmdow.exe";

	public final static String COMMON_LIB_PROJ_FOLDER = "C:\\COMMON-LIB\\selenium\\projects\\common-lib\\";

	public final static String COMMON_LIB_ROOT_FOLDER = "C:\\COMMON-LIB\\";

	public static final String JAVASCRIPT_WAVE_RUNNER = "var waveExtensionPath = " + "'chrome-extension://jbbplnpkjmmeebjpijfedlgcdilocofh/';var head = document.getElementsByTagName('head')[0];var "
			+ "waveStyle = document.createElement('link');var waveScript = document.createElement('script');waveStyle" + "" + ""
			+ ".setAttribute('type', 'text/css');waveStyle.setAttribute('rel', 'stylesheet');waveStyle.setAttribute('href', "
			+ "waveExtensionPath + 'styles/report-ext.css');waveScript.setAttribute('type', 'text/javascript');waveScript" + ""
			+ ".setAttribute('src', waveExtensionPath + 'wave.min.js');head.appendChild(waveStyle);head.appendChild(waveScript);";

	// move from WinHandlerSteps
	ArrayList<String> windowHandles;
	public static String mainWindowHandle = null;
	private static int last_max_wh_Count = 0;
	public static String alertMessage = "";

	public static String myTimeStamp() {
		return "[" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + "]";
	}

	public void waitLoadingDisappeared() {
		waitUntil(() -> !basePage.getSpinningWheel().isCurrentlyVisible());
		waitUntil(() -> !basePage.getSpinner().isCurrentlyVisible());
		waitUntil(() -> !basePage.getSystemWaitGlass().isCurrentlyVisible());
		// WaitUtil.waitMSeconds(500);
	}

	

	public void userPressesKey(final String key) {
		basePage.getActiveElement().sendKeys(key);
	}

	public void userPressesKeyUp() {
		userPressesKey(ACTION_ARROW_UP);
	}

	public void userPressesKeyDown() {
		userPressesKey(ACTION_ARROW_DOWN);
	}

	public void userPressesKeyRight() {
		userPressesKey(ACTION_ARROW_RIGHT);
	}

	public void userPressesKeyLeft() {
		userPressesKey(ACTION_ARROW_LEFT);
	}

	public void userPressesKeyTab() {
		userPressesKey(ACTION_TAB);
	}

	public void userPressesKeyTabNTimes(int n) {
		for (int i = 0; i < n; i++) {
			userPressesKeyTab();
		}
	}

	public void userPressesKeysShiftTab() {
		new Actions(getDriver()).keyDown(Keys.SHIFT).sendKeys(ACTION_TAB).build().perform();
	}

	// press key ALT, press arrow up, release key ALT. Simple double sendKeys()
	// doesn't apply here
	public void userPressesKeysAltAndKeyUp() {
		new Actions(getDriver()).keyDown(Keys.ALT).sendKeys(Keys.ARROW_UP).keyUp(Keys.ALT).perform();
	}

	// press key ALT, press arrow down, release key ALT. Simple double sendKeys()
	// doesn't apply here
	public void userPressesKeysAltAndKeyDown() {
		new Actions(getDriver()).keyDown(Keys.ALT).sendKeys(Keys.ARROW_DOWN).keyUp(Keys.ALT).perform();
	}

	public void userPressesKeysTabNTimes(int n) {
		for (int i = 0; i < n; i++) {
			userPressesKeysShiftTab();
		}
	}

	public void userPressesKeyEnter() {
		userPressesKey(ACTION_ENTER);
	}

	public void userPressesKeySpace() {
		userPressesKey(ACTION_SPACE);
	}

	public void userPressesKeyDelete() {
		userPressesKey(ACTION_DELETE);
	}

	public void userPressesKeyEscape() {
		userPressesKey(ACTION_ESCAPE);
	}

	public void robotPressedKeyEscape() throws AWTException, InterruptedException {
		Robot robot = new Robot();
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ESCAPE);
	}

	public void thereAreNoAccessibilityErrors() {
		assertEquals("Quantity of accessibility errors:", 0, basePage.getQuantityOfErrors());

	}

	public void thereAreNoAccessibilityErrorsAdministration() {
		thereAreNoAccessibilityErrorsForElement(basePage.adminPanel);
	}

	public void thereAreNoAccessibilityErrorsMessages() {
		thereAreNoAccessibilityErrorsForElement(basePage.panelMessages);
	}

	public void thereAreNoAccessibilityErrorsProjects() {
		thereAreNoAccessibilityErrorsForElement(basePage.panelProjects);
	}

	public void thereAreNoAccessibilityDocketHeader() {
		thereAreNoAccessibilityErrorsForElement(basePage.panelDocketHeader);
	}

	public void thereAreNoAccessibilityInstrumentBranchContainer() {
		thereAreNoAccessibilityErrorsForElement(basePage.containerInstrumentBranch);
	}

	private void thereAreNoAccessibilityErrorsForElement(final String xpath) {
		assertEquals("Quantity of accessibility errors:", 0, basePage.getQuantityOfErrors(xpath));
	}

	// at the beginning to wait for web driver instance init successfully
	public WebDriver get_web_driver() {
		WebDriver myDriver = null;
		final long watch = System.currentTimeMillis();

		while ((System.currentTimeMillis() - watch) < 60000) {
			try {
				logSteps.debug_log("\n" + "Begin to init web driver." + myTimeStamp());
				myDriver = getDriver();
				if (myDriver != null) {
					logSteps.debug_log("\n" + "Init web driver sucessfully." + myTimeStamp());
					break;
				} else
					logSteps.debug_log("\n" + "Get null web driver without exception." + myTimeStamp());
			} catch (Exception e) {
				logSteps.debug_log("\n" + "Init web driver has exception: " + e.toString() + myTimeStamp());
			}
		}
		return myDriver;
	}



	public BasePage getBasePage() {
		return basePage;
	}

	public void setBasePage(BasePage basePage) {
		this.basePage = basePage;
	}

	// to hasElementAttributes blocked cases input error message cases
	public void user_set_text_to_clipboard_to_paste(String text) {
		try {
			StringSelection selection = new StringSelection(text);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
		} catch (Exception e) {
			fail("fail to use clipboard to paste the text!");
		}
	}

	// clear cookies from browser session
	public void clearChromeBrowserCache() {
		getDriver().manage().deleteAllCookies();

	}

	public void set_session_French() {
		isFrenchSession = true;
	}

	public void set_session_English() {
		isFrenchSession = false;
	}

	public boolean isAlertPresent() {
		try {
			getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	@Step
	public boolean is_session_french() {
		return isFrenchSession;
	}

	public static String getLocaleText(final String label) {
		Locale locale = isFrenchSession ? Locale.CANADA_FRENCH : Locale.CANADA;
		return ResourceBundle.getBundle(PROPERTIES_NGTV_LOCALE, locale).getString(label.toUpperCase());
	}

	public static List<String> getArrayFromPropertiesText(final String label) {
		Locale locale = isFrenchSession ? Locale.CANADA_FRENCH : Locale.CANADA;
		return Arrays.asList(ResourceBundle.getBundle(PROPERTIES_NGTV_LOCALE, locale).getString(label.toUpperCase()).split(","));
	}

	@Given("Verification Level is set to $sVerification_Level")
	public void set_VerifyWritSearchByPage_Level(String sVerification_Level) {
		verificationLevel = 0;
		if (sVerification_Level == null)
			sVerification_Level = "";
		if (!sVerification_Level.equals("") && !sVerification_Level.equals("<Verification_Level>")) {
			verificationLevel = Integer.parseInt(sVerification_Level);
		} else {
			logSteps.execution_log("Verification_Level not specified. Setting Verification_Level to 0.");
		}
	}

	@Given("Proceed with Payment indicator $sProceedPayment is set to {Y|y|TRUE|true} then process the following")
	public void set_proceedPayment(String sProceedPayment) {
		proceedPayment = false;
		if (sProceedPayment == null)
			sProceedPayment = "N";
		if (sProceedPayment.toUpperCase().equals("Y") || sProceedPayment.toUpperCase().equals("TRUE")) {
			proceedPayment = true;
			logSteps.execution_log("Setting Proceed Payment to true");
		} else {
			logSteps.execution_log("Setting Proceed Payment to false");
		}
	}

	@Step
	public boolean getProceedPayment() {
		return proceedPayment;
	}

	@Then("date picker is visible")
	public void checkDatePickerState() {
		assertTrue("date picker is in wrong state", basePage.pickerDate.isCurrentlyVisible());
	}

	@Then("date picker is not visible")
	public void checkDatePickerInvisiblity() {
		assertFalse("date picker is in wrong state", basePage.pickerDate.isCurrentlyVisible());
	}

	@When("user chooses previous date from date picker")
	public void chooseDateFromPicker() {
		basePage.previousDayFromDatePicker().click();
	}

	@When("user chooses current date from date picker")
	public void chooseCurrentDateFromPicker() {
		basePage.currentDayFromDatePicker.click();
	}

	// =========================================== Resize Screen =========================================================
	public void userResizesBrowsersWindow(ExamplesTable parameters) {
		boolean replaceNamedParameters = true;
		int width = parameters.getRowAsParameters(0, replaceNamedParameters).valueAs("Width", Integer.class);
		int height = parameters.getRowAsParameters(0, replaceNamedParameters).valueAs("Height", Integer.class);
		getDriver().manage().window().setSize(new Dimension(width, height));
	}

	public void enlargeTestScreen() {
		Dimension currentD = basePage.getDriver().manage().window().getSize();
		if (currentD.width != FULLSCREEN_WIDTH || currentD.height != FULLSCREEN_HEIGHT) {
			Dimension fullD = new Dimension(FULLSCREEN_WIDTH, FULLSCREEN_HEIGHT - 40);
			Point targetPosition = new Point(0, 0);
			basePage.getDriver().manage().window().setSize(fullD);
			basePage.getDriver().manage().window().setPosition(targetPosition);
		}
	}

	


	@Step
	@Then("user verify PDF document $document in $path")
	public void user_verify_PDF_document(String document, String path) {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String captureBaseline = variables.getProperty("test.captureBaseline");
		if (captureBaseline == null) {
			captureBaseline = "false";
		}
		if (!captureBaseline.equals("true")) {
			String folder = path;
			if (path.trim().equals("output folder")) {
				folder = variables.getProperty("test.output.folder");
			}

			List<String> excludePattern = PDFCompare.initPattern();
			String testEnv = variables.getProperty("test.TestEnvironment");
			testEnv = testEnv.equals("") ? "UAT" : testEnv;
			String outputFolder = folder + "\\" + testEnv + "\\" + variables.getProperty("test.story.name");

			File resultFile = new File(outputFolder + "/DocFiles/" + document + ".pdf");
			File baselineFile = new File(outputFolder + "/DocFilesBaseline/" + document + ".pdf");

			if (!resultFile.exists()) {
				logSteps.execution_log("PDF result file not found: " + resultFile);
				assertFalse("PDF Result file not found.", true);
			} else if (!baselineFile.exists()) {
				logSteps.execution_log("PDF baseline file not found: " + baselineFile);
				assertFalse("PDF baseline file not found: " + baselineFile, true);
			} else {
				boolean compareResult = PDFCompare.pdfCompare(outputFolder, document + ".pdf", excludePattern);
				if (compareResult) {
					logSteps.execution_log("PDF matches baseline.");
				} else {
					logSteps.execution_log("PDF not matches baseline.");
				}
				assertTrue("PDF matches baseline", compareResult);
			}
		}
	}

		
	@Step
	private String get_field_value(String className, String fieldName) throws Exception {
		String fieldValue = null; // final value to return

		Class<?> clazz = Class.forName(className);
		Object obj = clazz.newInstance();
		// assign WebDriver from current WebDriver to page object WebDriver
		Method setDvr = clazz.getMethod("setDriver", WebDriver.class);
		setDvr.invoke(obj, getDriver());

		// check for "()" in the field name, if exists get the index within
		String weName = fieldName;
		int weIndex = -1;
		int bracPos = fieldName.indexOf("(");
		if (bracPos > 0) {
			weName = fieldName.substring(0, bracPos).trim();
			weIndex = Integer.parseInt(getStringBetweenBrackets(fieldName, "("));
		}

		// Search name thru all Field first
		WebElementFacade findObject = null;
		boolean found = false;
		Field[] fields = clazz.getFields();
		for (Field ifield : fields) {
			// System.out.println(ifield.getName());
			if (ifield.getName().equals(weName) && (ifield.getType() == WebElementFacade.class)) {
				System.out.println("Field matched: " + weName);
				Field clazzField = clazz.getDeclaredField(weName);
				findObject = (WebElementFacade) clazzField.get(obj);
				found = true;
				break;
			}
		}

		// if not exist in Field, search name in Method
		if (!found) {
			Method[] methods = clazz.getMethods();
			for (Method imethod : methods) {
				// System.out.println(imethod.getName());
				if (imethod.getName().equals(weName) && (imethod.getReturnType() == WebElementFacade.class)) {
					// System.out.println("Method matched: " + weName);
					Method clazzMethod = clazz.getMethod(weName, int.class);
					findObject = (WebElementFacade) clazzMethod.invoke(obj, weIndex);
					found = true;
					break;
				}
			}
		}

		if (found) {
			// below need to be extended for other application besides csp for (select/option)(ui/li)
			if (!basePage.isElementPresent(findObject, 10)) {
				Assert.fail("Element not found. Timeout: 10 seconds");
			}
			if (!findObject.isCurrentlyVisible()) {
				Assert.fail("Element not currently visible.");
			}
			String tag = findObject.getTagName();
			String type = findObject.getAttribute("type");
			if (type == null)
				type = "";
			// System.out.println("object tag: " + tag + " object type: " + type);
			if (tag.equals("input")) {
				if (type.equals("checkbox") || type.equals("radio")) {
					boolean isSelected = findObject.isSelected();
					if (isSelected)
						fieldValue = "selected";
					else
						fieldValue = "not selected";
				} else
					fieldValue = findObject.getValue();
			} else if (tag.equals("select")) {
				fieldValue = findObject.getSelectedVisibleTextValue();
			}
		}
		return fieldValue;
	}

	// ====================================== data driven functions =========================================================


	@Then("user clean up temp csv file")
	public void userRemoveTempDataFile() {
		String current_foler = getCurrentTestFolderName();
		String csv_path = current_foler + CSV_PATH;
		String story_name = getCurrentStoryName();
		// String full_path = csv_path + story_name + ".csv";
		File folder = new File(csv_path);
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			for (File f : files) {
				if (f.isFile() && f.getName().contains(story_name) && f.getName().contains(".csv"))
					f.deleteOnExit();
			}
		}
	}

	// If there is no property defined in Serenity.properties, will cause null point exception
	public static String getCurrentTestFolderName() {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String folder_name = variables.getProperty("test.folder");
		System.out.println("-------folder_name-------"+folder_name);
		if (folder_name == null)
			return "";
		return folder_name.trim();
	}

	public static String getCurrentTestDataFolderName() {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String folder_name = variables.getProperty("test.data.folder");
		if (folder_name == null)
			return "";
		return folder_name.trim();
	}

	public static String getCurrentStoryName() {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String story_name = variables.getProperty("test.story.name");
		if (story_name == null) {
			Assert.fail("Serenity.properties has problem, missing test.story.name.");
		}
		return story_name.trim();
	}

	public static String getCurrentEnv() {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String env_name = variables.getProperty("test.TestEnvironment");
		if (env_name == null)
			return "";
		return env_name.trim();
	}

	public static String getCurrentTestType() {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String test_type = variables.getProperty("test.type");
		if (test_type == null)
			return "";
		return test_type.trim();
	}

	public static String getCurrentAppName() {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String folder_name = variables.getProperty("test.folder");
		if (folder_name == null) {
			Assert.fail("Serenity.properties has problem, missing test.folder.");
		}

		String app_name = folder_name.substring(folder_name.lastIndexOf("\\") + 1);
		app_name = app_name.replace("QASS", ""); // for QASSNGTV
		return app_name;
	}

	public static String getTablePath() {
		String current_folder = getCurrentTestFolderName();
		String current_data_foler = getCurrentTestDataFolderName();
		String current_type = getCurrentTestType(); // for NGTV: Monitoring/Integrity
		String current_env = getCurrentEnv();
		String table_path = null;

		// Try specified data path
		table_path = current_data_foler + "\\";
		if (!current_type.trim().equals(""))
			current_type = current_type + "\\";
		File f = new File(table_path + current_type + current_env + "\\");
		if (f.exists() && f.isDirectory()) {
			table_path = table_path + current_type + current_env + "\\";
		} else {
			// Try default(local) data path
			table_path = current_folder + TABLE_PATH;
			f = new File(table_path + current_type + current_env + "\\");
			if (f.exists() && f.isDirectory()) {
				table_path = table_path + current_type + current_env + "\\";
			}
		}
		// System.out.println("Data table path: " + table_path);
		return table_path;
	}

	public String getStartSheetName(String story_name) {
		String[] story_split_name = story_name.split("_");
		String project_name = story_split_name[0] + "_";
		String sheet_name = story_name.replace(project_name, "");
		return sheet_name;
	}

	// only work for contains 1 pair of brackets
	public String getStringBetweenBrackets(String strInput, String lBracket) {
		String rBracket;
		String strOutPut = "";
		if (lBracket == "{")
			rBracket = "}";
		else if (lBracket == "(")
			rBracket = ")";
		else if (lBracket == "[")
			rBracket = "]";
		else
			return strOutPut;

		strOutPut = strInput.substring(strInput.indexOf(lBracket) + 1);
		strOutPut = strOutPut.substring(0, strOutPut.indexOf(rBracket));
		return strOutPut;
	}

	// ===================================== Date driven to get data as checkpoint =====================================================
	

		public String getTotalFee(String fee1, String fee2, String fee3) {
		Double dValue = Double.parseDouble(fee1) + Double.parseDouble(fee2) + Double.parseDouble(fee3);
		dValue = Math.round(dValue * 100D) / 100D;
		return String.format("%.2f", dValue);
	}

	public String getTotalFee(String fee1, String fee2) {
		Double dValue = Double.parseDouble(fee1) + Double.parseDouble(fee2);
		dValue = Math.round(dValue * 100D) / 100D;
		return String.format("%.2f", dValue);
	}

	// now move to each page to handle
	public void closeModalDialog() {
		Alert alerts = basePage.getDriver().switchTo().alert();
		alerts.dismiss();
	}

	

	public void verifyLink(WebElementFacade link, boolean isNewWindowOpen) {
		String hrefStr = link.getAttribute("href");
		String linkText = link.getText().trim();
		boolean isDisabled = false;

		if (hrefStr.contains("javascript:")) {
			hrefStr = hrefStr.replace("javascript:", ""); // href="javascript:void(0);"
			if (hrefStr.contains("void(0)")) {
				logSteps.execution_log("\n" + "Link: " + linkText + " is disabled, href=void(0)");
				isDisabled = true;
			} else
				logSteps.execution_log("\n" + "Link: " + linkText + " is java script: " + hrefStr);
			ExecuteJS(hrefStr);

			if (isNewWindowOpen && !isDisabled) {
				logSteps.debug_log("\n" + "The link click opens a new window, verify new window opened.");
				// assertTrue(winHandlerSteps.check_window_open_with_url_contains(""));
				assertTrue(check_window_open_with_url_contains(""));
			} else if (!isDisabled) {
				logSteps.execution_log("\n" + "The link click doesn't open a new window, verify page title change.");
			}

		} else {
			logSteps.execution_log("\n" + "Link: " + linkText + " is common url: " + hrefStr + ", naviget to url.");
			getDriver().navigate().to(hrefStr);

			if (isNewWindowOpen) {
				logSteps.execution_log("\n" + "The link click opens a new window, verify new window opened.");
				// assertTrue(winHandlerSteps.check_window_open_with_url_contains(hrefStr));
				assertTrue(check_window_open_with_url_contains(hrefStr));
			} else {
				logSteps.execution_log("\n" + "The link click doesn't open a new window, verify page title change.");
			}
		}
	}

	public static void ExecuteJS(String jscript) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		System.out.println("Verify javascript link using javascript executor: " + jscript);
		try {
			js.executeScript(jscript);
		} catch (Exception e) {
			System.out.println("Execution java script exception: " + e.toString());
		}
	}

	public void assertCondition(String message, String str1, String condition, String str2) {
		switch (condition) {
		case "equals":
			assertTrue(message + " [" + str1 + "] " + condition + " [" + str2 + "]", str1.equals(str2));
			break;
		case "not equals":
			assertFalse(message + " [" + str1 + "] " + condition + " [" + str2 + "]", str1.equals(str2));
			break;
		case "contains":
			assertTrue(message + " [" + str1 + "] " + condition + " [" + str2 + "]", str1.contains(str2));
			break;
		case "matches": // regex
			assertTrue(message + " [" + str1 + "] " + condition + " [" + str2 + "]", assertMatches(str1, str2));
			break;
		case "partialEquals": // contains without *
			assertTrue(message + " [" + str1 + "] " + condition + " [" + str2 + "]", assertPartialEquals(str1, str2));
			break;
		default:
			logSteps.execution_log("condition unknown: " + condition);
			return;
		}
	}

	// regex match
	public boolean assertMatches(String str1, String str2) {
		return str2.matches(str1);
	}

	// str2 contains str1 except *
	public boolean assertPartialEquals(String str1, String str2) {
		boolean retb = true;
		String[] strParts = str2.split("\\*");

		for (int i = 0; i < strParts.length; i++) {
			if (!str1.contains(strParts[i].trim())) {
				retb = false;
				break;
			}
		}
		return retb;
	}

	public void assertMatchString(String strRegExp, String strInput) {
		assertTrue(strInput.matches(strRegExp));
	}

	public void assertMatchDateTime(String pattern, String strInput) {
		boolean ret = false;
		String myDT = "";
		// int strLen = pattern.length();
		String[] tmpStr = strInput.split("Date/Time:");
		if (tmpStr.length > 1)
			// myDT = tmpStr[1].substring(0, strLen - 1);
			myDT = tmpStr[1].trim();
		else if (tmpStr.length == 1)
			// myDT = tmpStr[0].substring(0, strLen - 1);
			myDT = tmpStr[0].trim();
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			format.parse(myDT);
			ret = true;
		} catch (Exception e) {
			System.out.println("assertMatchDateTime " + e.toString());
		}
		assertTrue(ret);
	}

	// for multiple language
	public void assertMatchDateTime(String language, String pattern, String strInput) {
		boolean ret = false;
		String myDT = "";
		String myPattern = "";

		// int strLen = pattern.length();
		if (language.equals("en")) {
			myDT = strInput.replace("Date/Time:", "");
			myPattern = pattern.replace("Date/Time:", "");
		} else {
			myDT = strInput.replace("Date/Heure:", "");
			myPattern = pattern.replace("Date/Heure:", "");
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(myPattern);
			format.parse(myDT);
			ret = true;
		} catch (Exception e) {
			System.out.println("assertMatchDateTime " + e.toString());
		}
		assertTrue(ret);
	}

	public boolean verifyMatchDateTime(String pattern, String strInput) {
		boolean ret = false;
		String myDT = "";
		int strLen = pattern.length();
		String[] tmpStr = strInput.split("Date/Time:");
		if (tmpStr.length > 1)
			myDT = tmpStr[1].substring(0, strLen - 1);
		else if (tmpStr.length == 1)
			myDT = tmpStr[0].substring(0, strLen - 1);
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			format.parse(myDT);
			ret = true;
		} catch (Exception e) {
			System.out.println("verifyMatchDateTime " + e.toString());
		}
		return ret;
	}

	// duplicate function with getBrowserType() -- need to be removed/refactored later
	public String get_webdriver_driver() {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String driver = "";
		if (variables.getProperty("webdriver.remote.url") == null) {
			driver = variables.getProperty("webdriver.driver");
		} else {
			driver = variables.getProperty("webdriver.remote.driver");
		}
		return driver;
	}

	public String getToolPath(String toolName) {
		String retPath = "";
		File myFile = new File(COMMON_LIB_PROJ_FOLDER + toolName);
		if (myFile.exists())
			retPath = COMMON_LIB_PROJ_FOLDER + toolName;
		else {
			myFile = new File(COMMON_LIB_ROOT_FOLDER + toolName);

			if (myFile.exists())
				retPath = COMMON_LIB_ROOT_FOLDER + toolName;
		}
		return retPath;
	}

	// cmdow.exe -- bring windows to Top base on winCaption -- for pdf save
	public void bringWindowToTop(String winCaption) {
		String cmdowPath = getToolPath(CMDOW_FILE);
		if (cmdowPath.equals(""))
			Assert.fail("cmdow.exe was deleted by Symantec as virus.");

		try {
			Runtime.getRuntime().exec(cmdowPath + " \"" + winCaption + "\" /TOP /ACT /MAX");
			logSteps.execution_log("Bring window " + winCaption + " with cmdow to Top.");
		} catch (IOException e) {
			System.out.println("bringWindowToTop exception: " + e.toString());
		}
		return;
	}

	// cmdow.exe -- bring windows not to Top base on winCaption -- recover original status
	public void bringWindowNotToTop(String winCaption) {
		String cmdowPath = getToolPath(CMDOW_FILE);
		if (cmdowPath.equals(""))
			Assert.fail("cmdow.exe was deleted by Symantec as virus.");

		try {
			Runtime.getRuntime().exec(cmdowPath + " \"" + winCaption + "\" /NOT /INA");
			logSteps.execution_log("Bring window " + winCaption + " with cmdow to Bottom.");
		} catch (IOException e) {
			System.out.println("bringWindowNotToTop exception: " + e.toString());
		}
	}

	// before open pdf windows, save current windows caption
	public void saveCurrentWindowCaption(String browserType) {
		String line, bt;
		bt = browserType;
		if (browserType.equals("ie"))
			bt = "iexplore";

		String cmdowPath = getToolPath(CMDOW_FILE);
		if (cmdowPath.equals(""))
			Assert.fail("cmdow.exe was deleted by Symantec as virus.");

		try {
			Process process = Runtime.getRuntime().exec(cmdowPath + " /t /f");
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = input.readLine()) != null) {
				if (line.contains(bt)) {
					System.out.println(line);
					currentWinCaption = line.substring(line.indexOf(bt) + bt.length(), line.length()).trim();
					System.out.println("current window caption is: " + currentWinCaption);
				}
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// after open pdf window, get new pdf window caption to bring it to Top
	public String getNewWindowCaption(String browserType) {
		String retStr = "";
		String line, bt;
		bt = browserType;
		if (browserType.equals("ie"))
			bt = "iexplore";

		if (currentWinCaption.equals("")) // no need to bring new window to top
			return retStr;

		String cmdowPath = getToolPath(CMDOW_FILE);
		if (cmdowPath.equals(""))
			Assert.fail("cmdow.exe was deleted by Symantec as virus.");

		try {
			Process process = Runtime.getRuntime().exec(cmdowPath + " /t /f");
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = input.readLine()) != null) {
				if (line.contains(bt)) {
					if (!line.contains(currentWinCaption)) {
						System.out.println(line);
						retStr = line.substring(line.indexOf(bt) + bt.length(), line.length()).trim();
						System.out.println("new window caption is: " + retStr);
						break;
					}
				}
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retStr;
	}

	public boolean waitForPDFViewerProcessStart() {
		String line;
		boolean isPDFStart = false;

		String cmdowPath = getToolPath(CMDOW_FILE);
		if (cmdowPath.equals(""))
			Assert.fail("cmdow.exe was deleted by Symantec as virus.");

		final long watch = System.currentTimeMillis();
		while ((System.currentTimeMillis() - watch) < 20000) {
			try {
				Process process = Runtime.getRuntime().exec(cmdowPath + " /t /f");
				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
				while ((line = input.readLine()) != null) {
					if (line.contains("AcroRd32")) {
						System.out.println("PDF viewer opened");
						isPDFStart = true;
						break;
					}
				}
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (isPDFStart) {
				WaitUtil.waitMSeconds(1000);
				return isPDFStart;
			} else
				System.out.println("PDF viewer not opened, check again...");
		}
		return isPDFStart;
	}

	// ========= Multiple language support ==============================================

		public String getElementText(String className, String fieldName) {
		String elementText = "";
		String fullClassName = "ca.teranet.pages." + className;

		try {
			elementText = get_field_text(fullClassName, fieldName);
		} catch (Exception e) {
			System.out.println("getElementText exception: " + e.toString());
		}
		return elementText;
	}

	private String get_field_text(String className, String fieldName) throws Exception {
		String fieldValue = null; // final value to return

		Class<?> clazz = Class.forName(className);
		Object obj = clazz.newInstance();
		// assign WebDriver from current WebDriver to page object WebDriver
		Method setDvr = clazz.getMethod("setDriver", WebDriver.class);
		setDvr.invoke(obj, getDriver());

		// check for "()" in the field name, if exists get the index within
		String weName = fieldName;
		int weIndex = -1;
		int bracPos = fieldName.indexOf("(");
		if (bracPos > 0) {
			weName = fieldName.substring(0, bracPos).trim();
			weIndex = Integer.parseInt(getStringBetweenBrackets(fieldName, "("));
		}

		// Search name thru all Field first
		WebElementFacade findObject = null;
		boolean found = false;
		Field[] fields = clazz.getFields();
		for (Field ifield : fields) {
			// System.out.println(ifield.getName());
			if (ifield.getName().equals(weName) && (ifield.getType() == WebElementFacade.class)) {
				System.out.println("Field matched: " + weName);
				Field clazzField = clazz.getDeclaredField(weName);
				findObject = (WebElementFacade) clazzField.get(obj);
				found = true;
				break;
			}
		}

		// if not exist in Field, search name in Method
		if (!found) {
			Method[] methods = clazz.getMethods();
			for (Method imethod : methods) {
				// System.out.println(imethod.getName());
				if (imethod.getName().equals(weName) && (imethod.getReturnType() == WebElementFacade.class)) {
					// System.out.println("Method matched: " + weName);
					Method clazzMethod = clazz.getMethod(weName, int.class);
					findObject = (WebElementFacade) clazzMethod.invoke(obj, weIndex);
					found = true;
					break;
				}
			}
		}

		if (found) {
			// below need to be extended for other application besides csp for
			// (select/option)(ui/li)
			String tag = findObject.getTagName();
			String type = findObject.getAttribute("type");
			if (type == null)
				type = "";
			// System.out.println("object tag: " + tag + " object type: " + type);
			fieldValue = findObject.getText();
		}
		return fieldValue;
	}

	// ============= Windows Handler Steps, WinHandlerSteps causes steps pending ===============

	@Then("user sees new tab opened with title $winTitle")
	public void user_see_new_win_open_with_title(String winTitle) {
		user_see_window_open_with_title("", winTitle);
	}

	@Then("user sees $winName window opened with title $winTitle")
	public void user_see_window_open_with_title(String winName, String winTitle) {
		int mwhCount = 0;
		int retry = 0;
		int maxRetry = 10;
		boolean foundWindow = false;

		// Check if new window opened
		ArrayList<String> windowHandles = null;
		do {
			windowHandles = new ArrayList<String>(getDriver().getWindowHandles()); // this method will gives you the handles of all opened windows
			mwhCount = windowHandles.size();
			System.out.println("Found # of wh:" + mwhCount);
			if (mwhCount < 2) {
				WaitUtil.waitMSeconds(2000);
				retry++;
			}
		} while (mwhCount < 2 && retry < maxRetry);
		assertTrue(mwhCount >= 2);

		// Loop through all windows and check if new title matches
		// this method will gives you the handles of all opened windows
		retry = 0;
		Set<String> s = getDriver().getWindowHandles();
		Iterator<String> ite = null;
		do {
			ite = s.iterator();
			while (ite.hasNext() && !foundWindow) {
				String popupHandle = ite.next().toString();
				getDriver().switchTo().window(popupHandle);
				WaitUtil.waitMSeconds(1000);
				String swinTitle = getDriver().getTitle().trim();
				System.out.println("Current opened window title is: " + swinTitle);
				if (winTitle.trim().equals(swinTitle)) {
					foundWindow = true;
					break;
				}
				retry++;
			}
		} while (!foundWindow && retry < maxRetry);
		assertTrue("Found window with title " + winTitle, foundWindow);

		last_max_wh_Count = mwhCount;
	}

	@Then("user sees $winName window opened with no title")
	public void user_see_window_open_no_title(String winName) {
		user_see_window_open_with_title(winName, "");
	}

	@Then("user sees $winName window closed")
	public void user_see_window_close() {
		ArrayList<String> windowHandles = new ArrayList<String>(getDriver().getWindowHandles());
		int mwhCount = windowHandles.size();
		assertEquals(last_max_wh_Count - 1, mwhCount);
		last_max_wh_Count--;
		getDriver().switchTo().window(windowHandles.get(mwhCount - 1));
	}

	@Step
	public void user_close_win_tab_and_return_home() {
		getDriver().close();
		user_see_window_close();
	}

	@When("user clicks browser Back button")
	public void user_go_back_to_prev_page() {
		getDriver().navigate().back();
	}

	@Then("user closes browser")
	public void user_close_browser() {
		getDriver().quit(); // close all opened tabs and the whole browser
	}

	@Then("user sees $winName window opened with URL containing $url")
	public void user_see_window_open_with_url_contains(String winName, String url) {
		int mwhCount = 0;
		int retry = 0;
		int maxRetry = 20;
		boolean foundWindow = false;

		// Check if new window opened
		ArrayList<String> windowHandles = null;
		do {
			windowHandles = new ArrayList<String>(getDriver().getWindowHandles()); // this method will gives you the handles of all opened windows
			mwhCount = windowHandles.size();
			System.out.println("Found # of wh:" + mwhCount);
			if (mwhCount < 2) {
				WaitUtil.waitMSeconds(3000);
				retry++;
			}
		} while (mwhCount < 2 && retry < maxRetry);
		assertTrue(mwhCount >= 2);

		// Loop through all windows and check if new title matches
		// this method will gives you the handles of all opened windows
		retry = 0;
		Set<String> s = getDriver().getWindowHandles();
		Iterator<String> ite = null;
		do {
			ite = s.iterator();
			while (ite.hasNext() && !foundWindow) {
				String popupHandle = ite.next().toString();
				getDriver().switchTo().window(popupHandle);
				if (getDriver().getCurrentUrl().contains(url.trim())) {
					foundWindow = true;
					break;
				} else {
					WaitUtil.waitMSeconds(3000);
				}
				retry++;
			}
		} while (!foundWindow && retry < maxRetry);
		assertTrue("Found window with URL containing " + url, foundWindow);

		last_max_wh_Count = mwhCount;
	}

	public boolean check_window_open_with_url_contains(String url) {
		int mwhCount = 0;
		int retry = 0;
		int maxRetry = 5;
		boolean foundWindow = false;

		// Check if new window opened
		ArrayList<String> windowHandles = null;
		do {
			windowHandles = new ArrayList<String>(getDriver().getWindowHandles()); // this method will gives you the handles of all opened windows
			mwhCount = windowHandles.size();
			System.out.println("Found # of wh:" + mwhCount);
			if (mwhCount < 2) {
				WaitUtil.waitMSeconds(3000);
				retry++;
			}
		} while (mwhCount < 2 && retry < maxRetry);

		// Loop through all windows and check if new title matches
		// this method will gives you the handles of all opened windows
		retry = 0;
		Set<String> s = getDriver().getWindowHandles();
		Iterator<String> ite = null;
		do {
			ite = s.iterator();
			while (ite.hasNext() && !foundWindow) {
				String popupHandle = ite.next().toString();
				getDriver().switchTo().window(popupHandle);
				if (getDriver().getCurrentUrl().contains(url.trim())) {
					System.out.println("Found window with URL containing " + url);
					foundWindow = true;
					break;
				} else {
					WaitUtil.waitMSeconds(3000);
				}
				retry++;
			}
		} while (!foundWindow && retry < maxRetry);

		last_max_wh_Count = mwhCount;
		return foundWindow;
	}

	@Then("user sees error message on modal dialog: $message and clicks {OK|ok|Ok}")
	public void user_see_modal_dialog_error_message(String message) {
		try {
			waitLoadingDisappeared();
		} catch (Exception e) {
			String eMessage = e.getMessage();
			if (eMessage.contains("unexpected alert open")) {
				alertMessage = getStringBetweenBrackets(eMessage, "{");
				alertMessage = alertMessage.replace("Alert text : ", "").trim();
			} else if (eMessage.contains("Modal dialog present with text")) {
				int index1 = eMessage.indexOf(":");
				int index2 = eMessage.indexOf(":", index1 + 1);
				alertMessage = eMessage.substring(index1 + 1, index2).trim();
			} else {
				logSteps.execution_log("\n" + "Do not know how to handle: " + eMessage);
			}
			logSteps.execution_log("\n" + "Captured alert message: " + alertMessage);
			if (!alertMessage.equals("")) {
				assertTrue("Matchted expected message: " + message, message.equals(alertMessage));
				alertMessage = "";
			}

			if (isAlertPresent()) {
				getDriver().switchTo().alert().accept();
			}
		}
	}

	// this function is used for parse list of test datasheet, format as: 1,2,3...,20 or 1,3~20
	public String[] getStringList(String inputStr) {
		String[] midStrList = new String[100];
		int retStr_num = 0;
		String[] strList = inputStr.split(",");

		for (int i = 0; i < strList.length; i++) {
			if (strList[i].contains("~")) {
				String[] substrList = strList[i].split("~"); // 3, 20
				if (substrList.length == 2) {
					int begin = 0, end = 0;
					try {
						begin = Integer.parseInt(substrList[0].trim());
						end = Integer.parseInt(substrList[1].trim());
					} catch (Exception e) {
						Assert.fail("Data format is wrong for x~y, x, y should be number");
					}
					if (begin <= end) {
						for (int k = begin; k <= end; k++) {
							midStrList[retStr_num] = "" + k;
							retStr_num++;
						}
					} else
						Assert.fail("Data format is wrong for x~y: x is bigger than y.");
				} else
					Assert.fail("Data format is wrong for x~y: more than 2 ~ found.");
			} else {
				midStrList[retStr_num] = strList[i];
				retStr_num++;
			}
		}

		String[] retStrList = new String[retStr_num];
		for (int i = 0; i < retStr_num; i++)
			retStrList[i] = midStrList[i];

		return retStrList;
	}

	public void paste_from_clipboard(String text) {
		StringSelection stringSelection = new StringSelection(text);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			fail();
		}
		robot.delay(1000);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
		logSteps.execution_log("Pasting '" + text + "'");
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public void send_Enter_key() {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			fail();
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(1000);
	}

	@When("user refreshes current page")
	@Then("user refreshes current page")
	public void user_refresh_page() {
		getDriver().navigate().refresh();
	}

	// cigniti added
	public void sendKeys(String keys) throws AWTException {
		Robot robot = new Robot();
		for (char c : keys.toCharArray()) {
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
			if (KeyEvent.CHAR_UNDEFINED == keyCode) {
				throw new RuntimeException(
						"Key code not found for character '" + c + "'");
			}
			robot.keyPress(keyCode);
			robot.delay(100);
			robot.keyRelease(keyCode);
			robot.delay(100);
		}
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	// Description:setClipboardData setClipboardData throws Throwable the throwable
	public static void set_clipboard_data(String fileName) {
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(fileName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void upload_image_file(String fileLocation) throws AWTException {

		// Setting clipboard with file location
		set_clipboard_data(fileLocation);
		// native key strokes for CTRL, V and ENTER keys
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	// parse to remove starting and ending braces
	public String parseText(String test, String startsymbol, String endsymbol, String sourcechar, String targetchar) {

		if (test.startsWith(startsymbol) && (test.endsWith(endsymbol))) {
			test = test.substring(1, test.length() - 1);
		}
		test = test.replace(sourcechar, targetchar);
		return (test);

	}

	public int InStr(Object string1, Object string2) {
		return string1.toString().indexOf(string2.toString()) + 1;
	}

	public static void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(pageLoadCondition);
	}
}
