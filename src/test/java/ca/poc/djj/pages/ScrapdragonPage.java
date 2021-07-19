package ca.poc.djj.pages;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ca.poc.djj.steps.CommonSteps;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ScrapdragonPage extends PageObject {
	CommonSteps cs;
	public ScrapdragonPage(WebDriver driver) {
		super(driver);
	}
	public WebElementFacade contract_form_field(String field) {
		return findBy("//label[text()='" + field + "']/following-sibling::input");
	}
	
	@FindBy(css = "[href='/scale-pricing']")
	public WebElementFacade scale_pricing_head;
	
	
}
