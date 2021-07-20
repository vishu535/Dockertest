package ca.poc.djj.pages;

import java.util.List;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ca.poc.djj.steps.CommonSteps;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class FreightNewLanePage extends PageObject {
	CommonSteps cs;
	public FreightNewLanePage(WebDriver driver) {
		super(driver);
	}
	public WebElementFacade contract_form_field(String field) {
		return findBy("//label[text()='" + field + "']/following-sibling::input");
	}
	
	@FindBy(css = "[href='/home']")
	public WebElementFacade rf_heading;
	
	@FindBy(css = "[value='Add Stop']")
	public WebElementFacade addstop_button;
	
	@FindBy(xpath = "//label[text()='Supplier']/../input")
	public WebElementFacade new_stop_supplier;
	
	@FindBy(xpath = "//label[text()='Yard']/../input")
	public WebElementFacade new_stop_yard;
	
	@FindBy(xpath = "//label[text()='Yard43465345']/../input")
	public WebElementFacade new_stop_yard_inv;
	
	@FindBy(xpath = "//label[text()='Consumer']/../input")
	public WebElementFacade new_stop_consumer;
	
	@FindBy(xpath = "(//div[text()='Search...']/following-sibling::div/input)[1]")
	public WebElementFacade search_supplier_select;		
	
	@FindBy(xpath = "//div[text()=' 133574 - Central Recycling LLC ']")
	public WebElementFacade dropdown_value;
	
	public WebElementFacade type_dropdown(String field) {
		return findBy("//div[contains(text(),'" + field + "')]");
	}
	
	@FindBy(xpath = "//div[contains(text(),'133586')]")
	public WebElementFacade dd_value_second_stop;
	
	@FindBy(xpath = "(//button[text()='Ok'])[1]")
	public WebElementFacade new_stop_ok;
	
	@FindBy(xpath = "(//button[text()='Ok'])[2]")
	public WebElementFacade movement_charge_ok;
	
	@FindBy(xpath = "(//button[text()='Ok'])[3]")
	public WebElementFacade stop_charge_ok;
	
	@FindBy(xpath = "//input[@value='Save']/preceding-sibling::input[1]")
	public WebElementFacade add_stop_second;
	
	@FindBy(xpath = "//label[text()='Yard']/following-sibling::select")
	public WebElementFacade yard_select;
	
	@FindBy(xpath = "//label[text()='Rate Type']/following-sibling::select")
	public WebElementFacade rate_type_select;
	
//	@FindBy(xpath = "//label[text()='Carrier']/following-sibling::customer-dropdown //div[text()='Search...']")
	@FindBy(xpath = "(//div[text()='Search...'])[3]/.. //input")
	public WebElementFacade carrier_dropdown;
	
	@FindBy(xpath = "(//div[text()='Search...'])[4]/.. //input")
	public WebElementFacade stop_charge_carrier_dropdown;
	
	@FindBy(xpath = "(//label[text()='Rate Type']/following-sibling::select)[2]")
	public WebElementFacade stop_charge_rate_type_select;

	@FindBy(xpath = "//label[text()='Est Qty']/following-sibling::input")
	public WebElementFacade est_qty_field;
	
	@FindBy(xpath = "(//label[text()='Est Qty']/following-sibling::input)[2]")
	public WebElementFacade stop_charge_est_qty_field;
	
	@FindBy(xpath = "(//input[@step='.01'])[1]")
	public WebElementFacade rate_field;
	
	@FindBy(xpath = "(//input[@step='.01'])[4]")
	public WebElementFacade stop_charge_rate_field;
	
	@FindBy(xpath = "(//span[text()='Add Charge'])[3]")
	public WebElementFacade supplier_add_charge;
	
	@FindBy(xpath = "//input[@value='Save']")
	public WebElementFacade save_button;
}
