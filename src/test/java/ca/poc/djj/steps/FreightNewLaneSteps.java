package ca.poc.djj.steps;


import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import ca.poc.djj.pages.FreightNewLanePage;
import ca.poc.djj.pages.ScalePricingPage;
import ca.poc.djj.utils.LogSteps;
import net.thucydides.core.annotations.Steps;

public class FreightNewLaneSteps extends CommonSteps {
	@Steps
	LogSteps logsteps;
	FreightNewLanePage rf_page;	
	
	@When("user landed to recycling freight")
	public void validate_recycling_freight_page() {
		logsteps.execution_log("Landed on "+rf_page.rf_heading.getText());
	}
	
	@When("user add first stop")
	public void add_first_stop() throws InterruptedException {
		rf_page.addstop_button.click();
		logsteps.execution_log("first add stop button clicked");
		rf_page.search_supplier_select.click();
		Thread.sleep(2000);
		rf_page.search_supplier_select.sendKeys("133574");
		Thread.sleep(5000);
		rf_page.dropdown_value.click();
		logsteps.execution_log("selected supplier 133574");
		Thread.sleep(2000);
		rf_page.new_stop_ok.click();
		logsteps.execution_log("new stop is created");
	}
	
	@When("user add second stop")
	public void add_second_stop() throws InterruptedException {
		rf_page.add_stop_second.click();
		logsteps.execution_log("second stop button clicked");
		Thread.sleep(2000);
		jsClick(rf_page.new_stop_yard);
		logsteps.execution_log("selected yard");
		rf_page.yard_select.selectByVisibleText("MRS-Monroe");
		logsteps.execution_log("selected yard MRS-Monroe");
		rf_page.new_stop_ok.click();
		logsteps.execution_log("second stop is created");
	}
	

	@When("user add second stop invalid locator")
	public void add_second_stop_invalidlocator() throws InterruptedException {
		rf_page.add_stop_second.click();
		logsteps.execution_log("second stop button clicked");
		Thread.sleep(2000);
		jsClick(rf_page.new_stop_yard_inv);
		logsteps.execution_log("selected yard");
		rf_page.yard_select.selectByVisibleText("MRS-Monroe");
		logsteps.execution_log("selected yard MRS-Monroe");
		rf_page.new_stop_ok.click();
		logsteps.execution_log("second stop is created");
	}
	
	@Then("user add movement charge for yard to supplier")
	public void add_movement_charge() throws InterruptedException {
		Thread.sleep(4000);
		rf_page.carrier_dropdown.click();
		Thread.sleep(2000);
		rf_page.carrier_dropdown.sendKeys("100475");
		Thread.sleep(5000);
		logsteps.execution_log("selected acrrier 100475");
		boolean rate_type_status = rf_page.rate_type_select.isDisabled();
		System.out.println("------"+rate_type_status);
		rf_page.type_dropdown("100475").click();
		rf_page.rate_field.sendKeys("80");
		logsteps.execution_log("rate field id set to 80");
		rf_page.est_qty_field.sendKeys("4");
		logsteps.execution_log("qty is set to 4");
		rf_page.movement_charge_ok.click();
		logsteps.execution_log("movement charge is created");
		Thread.sleep(5000);
	}
	
	@Then("user add stop charge for yard to supplier")
	public void add_stop_charge() throws InterruptedException {
		Thread.sleep(4000);
		rf_page.stop_charge_carrier_dropdown.click();
		Thread.sleep(2000);
		rf_page.stop_charge_carrier_dropdown.sendKeys("100471");
		Thread.sleep(5000);
		logsteps.execution_log("selected carrier 100471");
		boolean rate_type_status = rf_page.stop_charge_rate_type_select.isDisabled();
		System.out.println("------"+rate_type_status);
		rf_page.type_dropdown("100471").click();
		rf_page.stop_charge_rate_field.sendKeys("60");
		logsteps.execution_log("rate field id set to 80");
		rf_page.stop_charge_est_qty_field.sendKeys("3");
		logsteps.execution_log("qty is set to 4");
		rf_page.stop_charge_ok.click();
		Thread.sleep(5000);
		logsteps.execution_log("Created lane");
	}
	
	@Then("user save lane")
	public void save_lane() throws InterruptedException {
		rf_page.save_button.click();
		Thread.sleep(2000);
	}
	
	@Then("user add charges to supplier")
	public void add_charges_supplier() {
		rf_page.supplier_add_charge.click();
		logsteps.execution_log("initiated supplier add charge");
	}
}
