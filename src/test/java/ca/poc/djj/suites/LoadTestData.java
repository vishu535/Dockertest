package ca.poc.djj.suites;
import net.serenitybdd.jbehave.SerenityStories;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
public class LoadTestData extends SerenityStories   {
	public LoadTestData() {
		// This will only load the matched stories.
		findStoriesCalled("LoadTestData.story");
	}
}
