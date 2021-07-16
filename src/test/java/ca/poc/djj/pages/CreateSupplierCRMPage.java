package ca.poc.djj.pages;

import java.util.List;

import org.openqa.selenium.Keys;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ca.poc.djj.steps.CommonSteps;
import ca.poc.djj.utils.LogSteps;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class CreateSupplierCRMPage extends PageObject {
	public CreateSupplierCRMPage(WebDriver driver) {
		super(driver);
	}
	
	@Steps
	LogSteps logsteps;
	
	public WebElementFacade contract_form_field(String field) {
		return findBy("//label[text()='" + field + "']/following-sibling::input");
	}
	
	@FindBy(xpath = "//li[text()='Edit Supplier']")
	public WebElementFacade edit_supplier_header;
	
	@FindBy(xpath = "//li[text()='Account Summary']")
	public WebElementFacade account_summary_header;
	
	@FindBy(css = "[aria-label='Company Name']")
	public WebElementFacade company_name;
	
	@FindBy(css = "[aria-label='Supplier Type']")
	public WebElementFacade supplier_type_select;
	
	@FindBy(css = "[aria-label='Branch Group']")
	public WebElementFacade branch_group_select;
	
	@FindBy(css = "[aria-label='Related Facility']")
	public WebElementFacade related_facility_select;
	
	@FindBy(css = "[aria-label='Priority']")
	public WebElementFacade priority_select;
	
	@FindBy(css = "[aria-label='Source']")
	public WebElementFacade source_select;
//Address Information	
	@FindBy(css = "[aria-label='Requested Street 1']")
	public WebElementFacade requested_street_select;
	
	@FindBy(css = "[aria-label='Address 1: Street 2']")
	public WebElementFacade address_street_select;
	
	@FindBy(css = "[aria-label='Requested City']")
	public WebElementFacade requested_city_select;
	
	@FindBy(xpath = "//label[text()='Requested State']/../../../following-sibling::div //select")
	public WebElementFacade select_requested_state;
	
	@FindBy(css = "[aria-label='Requested Country']")
	public WebElementFacade requested_country_select;
	
	@FindBy(css = "[aria-label='Requested State']")
	public WebElementFacade requested_state_select;
	
	@FindBy(css = "[aria-label='Requested Zip Code']")
	public WebElementFacade requested_zip_select;
//Check address information
	@FindBy(xpath = "//label[text()='Requested Pay Method']/../../../following-sibling::div //input")
	public WebElementFacade requested_pay_method_input;
	
	@FindBy(css = "#recy_requestedpaymethod_ledit")
	public WebElementFacade requested_pay_method_d1;
	
	@FindBy(css = "input[aria-label='Check']")
	public WebElementFacade check_checkbox;
	
	@FindBy(css = "label[title='Check']")
	public WebElementFacade check_select;
	
	@FindBy(css = "[aria-label='Requested Final Terms']")
	public WebElementFacade requested_final_terms_select;
	
	@FindBy(css = "[aria-label='Toggle menu']")
	public WebElementFacade toggle_menu;
	
	@FindBy(xpath = "//img[contains(@src,'Save.svg')]")
	public WebElementFacade save_button;
	
	@FindBy(xpath = "//span[text()='Save']")
	public WebElementFacade save_footer_button;
	
	@FindBy(xpath = "//span[text()='Supplier Home Page']")
	public WebElementFacade supplier_home_page;
	
	@FindBy(xpath = "//*[text()='SAIOne - Supplier Info']")
	public WebElementFacade saione_info;
	
	@FindBy(xpath = "//span[text()='Approve Supplier']")
	public WebElementFacade button_approve_supplier;
	
	@FindBy(xpath = "//div[text()='Supplier']/../div/div")
	public WebElementFacade text_supplier;
	
	@FindBy(css = "[aria-label='Longitude']")
	public WebElement longitude;
	
	@FindBy(css = "[aria-label='Requested Longitude']")
	public WebElementFacade req_longitude_field;
	
	@FindBy(css = "[aria-label='Address 1: Street 1']")
	public WebElementFacade address_street_field;
	
	@FindBy(css = "#formHeaderTitle_2")
	public WebElementFacade supplier_heading;
	
	@FindBy(xpath = "//label[text()='Requested Check Pay To']/../../../following-sibling::div //input")
	public WebElementFacade input_requested_check_payto;
	
	@FindBy(xpath = "//label[text()='Requested Check Name']/../../../following-sibling::div //input")
	public WebElementFacade input_requested_check_name;
	
	@FindBy(xpath = "//label[text()='Requested Check Address 1']/../../../following-sibling::div //input")
	public WebElementFacade input_requested_check_address1;
	
	@FindBy(xpath = "//label[text()='Requested Check City']/../../../following-sibling::div //input")
	public WebElementFacade input_requested_check_city;
	
	@FindBy(xpath = "//label[text()='Requested Check State']/../../../following-sibling::div //select")
	public WebElementFacade select_requested_check_state;
	
	@FindBy(xpath = "//label[text()='Requested Check Zip Code']/../../../following-sibling::div //input")
	public WebElementFacade input_requested_check_zip;
	
	@FindBy(xpath = "//label[text()='Primary Contact']/../../../following-sibling::div //input")
	public WebElementFacade input_primary_contact;
	
	public List<WebElementFacade> primary_contact_field(){ 
		return findAll("//label[text()='Primary Contact']/../../../following-sibling::div //input");
	}
	
	@FindBy(xpath = "//*[text()='New Contact']")
	public WebElementFacade input_new_contact;
	
	@FindBy(xpath = "//label[text()='Last Name']/../../../following-sibling::div //input")
	public WebElementFacade input_quick_contact_lastname;
	
	@FindBy(xpath = "//label[text()='First Name']/../../../following-sibling::div //input")
	public WebElementFacade input_quick_contact_firstname;
	
	@FindBy(xpath = "//label[text()='Job Title']/../../../following-sibling::div //input")
	public WebElementFacade input_quick_contact_jobtitle;
	
	@FindBy(xpath = "//label[text()='Mobile Phone']/../../../following-sibling::div //input")
	public WebElementFacade input_quick_contact_mobile;
	
	@FindBy(xpath = "//label[text()='City']/../../../following-sibling::div //input")
	public WebElementFacade input_quick_contact_city;
	
	@FindBy(xpath = "//label[text()='State/Province']/../../../following-sibling::div //input")
	public WebElementFacade input_quick_state;
	
	@FindBy(xpath = "//label[text()='ZIP/Postal Code']/../../../following-sibling::div //input")
	public WebElementFacade input_quick_contact_zip;
	
	@FindBy(xpath = "//span[text()='Convert to Supplier']")
	public WebElementFacade button_convert_to_supplier;
	
	@FindBy(xpath = "//span[text()='Save and Close']")
	public WebElementFacade button_save_and_close;
	
	@Step
	public void paymethod() throws InterruptedException {
		Thread.sleep(1000);
		requested_zip_select.sendKeys(Keys.chord(Keys.PAGE_DOWN));
		Thread.sleep(1000);
		requested_zip_select.sendKeys(Keys.chord(Keys.PAGE_DOWN));
		Thread.sleep(1000);
		requested_zip_select.sendKeys(Keys.chord(Keys.PAGE_DOWN));
		Thread.sleep(1000);
		requested_zip_select.sendKeys(Keys.chord(Keys.PAGE_DOWN));
		Thread.sleep(1000);
		longitude.click();
		Thread.sleep(2000);
		longitude.sendKeys(Keys.chord(Keys.TAB));
		Thread.sleep(1000);
		requested_pay_method_input.click();
		Thread.sleep(1000);
		requested_pay_method_input.click();
		Thread.sleep(2000);
		requested_pay_method_input.sendKeys("Check");
		Thread.sleep(2000);
		check_select.click();
		check_select.sendKeys(Keys.chord(Keys.TAB));
		Thread.sleep(2000);
	}
			
}
