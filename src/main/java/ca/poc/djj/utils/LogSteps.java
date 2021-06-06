package ca.poc.djj.utils;

import net.thucydides.core.annotations.Step;

public class LogSteps {

	// public used by other pages need to print out log in Serenity report
	@Step
	public void debug_log(String myMessage) {
	}

	@Step
	public void execution_log(String myMessage) {
		System.out.println(myMessage);
	}

	@Step
	public void execution_log_ts(String myMessage) {
		// System.out.println(BaseSteps.myTimeStamp() + ": " + myMessage);
	}

}
