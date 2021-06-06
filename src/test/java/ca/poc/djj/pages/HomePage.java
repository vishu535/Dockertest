package ca.poc.djj.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import ca.poc.djj.steps.CommonSteps;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class HomePage extends PageObject{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
		
	@Steps
	CommonSteps cs; 
	
	@FindBy(xpath = "//div[@id='otherTileText']")
	public WebElementFacade useotheraccount;

	@FindBy(xpath = "//input[@type='email']")
	public WebElementFacade TXT_Username;
	
	@FindBy(xpath = "//input[@type='submit']")
	public WebElementFacade Button_Next;
	
	@FindBy(xpath = "//input[@type='password']")
	public WebElementFacade TXT_Password;
	
	@FindBy(xpath = "//input[@value='Sign in']")
	public WebElementFacade btn_signIn;
	
	@FindBy(xpath = "//input[@value='Yes']")
	public WebElementFacade btn_Yes;
	
	@FindBy(css = ".modulesFlyout-ExpandAll")
	public WebElementFacade expand_all;
	
	@FindBy(xpath = "//span[contains(text(),'Sign out')]")
	public WebElementFacade link_signout;
	
	@FindBy(xpath = "//span[@class='navigation-bar-userInitials']")
	public WebElementFacade btn_clickonUser;

	@FindBy(xpath = "//div[@class='gutterList']//div[@id='modulesPaneOpener']")
	public WebElementFacade btn_pane;
	
	@FindBy(css = "#brkcontractform_3_HeaderEntrySections")
	public WebElementFacade test_loc_1;

	@FindBy(xpath = "//div[@id='navPaneModuleID']//span[@class='workspace-image GroupedList-symbol']")
	public WebElementFacade btn_ModulesPane;
	
	@FindBy(xpath = "//input[@name='NavigationSearchBox_searchBoxInput']")
	public WebElementFacade searchBox;
	
	@FindBy(xpath = "//*[@id='NavigationSearchBox_listbox_item0']/span[@title='Batch jobs']")
	public WebElementFacade batchJobsLink;
	
	@FindBy(xpath = "//*[@name='QuickFilter_Input']")
	public WebElementFacade batchJobFilter;
	
	@FindBy(xpath = "//span[text()='Run by']")
	public WebElementFacade filter_runby;
	
//	@FindBy(css = ".listBody > div:nth-child(1) > div:nth-child(3) input")  #changed
	@FindBy(css = "#BatchJob_Status_input")
	public WebElementFacade batch_job_status;
	
	@FindBy(xpath = "//div/span[@class='button-commandRing Refresh-symbol']")
	public WebElementFacade btn_refresh;
	
	@FindBy(css = "[title='Run in the background']")
	public WebElementFacade run_in_backgound_link;
	
	@FindBy(xpath = "//label[text()='Batch processing']/following-sibling::div/span[@class='toggle-value']")
	public WebElementFacade toggleValue;

	@FindBy(xpath = "//label[text()='Batch processing']/following-sibling::div/span[@role='switch']")
	public WebElementFacade toggleSwitch;
	
	@FindBy(xpath = "//span[text()='OK']")
	public WebElementFacade ok_button;

	@FindBy(xpath = "//div[text()='The Invoice shipments job is added to the batch queue.']")
	public List<WebElementFacade> BatchRunMsg;
	
	@FindBy(xpath = "(//a[text()='DailyContractDocuments'])[2]")
	public WebElementFacade contract_documents;

	@FindBy(xpath = "//div[text()='Code + Test']")
	public WebElementFacade code_test;
	
	@FindBy(xpath = "//div[text()='Test/Run']")
	public WebElementFacade test_run;
	
	public WebElementFacade workspace(String workspace) {
		return findBy("//a[text()='" + workspace + "']");
	}
	
	public void jsexecuteclick(WebElementFacade WebElement) {
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", WebElement);
	}
	
	@Step
	public void execution_log(String myMessage) {
		System.out.println(myMessage);
	}
	
	@Step
	 public void workspace_modules_icon() throws InterruptedException {
		Thread.sleep(1000);
	//	 jsexecuteclick(btn_ModulesPane);
		 Thread.sleep(500);
	 }
	
	@Step
	public void shipment_batch_processing_toggle() {
		if (toggleValue.getAttribute("title").equals("No")) {
			toggleSwitch.click();
		}
		ok_button.click();
		if (BatchRunMsg.size() != 0)
			System.out.println("Batch process done!!!");
//TODO			logsteps.execution_log("Batch process is done");
	}
	
	@Step
	 public void navigate_to_modules(String modu) throws InterruptedException {
		Thread.sleep(500);
		while((getDriver().findElements(By.xpath("//a[text()='" + modu + "']")).size() == 0)) {
			jsexecuteclick(btn_ModulesPane);
			Thread.sleep(2000);
		}
		workspace(modu).click();
		Thread.sleep(500);
	 }
	
	@Step
	public void navigate_to_workspace(String ws) throws InterruptedException {
		expand_all.click();
		workspace(ws).click();
		Thread.sleep(1000);
	}
	
	@Step
	public void run_in_the_background() {
		run_in_backgound_link.click();
	}
	
	@Step
	public void search_batch_job() {
		searchBox.type("batch jobs");
		batchJobsLink.click();
	}
	
	@Step
	public void batch_jobs_filter_by_username(String username) throws InterruptedException {
		batchJobFilter.type(username.split("@")[0]);
		Thread.sleep(1500);
		filter_runby.waitUntilVisible();
		filter_runby.click();
		Thread.sleep(2000);
		while(!batch_job_status.getAttribute("title").contains("Ended")) {
			jsexecuteclick(btn_refresh);
			Thread.sleep(4000);
		}
	}
}
