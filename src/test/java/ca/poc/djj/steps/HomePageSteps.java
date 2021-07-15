package ca.poc.djj.steps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import ca.poc.djj.pages.HomePage;
import ca.poc.djj.utils.LogSteps;
import ca.poc.djj.utils.WaitUtil;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.SerenitySystemProperties;
import net.thucydides.core.ThucydidesSystemProperty;
import net.thucydides.core.annotations.Steps;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class HomePageSteps extends CommonSteps {
	@Steps
	LogSteps logsteps;
	HomePage h_page;
	
	
	public String contractID = "";
	public String paymentID = "";
	public String VendorLocation = "";
	public String origincity = "";
	public String destinationcity = "";
	public String shipmentID = "";
	public String genShippingNum = "";
	
	@Given("user login to application with valid credentials")
	public void user_login_to() throws Exception {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String baseUrl = variables.getProperty(ThucydidesSystemProperty.WEBDRIVER_BASE_URL);
		String un = variables.getProperty(ThucydidesSystemProperty.SERENITY_PROXY_USER);
		String pwd = variables.getProperty(ThucydidesSystemProperty.SERENITY_PROXY_PASSWORD);
		System.out.println("------------"+un+pwd);
		h_page.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		h_page.getDriver().get(baseUrl);
		h_page.open();
	}
	@Given("user login to application with valid credentials as: $Username and $Password")
	public void user_login_to_Dynamics(String un, String pw) throws Exception {
	//	try {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		un = variables.getProperty(ThucydidesSystemProperty.SERENITY_PROXY_USER);
		String pwd = variables.getProperty(ThucydidesSystemProperty.SERENITY_PROXY_PASSWORD);
			System.out.println("Login method started =====> " + (java.time.LocalDateTime.now()));
			logsteps.execution_log(
					"Creating object for SystemEnvironmentVariables =====>" + (java.time.LocalDateTime.now()));
			String baseUrl = variables.getProperty(ThucydidesSystemProperty.WEBDRIVER_BASE_URL);
		//	System.out.println("------"+SerenitySystemProperties.getProperties().getValue(null, "USER_NAME"));
			logsteps.execution_log(
					"Reading baseUrl from Serenity.Properites file =====>" + (java.time.LocalDateTime.now()));
			h_page.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			h_page.getDriver().manage().deleteAllCookies();
			logsteps.execution_log("Time before invoking browser =====> " + (java.time.LocalDateTime.now()));
			h_page.getDriver().get(baseUrl);
			h_page.open();
			logsteps.execution_log("Time after opening browser =====> " + (java.time.LocalDateTime.now()));
			h_page.getDriver().manage().window().maximize();
			if(h_page.TXT_Username.isPresent()) {
				h_page.TXT_Username.type(un);
				logsteps.execution_log("User enters username");
				h_page.Button_Next.click();
				logsteps.execution_log("Click on next button successful");
				h_page.TXT_Password.waitUntilPresent();
				h_page.TXT_Password.sendKeys(pwd);
				logsteps.execution_log("User enters password");
				h_page.btn_signIn.click();
				h_page.btn_Yes.click();
				logsteps.execution_log("Time before login click " + (java.time.LocalDateTime.now()));
			}
			/*
			 * } catch (Exception e) { // logsteps.debug_log("Login Failed");
			 * Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			 * Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe"); throw new
			 * Exception("Exception occured due to Login failure");
			 
		}*/

	}
	
	@Given("Test login get url")
	public void get_url_dummy_for_test() {
		
		logsteps.execution_log("get url");
		
	}
	@When("Test enter detials")
	public void condition_dummy_for_test() {
		
		
		logsteps.execution_log("test log in condition");
	}
	@Then("Test result validation")
	public void result_for_test() {
		logsteps.execution_log("test log in result");
	}
	@Then("user verifies login is successful")
	public void user_verify_login() throws Exception {
//		untilPageLoadComplete(h_page.btn_pane, h_page, 40000);
		h_page.btn_pane.waitUntilVisible();
		h_page.btn_pane.isDisplayed();
	}
	
	@When("user navigates to contract entry in module menu")
	public void user_navigate_contact_entry() throws InterruptedException {
		h_page.workspace_modules_icon();
		h_page.navigate_to_modules("DJJ Brokerage");
		logsteps.execution_log("Navigated to DJJ Brokerage");
		h_page.navigate_to_workspace("Purchase contract entry");
		logsteps.execution_log("Navigated to workspace - Purchase contract entry");
		
	}

	
	@When("user navigates to General journals in module menu")
	public void user_navigate_general_journals() throws InterruptedException {
		h_page.workspace_modules_icon();
		h_page.navigate_to_modules("General ledger");
		logsteps.execution_log("General ledger");
		h_page.navigate_to_workspace("General journals");
		logsteps.execution_log("Navigated to workspace - General journals");
	
	}
	
	@When("user navigates to invoice shipments")
	public void user_navigates_InvoiceShipments() throws InterruptedException {
		h_page.workspace_modules_icon();
		h_page.navigate_to_modules("DJJ Brokerage");
		logsteps.execution_log("Navigated to DJJ Brokerage");
		h_page.navigate_to_workspace("Invoice shipments");
		logsteps.execution_log("Navigated to workspace - Invoice shipments");

	}
	   
	@Then("user search batch jobs with: $Username")
	public void user_search_batchJobs(String un) throws InterruptedException {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		un = variables.getProperty(ThucydidesSystemProperty.SERENITY_PROXY_USER);
		h_page.search_batch_job();
		h_page.batch_jobs_filter_by_username(un);
		Thread.sleep(2000);
	}
	
	@When("user navigates to All purchase orders from Accounts payable")
	public void user_navigates_AccountPayable() throws InterruptedException {
		h_page.workspace_modules_icon();
		h_page.navigate_to_modules("Accounts payable");
		logsteps.execution_log("Navigated to Accounts payable");
		h_page.navigate_to_workspace("All purchase orders");
		logsteps.execution_log("Navigated to workspace - All purchase orders");
	
	}
	@When("user navigates to All sales orders from Accounts receivable")
	public void user_navigates_AccountReceivable() throws InterruptedException {
		h_page.workspace_modules_icon();
		h_page.navigate_to_modules("Accounts receivable");
		logsteps.execution_log("Navigated to Accounts receivable");
		h_page.navigate_to_workspace("All sales orders");
		logsteps.execution_log("Navigated to workspace - All sales orders");
	
	}
	
	@When("user navigates to Vendor payment journal from Accounts payable")
	public void user_nagivates_VendorPaymentJournal() throws InterruptedException {
		h_page.workspace_modules_icon();
		h_page.navigate_to_modules("Accounts payable");
		logsteps.execution_log("Navigated to Accounts payable");
		h_page.navigate_to_workspace("Vendor payment journal");
	
	}
	
	@When("user navigates to Customer payment journal from Accounts receivable")
	public void user_nagivates_CustomerPaymentJournal() throws InterruptedException {
		h_page.workspace_modules_icon();
		h_page.navigate_to_modules("Accounts receivable");
		logsteps.execution_log("Navigated to Accounts receivable");
		h_page.navigate_to_workspace("Customer payment journal");
		logsteps.execution_log("Navigated to workspace - Customer payment journal");

	}
	
	@When("user navigates to Freight bill workspace")
	public void user_navigates_freightBill_workspace() throws InterruptedException {
		h_page.workspace_modules_icon();
		h_page.navigate_to_modules("DJJ Brokerage");
		logsteps.execution_log("Navigated to DJJ Brokerage");
		h_page.navigate_to_workspace("Freight bill workspace");
		logsteps.execution_log("Navigated to - Freight bill workspace");
	
	}
	
	@When("user navigates to shipment workspace in module menu")
	public void user_navigate_shipment_workspace() throws InterruptedException {
		h_page.workspace_modules_icon();
		h_page.navigate_to_modules("DJJ Brokerage");
		logsteps.execution_log("Navigated to DJJ Brokerage");
		h_page.navigate_to_workspace("Shipment workspace");
		logsteps.execution_log("Navigated to workspace - shipment workspace");
	
	}
	
	@When("user navigates to Freight bill matching")
	public void user_navigates_freightBill_matching() throws InterruptedException {
		h_page.workspace_modules_icon();
		h_page.navigate_to_modules("DJJ Brokerage");
		logsteps.execution_log("Navigated to DJJ Brokerage");
		h_page.navigate_to_workspace("Freight bill matching");
		logsteps.execution_log("Navigated to workspace - Freight bill matching");
		
		h_page.run_in_the_background();
		h_page.shipment_batch_processing_toggle();
	//	csp.ok_button.waitUntilClickable();
	}
	
	@When("test should pick only from desired row: $serialno")
	public void test_row(String no) {
		System.out.println("hello---------------------------"+no);
	}
	@When("test should pick only from desired row")
	public void test_row() {
		System.out.println("hello---------------------------");
	}
	@Then("user Logout")
	public void user_logout() {
		System.out.println("logout---------------------------");
	}
	
	
}
