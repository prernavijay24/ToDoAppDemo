package in.todo.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonElement;

public class ToDoHomePageTests extends BaseTests {

	CommonElement commonElement;

	WebDriver driver;

	String currentWorkingDirectory;

	BaseTests baseTest = new BaseTests();

	// This method will get invoked before every test
	@BeforeMethod
	public void preSetup() throws Exception {
		baseTest.initializeReport();
		baseTest.invokeBrowser();
	}

	@Test()
	public void toDoHomePageCases() throws Exception {

		baseTest.checkToDoText();
		baseTest.addTask();
		baseTest.cancelTask();
		baseTest.closeAddTaskDialog();
		baseTest.updateToDoTask();
		baseTest.deleteToDoTask();

	}

	// This method will invoke after every test
	@AfterMethod
	public void postSetup() throws Exception {
		baseTest.extentReportUtils.closeRepor();
		baseTest.commonDriver.closeAllBrowser();

	}

}
