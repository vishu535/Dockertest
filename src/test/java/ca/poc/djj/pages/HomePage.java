package ca.poc.djj.pages;

import java.util.List;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ca.poc.djj.steps.CommonSteps;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class HomePage extends PageObject {
	CommonSteps cs;
	public HomePage(WebDriver driver) {
		super(driver);
	}
	public WebElementFacade contract_form_field(String field) {
		return findBy("//label[text()='" + field + "']/following-sibling::input");
	}
	
	@FindBy(css = "[href='/scale-pricing']")
	public WebElementFacade scale_pricing_head;
	
	@FindBy(css = "[href='/price-list']")
	public WebElementFacade price_list_head;
	
	@FindBy(css = "iframe#AppLandingPage")
	public WebElementFacade crm_iframe;
	
	@FindBy(xpath = "//div[text()='Recycling CRM']")
	public WebElementFacade recycling_crm_section;
	
	@FindBy(xpath = "//span[text()='Accounts (All Views)']")
	public WebElementFacade all_accounts_view_activity;
	
	@FindBy(css = ".ChevronDownMed-symbol  ")
	public WebElementFacade dropdown_arrow;
	
	@FindBy(xpath = "//*[contains(text(),'All Accounts')]")
	public WebElementFacade all_accounts;

	@FindBy(xpath = "//span[text()='New']")
	public WebElementFacade new_supplier_button;
	
	@FindBy(xpath = "//label[text()='Company:']/.. //select")
	public WebElementFacade select_company;
	
	@FindBy(xpath = "//label[text()='Branch:']/.. //select")
	public WebElementFacade select_branch;
	
	@FindBy(xpath = "//input[@value='Search...']")
	public WebElementFacade search_button;
	
	@FindBy(xpath = "(//th[text()='Material Description'])[1]/../../tr/td[2]")
	public WebElementFacade table_md;
	
	@FindBy(xpath = "//span[text()='Account']/following-sibling::div/div/div/div/span[1]")
	public WebElementFacade text_account_supplier_value;
	
	@FindBy(xpath = "//*[text()='Supplier Home Page']")
	public WebElementFacade supplier_home_drop;
	
	public List<WebElementFacade> table_list(){ 
		return findAll("(//th[text()='Material Description'])[1]/../../tr/td[2]");
	}
}
