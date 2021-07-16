package ca.poc.djj.steps;


import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import ca.poc.djj.pages.HomePage;
import ca.poc.djj.pages.LoginPage;
import ca.poc.djj.utils.LogSteps;
import net.thucydides.core.ThucydidesSystemProperty;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class LoginPageSteps extends CommonSteps {
	@Steps
	LogSteps logsteps;
	LoginPage login_page;
	
	@Given("login to DJJ Vikings pricing app")
	public void login_to_pricing_app() throws Exception {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String baseUrl = variables.getProperty(ThucydidesSystemProperty.WEBDRIVER_BASE_URL);
		login_page.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		login_page.getDriver().manage().deleteAllCookies();
		login_page.getDriver().get(baseUrl);
		login_page.open();
		login_page.getDriver().manage().window().maximize();
		login_page.username.type(readjson("username"));
		if(login_page.username.isPresent()) {
			login_page.username.type(readjson("username"));
			logsteps.execution_log("User enters username");
			login_page.next_button.click();
			logsteps.execution_log("Click on next button successful");
			login_page.password.waitUntilPresent();
			Thread.sleep(2000);
			login_page.password.sendKeys(readjson("password"));
			logsteps.execution_log("User enters password");
			login_page.signin_button.click();
			login_page.yes_button.click();
		}
	}
	
	@Given("login to DJJ Vikings freight app")
	public void login_to_freight_app() throws Exception {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		String baseUrl = variables.getProperty("webdriver.freight.url");
		login_page.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		login_page.getDriver().manage().deleteAllCookies();
		login_page.getDriver().get(baseUrl);
//		login_page.open();
		login_page.getDriver().manage().window().maximize();
		login_page.username.type(readjson("username"));
		if(login_page.username.isPresent()) {
			login_page.username.type(readjson("username"));
			logsteps.execution_log("User enters username");
			login_page.next_button.click();
			logsteps.execution_log("Click on next button successful");
			login_page.password.waitUntilPresent();
			login_page.password.sendKeys(readjson("password"));
			logsteps.execution_log("User enters password");
			login_page.signin_button.click();
			login_page.yes_button.click();
		}
	}	

	@Given("login to DJJ Vikings CRM application")
	public void login_to_crm_app() throws Exception {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
//		String baseUrl = "https://djjviking.crm.dynamics.com/";
		login_page.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		login_page.getDriver().manage().deleteAllCookies();
		login_page.getDriver().get(variables.getProperty("webdriver.crm.url"));
	//	login_page.open();
		login_page.getDriver().manage().window().maximize();
		login_page.username.type(readjson("username"));
		if(login_page.username.isPresent()) {
			login_page.username.type(readjson("username"));
			logsteps.execution_log("User enters username");
			login_page.next_button.click();
			logsteps.execution_log("Click on next button successful");
			login_page.password.waitUntilPresent();
			login_page.password.sendKeys(readjson("password"));
			logsteps.execution_log("User enters password");
			login_page.signin_button.click();
			login_page.yes_button.click();
		}
	}
	
	@Given("login to DJJ Vikings")
	public void login_to_application() {
		
	}
	@Then("user logout")
	public void verify_logout() {
		
	}
}
