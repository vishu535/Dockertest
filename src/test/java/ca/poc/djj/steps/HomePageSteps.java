package ca.poc.djj.steps;


import java.util.List;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import ca.poc.djj.pages.HomePage;
import ca.poc.djj.utils.LogSteps;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Steps;

public class HomePageSteps extends CommonSteps {
	@Steps
	LogSteps logsteps;
	HomePage h_page;
	
	@When("user navigates to scale pricing")
	public void navigates_to_scale_pricing() {
		h_page.scale_pricing_head.click();
		logsteps.execution_log("navigated to scale pricing");
	}
	
	@Then("user navigates to price list")
	public void navigates_to_price_list() {
		jsClick(h_page.price_list_head);
		logsteps.execution_log("navigated to price list");
	}
	
	@When("User navigates to recycling CRM section")
	public  void navigates_to_recycling_crm_section() throws InterruptedException {
	//	h_page.crm_iframe.waitUntilVisible();
		
		Thread.sleep(5000);
		h_page.getDriver().navigate().refresh();
		Thread.sleep(10000);
		System.out.println("refresh!!!!!");
	//	h_page.getDriver().navigate().refresh();
	//	Thread.sleep(40000);
		System.out.println("refresh!!!!!2");
		Thread.sleep(60000);
		h_page.crm_iframe.waitUntilVisible();
		h_page.getDriver().switchTo().frame(h_page.crm_iframe);
		logsteps.execution_log("Handled frame ");
		h_page.recycling_crm_section.click();
		h_page.getDriver().switchTo().defaultContent();
		logsteps.execution_log("navigated to CRM section ");
	}
	
	@When("User directly navigates to accounts")
	public void navigates_account() {
		h_page.getDriver().get("https://djjvikingqa.crm.dynamics.com/main.aspx?appid=dd9233be-5e39-eb11-a813-000d3a5a17e3&pagetype=entityrecord&etn=account");
		logsteps.execution_log("account opened");
	}
	
	@When("User selects accounts view activity")
	public void select_accounts_view_activity() {
		h_page.all_accounts_view_activity.click();
		logsteps.execution_log("selected All accounts oview activity");
	}
	
	@When("user selects all accounts from view section")
	public void all_accounts_view_section() throws InterruptedException {
		h_page.dropdown_arrow.waitUntilVisible();
		h_page.dropdown_arrow.waitUntilClickable();
		Thread.sleep(1500);
		h_page.dropdown_arrow.click();
		Thread.sleep(1500);
		h_page.all_accounts.click();
		Thread.sleep(1500);
		logsteps.execution_log("selected All accounts option from view section");
	}
	
	@Then("user select company branch and search prices from price list")
	public void select_price_pricelist() throws InterruptedException {
		h_page.select_company.selectByVisibleText("MRS");
		logsteps.execution_log("selected company");
		Thread.sleep(3000);
		h_page.select_branch.selectByVisibleText("MRS-Gastonia");
		logsteps.execution_log("selected branch");
		h_page.search_button.click();
		logsteps.execution_log("search price list");
		Thread.sleep(3000);
	}
	
	@When("Create new Supplier account from supplier home page")
	public void new_supplier_button() throws InterruptedException {
		Thread.sleep(5000);
		h_page.new_supplier_button.waitUntilClickable();
		jsClick(h_page.new_supplier_button);
		logsteps.execution_log("user started creating new supplier");
		Thread.sleep(3000);
		h_page.text_account_supplier_value.waitUntilVisible();
		Thread.sleep(3000);
		System.err.println(h_page.text_account_supplier_value.getText());
		if(h_page.text_account_supplier_value.getText().contains("SAIOne - Supplier Info")) {
			h_page.text_account_supplier_value.click();
			h_page.supplier_home_drop.click();
			Thread.sleep(5000);
		}
	}
	
	@Then("validate material description in pricing")
	public void validate_material() {
		
		List<WebElementFacade> li = h_page.table_list();
	for(WebElementFacade we : li) {	
		
		if(Serenity.sessionVariableCalled("Material").toString().equals(we.getText())){
			logsteps.execution_log("material present in price table");
		}
	}
	}
}
