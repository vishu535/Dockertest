package ca.poc.djj.suites;

import java.io.IOException;

import org.jbehave.core.annotations.AfterStories;
import net.serenitybdd.jbehave.SerenityStories;
import net.serenitybdd.jbehave.annotations.Metafilter;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
@Metafilter("+pocerror")
public class RunTest extends SerenityStories {
	
	public RunTest() {
		// This will only load the matched stories.
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
	//	String story_name = variables.getProperty("test.story.name");
	//	findStoriesCalled(story_name + ".story");
	}	
	@AfterStories // equivalent to  @AfterScenario(uponOutcome=AfterScenario.Outcome.ANY)
	public void afterAnyScenario() throws IOException {
		Runtime.getRuntime().exec("aggre.bat");
		System.out.println("in After scenario");	
	}
}
