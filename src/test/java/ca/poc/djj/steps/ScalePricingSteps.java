package ca.poc.djj.steps;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.Keys;

import ca.poc.djj.pages.ScalePricingPage;
import ca.poc.djj.utils.LogSteps;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Steps;

public class ScalePricingSteps extends CommonSteps {
	@Steps
	LogSteps logsteps;
	ScalePricingPage spricing_page;
	public String material = "";
	
	@When("user enters default data to retreive prices")
	public void enter_default_data() throws InterruptedException{
		spricing_page.invent_type_radio_fe.click();
//		spricing_page.date_select.clear();
//		spricing_page.date_select.sendKeys("05/06/2021");
		spricing_page.company_select.selectByVisibleText("MRS");
	//	spricing_page.company_select.get
	//	spricing_page.branches_select.click();
		if(!spricing_page.branches_select.getText().contains("Select")) {
			spricing_page.branches_select.click();
			spricing_page.branches_select_all_checkbox.click();
		}
		spricing_page.commod_arrow.click();
		spricing_page.commod_select_all.click();
		spricing_page.level_select.selectByVisibleText("FE Board");
		spricing_page.search_button.click();
		Thread.sleep(2000);
	}
	
	@When("select inventory type and company from dropdown")
	public void select_company() {
		spricing_page.invent_type_radio_fe.click();
		spricing_page.company_select.selectByVisibleText("MRS");
	}
	
	@When("select branch and level from list")
	public void select_branch() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println(spricing_page.branches_select.getText());
		if(spricing_page.branches_select.getText().contains("MRS-GA, MRS-MR, MRS-WH")) {
			spricing_page.branches_select.click();
			spricing_page.branches_select_all_checkbox.click();
		}
		spricing_page.gast_branch_check.click();
		spricing_page.level_select.selectByVisibleText("FE Board");
	}
	
	@When("scale pricing search")
	public void search_button() throws InterruptedException {
		spricing_page.search_button.click();
		Thread.sleep(2000);
	}
	
	@When("user adds new price")
	public void add_new_price() {
		spricing_page.addnew_button.click();
	}
	
	@When("user enters new price details")
	public void add_new_price_details() throws InterruptedException {
		spricing_page.addprice_branch_select.selectByVisibleText("MRS-GA");
		spricing_page.addprice_eff_date.clear();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();  
		spricing_page.addprice_eff_date.sendKeys(formatter.format(date).toString());
		Calendar today = Calendar.getInstance();
		spricing_page.addprice_exp_date.clear();
		today.add(Calendar.DAY_OF_MONTH, 2);
		
		spricing_page.addprice_exp_date.sendKeys(formatter.format(today.getTime()));
		System.out.println("today date->"+formatter.format(date).toString()+"after 2 days->"+formatter.format(today.getTime()));
		spricing_page.first_price_field.sendKeys(Keys.TAB);
		spricing_page.first_price_field.sendKeys("200");
		Thread.sleep(2000);
		material = spricing_page.first_price_material.getText();
		
		material = material.split(" - ")[1];
		Serenity.setSessionVariable("Material").to(material);
		System.err.println("-material-----"+material);
		spricing_page.ok_button.click();
		Thread.sleep(2000);
	}
	
	@Then("Add variances for all levels")
	public void add_variance() throws InterruptedException {
		int cnt = spricing_page.material_rows().size();
		System.err.println("-rows-count -----"+cnt);
		for(int i=1;i<cnt;i++) {
			if(spricing_page.getDriver().findElements(By.cssSelector("table.price-table tbody tr:nth-child("+i+") td")).size()>4) {		
				System.err.println(spricing_page.material_table(i, 3).getText());
				if(spricing_page.material_table(i, 3).getText().equals(material)) {
					System.err.println("added successfully--!!!!!!!--");
					logsteps.execution_log(""+spricing_page.material_table(i, 3).getText());
					spricing_page.material_table(i, 10).click();
					spricing_page.varience_amount_input.sendKeys("5");
					logsteps.execution_log("entered varience amount for level 1");
					spricing_page.varience_ok.click();
					spricing_page.material_table(i, 12).click();
					spricing_page.varience_amount_input.sendKeys("-4");
					logsteps.execution_log("entered varience amount for level 2");
					spricing_page.varience_ok.click();
					spricing_page.material_table(i, 14).click();
					spricing_page.varience_percent_input.sendKeys("2");
					logsteps.execution_log("entered varience percent for level 3");
					Thread.sleep(1000);
					spricing_page.varience_ok.click();
					Thread.sleep(1000);
					spricing_page.material_table(i, 16).click();
					spricing_page.varience_percent_input.sendKeys("3");
					logsteps.execution_log("entered varience percent for level 4");
					Thread.sleep(2000);
					jsClick(spricing_page.varience_ok);
					
					
				}
			}
		}
		Thread.sleep(2000);
	}
	
	@Then("new price validations before save")
	public void new_price_validations_before_save() throws InterruptedException {
		int cnt = spricing_page.material_rows().size();
		System.err.println("-rows-count -----"+cnt);
		for(int i=1;i<cnt;i++) {
			if(spricing_page.getDriver().findElements(By.cssSelector("table.price-table tr:nth-child("+i+") td")).size()>4) {				
				if(spricing_page.material_table(i, 3).getText().equals(material)) {
					System.err.println("added successfully--!!!!!!!--");
					logsteps.execution_log(""+spricing_page.material_table(i, 3).getText());
			//		System.err.println("--"+spricing_page.material_table(i, 4).getText());
			//		System.err.println("--"+spricing_page.material_table(i, 5).getText());
			//		System.err.println("--"+spricing_page.material_table(i, 6).getText());
			//		System.err.println("--"+spricing_page.material_table(i, 7).getText());
				}
			}
		}
		Thread.sleep(2000);
	}
	
	@Then("save price material")
	public void save_price() throws InterruptedException {
		spricing_page.save_button.click();
		Thread.sleep(12000);
	}
	
	@Then("Navigate to price list")
	public void navigate_to_pricelist() throws InterruptedException {
		spricing_page.save_button.click();
		Thread.sleep(2000);
	}
}
