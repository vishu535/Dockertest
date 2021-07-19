package ca.poc.djj.steps;


import java.util.List;

import org.jbehave.core.annotations.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import ca.poc.djj.pages.ScrapdragonPage;
import ca.poc.djj.utils.LogSteps;
import io.appium.java_client.windows.WindowsDriver;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class ScrapDragonSteps extends CommonSteps {
	@Steps
	LogSteps logsteps;
	ScrapdragonPage sdp;
	public WindowsDriver driverwin;
	@When("user opens scrapdragon application")
	public void open_scrapdragon() throws InterruptedException, FindFailed {
		driverwin = setupScrapdragon();
		logsteps.execution_log("opened scrap dragon desktop application");
		System.out.println("after setup!!!!!!!!");
		driverwin.findElementByName("I accept the license agreement.").click();
		System.out.println("after click!!!!!!!!");		
		driverwin.findElementByName("Your Primary Email Address").sendKeys("vishveshwar.pulla@djj.com");
		logsteps.execution_log("scrapdragon username entered");
		driverwin.findElementByName("Next").click();
		driverwin.findElementByName("Enter password").sendKeys("GalliumGa31");
		driverwin.findElementByName("Sign in").click();
		Thread.sleep(10000);
		driverwin.findElementByName("Non Corporate Yards").click();
		driverwin.findElementByName("MRS-GA").click();
		logsteps.execution_log("selected MRS-GA");
		System.out.println("before sleep!!!!!!!");
		Thread.sleep(27000);
		Screen s = new Screen();
		driverwin.findElementByAccessibilityId("Maximize-Restore").click();
		Thread.sleep(2000);
		Pattern backoffice_button = new Pattern("C:\\Users\\vishu\\Pictures\\BckOffice.png");
		s.click(backoffice_button);
		Thread.sleep(4000);
		//       Pattern masterfiles = new Pattern("C:\\Users\\vishu\\Pictures\\masterfiles.png");
		Thread.sleep(2000);
		//    driverwin.findElementByXPath("//*[@Name='ScrapDragon.Client.BackOffice.Wpf.Models.SubMenu.MenuModel'] //*[@Name='Master Files']").click();
		driverwin.findElementByXPath("//*[@ClassName='SubMenuDialogView'] //*[@Name='Master Files']").click();
		Thread.sleep(2000);
		driverwin.findElementByName("Customers").click();
		Thread.sleep(4000);
		WebDriverWait wait = new WebDriverWait(driverwin, 60);
		//	wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Search All")));
		driverwin.findElementByAccessibilityId("_searchTextBox").sendKeys("rptestt");
		Thread.sleep(1000);
		Actions action = new Actions(driverwin);
		action.sendKeys(Keys.chord(Keys.ENTER)).perform();
		Thread.sleep(10000);
		driverwin.findElementByXPath("//*[@Name='ScrapDragon.Client.BackOffice.Wpf.Models.Customer.CustomerListDataModel']/*[@Name='Rptestt']").click();
		Thread.sleep(6000);
		String str2 = driverwin.findElementByXPath("(//*[@ClassName='GroupBox']/*[@ClassName='TextBlock'])[5]").getText();
		Thread.sleep(5000);
		System.out.println(str2);
		Thread.sleep(2000);
		driverwin.findElementByAccessibilityId("Close").click();
		Thread.sleep(3000);
		driverwin.findElementByXPath("//*[@ClassName='Button'] //*[@Name='Yes']").click();
		Thread.sleep(6000);



	}
	@When("user opens scrapdragon application and validates")
	public void open_validate_scrapdragon_pricing() throws InterruptedException, FindFailed {
		driverwin = setupScrapdragon();
		logsteps.execution_log("opened scrap dragon desktop application");
		System.out.println("after setup!!!!!!!!");
		driverwin.findElementByName("I accept the license agreement.").click();
		System.out.println("after click!!!!!!!!");		
		driverwin.findElementByName("Your Primary Email Address").sendKeys("vishveshwar.pulla@djj.com");
		logsteps.execution_log("scrapdragon username entered");
		driverwin.findElementByName("Next").click();
		driverwin.findElementByName("Enter password").sendKeys("GalliumGa31");
		driverwin.findElementByName("Sign in").click();
		Thread.sleep(10000);
		driverwin.findElementByName("Non Corporate Yards").click();
		logsteps.execution_log("Non corporate yards");
		driverwin.findElementByName("MRS-GA").click();
		logsteps.execution_log("yard MRS");
		logsteps.execution_log("selected MRS-GA");
		System.out.println("before sleep!!!!!!!");
		Thread.sleep(27000);
		System.out.println("wait time finished");
		Screen s = new Screen();
		driverwin.findElementByAccessibilityId("Maximize-Restore").click();
		logsteps.execution_log("maximize sd sscreen");
		Thread.sleep(2000);
		Pattern buy_button = new Pattern("C:\\Users\\vishu\\Pictures\\Buy.png");
		s.click(buy_button);
		logsteps.execution_log("entered on buy module");
		Thread.sleep(3000);
	//	Pattern quickTicket_button = new Pattern("C:\\Users\\vishu\\Pictures\\Buy.png");
	//	s.click(quickTicket_button);//Quick Ticket
		Thread.sleep(8000);
        Pattern quickTicket_button = new Pattern("C:\\Users\\vishu\\Pictures\\QuickTicket.png");
        s.click(quickTicket_button);
        logsteps.execution_log("selected quick ticket button");
        Thread.sleep(10000);
        System.out.println("hello losding components");
   //     driver.findElementByXPath("//*[@Name='Quick Ticket']").click();
        Thread.sleep(15000);
     //   Pattern lookup_button = new Pattern("C:\\Users\\vishu\\Pictures\\lookup.png");
        //s.wait(lookup_button.similar(0.8));
    //    s.click(lookup_button);
        
        driverwin.findElementByName("Lookup Commodity").click();
        logsteps.execution_log("search for the material");
    //    driver.findElementByXPath("//*[@Name='Lookup Commodity']").sendKeys("1001");
        Actions action = new Actions(driverwin);
        action.sendKeys(Keys.chord(Serenity.sessionVariableCalled("Material").toString())).perform();
    //    action.sendKeys(Keys.chord("Crushed Cars Internal")).perform();
        Thread.sleep(3000);
        driverwin.findElementByXPath("//*[@Name='ScrapDragon.Client.BackOffice.Wpf.Models.Shared.CommodityListDataModel']/*").click();
        Thread.sleep(3000);
        logsteps.execution_log("validate the material");
        List<WebElement> li = driverwin.findElementsByXPath("(//*[@ClassName='ListBoxItem'])[1]/*[@ClassName='Button']");
        System.out.println(li.size());
        Thread.sleep(3000);
		Thread.sleep(2000);
		driverwin.findElementByAccessibilityId("Close").click();
		Thread.sleep(3000);
		driverwin.findElementByXPath("//*[@ClassName='Button'] //*[@Name='Yes']").click();
		Thread.sleep(10000);
	}


}
