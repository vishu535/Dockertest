package ca.poc.djj.pages;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ca.poc.djj.steps.CommonSteps;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends PageObject {
	CommonSteps cs;
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	public WebElementFacade contract_form_field(String field) {
		return findBy("//label[text()='" + field + "']/following-sibling::input");
	}

	@FindBy(xpath = "//input[@type='email']")
	public WebElementFacade username;
	
	@FindBy(xpath = "//input[@type='submit']")
	public WebElementFacade next_button;
	
	@FindBy(xpath = "//input[@type='password']")
	public WebElementFacade password;

	@FindBy(xpath = "//input[@value='Sign in']")
	public WebElementFacade signin_button;
	
	@FindBy(xpath = "//input[@value='Yes']")
	public WebElementFacade yes_button;
}
