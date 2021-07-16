package ca.poc.djj.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import ca.poc.djj.pages.CreateSupplierCRMPage;
import ca.poc.djj.pages.HomePage;
import ca.poc.djj.utils.LogSteps;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class CreateSupplierCRMSteps extends CommonSteps {
	@Steps
	LogSteps logsteps;
	CreateSupplierCRMPage create_crm_page;

	@Then("Edit supplier account header")
	public void edit_supplier_header() {
		create_crm_page.edit_supplier_header.click();
	}

	@When("Navigate to account summary account header")
	public void account_summary_header() throws InterruptedException {
		Thread.sleep(10000);
		create_crm_page.account_summary_header.waitUntilVisible();
		create_crm_page.account_summary_header.waitUntilClickable();
		create_crm_page.account_summary_header.click();
	}

	@Then("Enter main details in supplier")
	public void main_details_supplier() {
		create_crm_page.company_name.type("Supplier" + System.currentTimeMillis());
		create_crm_page.supplier_type_select.selectByVisibleText("DJJ Affiliates");
		create_crm_page.branch_group_select.selectByVisibleText("MRS");
		create_crm_page.related_facility_select.selectByVisibleText("MRS-Gastonia");
		// create_crm_page.priority_select.wa
		create_crm_page.priority_select.selectByVisibleText("1. High");
		create_crm_page.source_select.selectByVisibleText("MyScrapInfo");
	}

	@Then("Enter address information")
	public void address_information_details_supplier() {
		create_crm_page.requested_street_select.type("East");
		create_crm_page.address_street_select.type("1122 Hometown");
		create_crm_page.requested_city_select.type("Nash");
		create_crm_page.requested_country_select.selectByVisibleText("US");
		create_crm_page.select_requested_state.selectByVisibleText("LA");
		create_crm_page.requested_zip_select.type("37011");
		// create_crm_page.req_longitude_field.sendKeys("233.17");
	}

	@Then("Check address information")
	public void check_address_information() throws InterruptedException {
		// actionsClick(create_crm_page.requested_pay_method_input);
		// ScrollTOElement(create_crm_page.longitude);
		create_crm_page.paymethod();
		// jsClick(create_crm_page.requested_pay_method_input);
		// ScrollTOElement(create_crm_page.requested_final_terms_select);
		create_crm_page.input_requested_check_payto.type("pay" + System.currentTimeMillis());
		create_crm_page.input_requested_check_name.type("name" + System.currentTimeMillis());
		create_crm_page.input_requested_check_address1.type("address" + System.currentTimeMillis());
		create_crm_page.input_requested_check_city.type("nashville" + System.currentTimeMillis());
		create_crm_page.select_requested_check_state.selectByVisibleText("LA");
		create_crm_page.input_requested_check_zip.type("37544");
		create_crm_page.requested_final_terms_select.selectByVisibleText("30-30 DAYS");
		create_crm_page.save_button.click();
		Thread.sleep(10000);
	}

	@When("convert to supplier")
	public void convert_to_supplier() throws InterruptedException {
		create_crm_page.button_convert_to_supplier.click();
		Thread.sleep(6000);
		create_crm_page.save_button.click();
	}

	@Then("user approve supplier and copy supplier id")
	public void approve_supplier() throws InterruptedException {
		create_crm_page.supplier_home_page.click();
		create_crm_page.saione_info.click();
		Thread.sleep(6000);
		create_crm_page.button_approve_supplier.click();
		Thread.sleep(11000);
		Serenity.setSessionVariable("Supplier").to(create_crm_page.text_supplier.getText());
		System.out.println("supplier---->" + Serenity.sessionVariableCalled("Material").toString());
	}

	@Then("create new primary contact")
	public void create_primary_contact() throws InterruptedException {
		Thread.sleep(6000);
		System.out.println("size----->"+create_crm_page.primary_contact_field().size());
		while((create_crm_page.primary_contact_field().size())==0) {
			create_crm_page.input_primary_contact.click();
			create_crm_page.input_new_contact.click(); 
			Thread.sleep(6000);
			create_crm_page.input_quick_contact_firstname.type("testqc"+System.currentTimeMillis()); 
			Thread.sleep(1000);
			create_crm_page.input_quick_contact_lastname.type("testlc"+System.currentTimeMillis()); 
			Thread.sleep(1000);
			create_crm_page.input_quick_contact_jobtitle.type("Accounts");
			Thread.sleep(1000);
			create_crm_page.input_quick_contact_mobile.type("9876543210");
			Thread.sleep(1000);
			create_crm_page.input_quick_contact_city.type("cincinatti");
			Thread.sleep(1000);
			create_crm_page.input_quick_contact_zip.type("37544");
			Thread.sleep(1000);
			create_crm_page.button_save_and_close.click(); 
			Thread.sleep(15000);
			System.out.println("size----->"+create_crm_page.primary_contact_field().size());
		}

		/*
		 * create_crm_page.input_primary_contact.type("test123"); Thread.sleep(1000);
		 * create_crm_page.input_primary_contact.sendKeys(Keys.chord(Keys.ARROW_DOWN));
		 * Thread.sleep(1000);
		 * create_crm_page.input_primary_contact.sendKeys(Keys.chord(Keys.ENTER));
		 * Thread.sleep(1000);
		 */
	}

	@Then("User save primary contact account")
	public void save_primary_contact() throws InterruptedException {
		create_crm_page.save_footer_button.click();
		Thread.sleep(3000);
	}

	@Then("validate supplier heading")
	public void validate_heading() {
		String head = create_crm_page.supplier_heading.getText();

	}
}
