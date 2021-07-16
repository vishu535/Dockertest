package ca.poc.djj.pages;

import java.util.List;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ca.poc.djj.steps.CommonSteps;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ScalePricingPage extends PageObject {
	CommonSteps cs;
	public ScalePricingPage(WebDriver driver) {
		super(driver);
	}
	public WebElementFacade contract_form_field(String field) {
		return findBy("//label[text()='" + field + "']/following-sibling::input");
	}

	@FindBy(xpath = "//label[text()='FE']/preceding-sibling::input")
	public WebElementFacade invent_type_radio_fe;
	
	@FindBy(xpath = "//label[text()='NF']/preceding-sibling::input")
	public WebElementFacade invent_type_radio_nf;
	
	@FindBy(xpath = "//label[text()='Date:']/following-sibling::input")
	public WebElementFacade date_select;
	
	@FindBy(xpath = "//label[text()='Company:']/.. //select")
	public WebElementFacade company_select;
	
	@FindBy(css = "[id^='multiSelectDropdown']")
	public WebElementFacade branches_select;
	
	@FindBy(xpath = "//label[text()='MRS-Gastonia']/preceding-sibling::input")
	public WebElementFacade gast_branch_check;
	
	@FindBy(xpath = "//label[text()='Select All']/preceding-sibling::input")
	public WebElementFacade branches_select_all_checkbox;
	
	@FindBy(xpath = "//label[text()='Commodities:']/following-sibling::div //ng-select[2]/div/span[1]")
	public WebElementFacade comm_closeall;
	
	@FindBy(xpath = "//label[text()='Commodities:']/following-sibling::div //ng-select[2]/div/span[2]")
	public WebElementFacade commod_arrow;
	
	@FindBy(xpath = "//button[text()='Select all']")
	public WebElementFacade commod_select_all;
	
	@FindBy(xpath = "//label[text()='Level:']/following-sibling::div //select")
	public WebElementFacade level_select;
	
	@FindBy(css = "input[value='Search...']")
	public WebElementFacade search_button;
	
	@FindBy(css = "input[value='Add New']")
	public WebElementFacade addnew_button;
	
	@FindBy(xpath = "//label[text()='Branch:']/following-sibling::div/select")
	public WebElementFacade addprice_branch_select;
	
	@FindBy(xpath = "//label[text()='Effective Date:']/following-sibling::div/input")
	public WebElementFacade addprice_eff_date;
	
	@FindBy(xpath = "//label[text()='Expiration Date:']/following-sibling::div/input")
	public WebElementFacade addprice_exp_date;
	
	@FindBy(xpath = "(//td[text()=' $']/input)[1]")
	public WebElementFacade first_price_field;
	
	@FindBy(xpath = "(//td[text()=' $'])[1]/preceding-sibling::td/span")
	public WebElementFacade first_price_material;
	
	@FindBy(css = "#backdateOk")
	public WebElementFacade ok_button;
	
	
	@FindBy(css = "#variancePercent")
	public WebElementFacade varience_percent_input;
	
	@FindBy(css = "#varianceAmt")
	public WebElementFacade varience_amount_input;
	
	@FindBy(css = "#varianceOk")
	public WebElementFacade varience_ok;
	
	@FindBy(css = "[value=Save]")
	public WebElementFacade save_button;
	
	public List<WebElementFacade> material_rows(){ 
		return findAll("table.price-table tr");
	}
	
	public List<WebElement> row_wise(int no) {
		return findBy("table.price-table tr:nth-child("+no+") td");
	}
	
	public WebElementFacade material_table(int x, int y) {
		return findBy("table.price-table tbody tr:nth-child("+x+") td:nth-child("+y+")");
	}
	//i[style='color: red;']
	//span[text()='Prices successfully saved']
}
