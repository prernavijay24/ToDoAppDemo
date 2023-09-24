package in.todo.tests;

import java.io.IOException;

import java.util.Properties;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.CommonElement;
import commonLibs.utils.CSVUtils;
import commonLibs.utils.DateUtils;
import commonLibs.utils.ExtentReportUtils;

public class BaseTests {

	ExtentReportUtils extentReportUtils;
	CommonElement commonElement;
	CommonDriver commonDriver;
	CSVUtils csvUtils;
	Properties configProperties;
	WebDriver driver;
	HomePageElement pageElement = new HomePageElement();

	String configFileName;
	String currentWorkingDirectory;
	String executionStartTime;
	String htmlReportFileName;

	public void initializeReport() throws IOException {

		executionStartTime = DateUtils.getCurrentDatetime();

		currentWorkingDirectory = System.getProperty("user.dir");
		htmlReportFileName = String.format("%s\\reports\\ToDo-report-%s.html", currentWorkingDirectory,
				executionStartTime);
		extentReportUtils = new ExtentReportUtils(htmlReportFileName);

		csvUtils = new CSVUtils();

	}

	public void invokeBrowser() throws Exception {

		String browserType = csvUtils.browserType;

		commonDriver = new CommonDriver(browserType);

		String baseURL = csvUtils.baseURL;

		commonDriver.navigateToUrl(baseURL);

	}

	// Checking the no ToDos Visibility on HomePage
	public void checkToDoText() throws Exception {

		extentReportUtils.createTest("TC_001 - Verify if 'noToDo' text is displaying on Home Page");
		commonElement = new CommonElement();

		driver = commonDriver.getDriver();

		// Get the element value from HomePageElement
		WebElement noToDoText = driver.findElement(By.className(HomePageElement.noToDoText));

		commonElement.isElementVisible(noToDoText);

		String actualResult = commonElement.getText(noToDoText);
		String expectedResult = csvUtils.toDoExpectedResult;

		if (actualResult.equalsIgnoreCase(expectedResult)) {
			extentReportUtils.addLog(Status.INFO, "'No ToDos' text is visible on Home Page: " + actualResult);

		} else {

			extentReportUtils.addLog(Status.INFO, "'No ToDos' text is not visible on Home Page: " + actualResult);
		}

	}

	// Adding a task on HomePage
	public void addTask() throws InterruptedException {

		extentReportUtils.createTest("TC_002 - Verify if add task dialog box is visible and task is getting created");

		driver = commonDriver.getDriver();

		WebElement addTask = driver.findElement(By.xpath(HomePageElement.addTaskBtn));
		addTask.click();

		WebElement title = driver.findElement(By.id("title"));
		String titleTextbox = title.getText();

		WebElement titleText = driver.findElement(By.xpath(HomePageElement.addToDoTitleText));

		String actualResultText = titleText.getText();

		// Fetching the data from CS file
		String expectedResult = csvUtils.addTaskExpectedResult;

		if (actualResultText.equalsIgnoreCase(expectedResult)) {
			extentReportUtils.addLog(Status.INFO, "Dialog Box is  Visible");
		} else {
			extentReportUtils.addLog(Status.INFO, "Dialog Box is not Visible");
		}

		Thread.sleep(2000);

		String firstTaskName = csvUtils.firstTaskName;

		String blankvalue = "";

		Thread.sleep(2000);

		title.sendKeys(firstTaskName);

		WebElement submitBtn = driver.findElement(By.xpath(HomePageElement.addTasksubmitBtn));
		submitBtn.click();

		try {
			WebElement taskadded = driver.findElement(By.xpath(HomePageElement.taskAddedSuccess));

			extentReportUtils.addLog(Status.INFO, "Task added successfully info message is displaying ");
		} catch (Exception e) {
			e.printStackTrace();
			extentReportUtils.addLog(Status.INFO, "Task added successfully info message is not displaying ");

		}
		Thread.sleep(2000);

		WebElement taskName = driver.findElement(By.xpath(HomePageElement.taskName));
		String nameoffirstTask = taskName.getText();

		if (firstTaskName.equalsIgnoreCase(nameoffirstTask)) {
			extentReportUtils.addLog(Status.INFO, "Task added successfully: " + firstTaskName);
		} else {
			extentReportUtils.addLog(Status.INFO, "Task is not added: " + firstTaskName);
		}

		Thread.sleep(2000);
	}

	public void cancelTask() throws InterruptedException {

		extentReportUtils.createTest("TC_003 - Verify if add task dialog box is visible and task is not getting added when cancel button is pressed	");

		driver = commonDriver.getDriver();

		WebElement addTask = driver.findElement(By.xpath(HomePageElement.addTaskBtn));
		addTask.click();

		WebElement title = driver.findElement(By.id("title"));

		WebElement titleText = driver.findElement(By.xpath(HomePageElement.addToDoTitleText));

		String actualResultText = titleText.getText();

		String expectedResult = csvUtils.addTaskExpectedResult;

		if (actualResultText.equalsIgnoreCase(expectedResult)) {
			extentReportUtils.addLog(Status.INFO, "Dialog Box is  Visible");
		} else {
			extentReportUtils.addLog(Status.INFO, "Dialog Box is not Visible");
		}

		Thread.sleep(2000);

		// String secondTaskName = configProperties.getProperty("secondTaskName");

		String secondTaskName = csvUtils.secondTaskName;

		title.sendKeys(secondTaskName);

		WebElement cancelTaskBtn = driver.findElement(By.xpath(HomePageElement.cancelTaskBtn));
		cancelTaskBtn.click();

		try {
			WebElement taskName = driver.findElement(By.xpath(HomePageElement.cancelTaskName));
			String nameOfSecondTask = taskName.getText();
			if (secondTaskName.equalsIgnoreCase(nameOfSecondTask)) {
				extentReportUtils.addLog(Status.INFO, "Task is  added:  " + secondTaskName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			extentReportUtils.addLog(Status.INFO, "Task is cancelled: " + secondTaskName);

		}

	}

	// Validating the close button functionality on Add Task dialog window
	public void closeAddTaskDialog() throws InterruptedException {

		extentReportUtils.createTest("TC_010 - Verify if close button is not adding the task");
		driver = commonDriver.getDriver();

		WebElement addTask = driver.findElement(By.xpath(HomePageElement.addTaskBtn));
		addTask.click();

		WebElement title = driver.findElement(By.id("title"));

		String thirdTaskName = csvUtils.thirdTaskName;

		title.sendKeys(thirdTaskName);

		WebElement closeBtn = driver.findElement(By.xpath(HomePageElement.closeBtn));
		closeBtn.click();

		Thread.sleep(2000);

		try {
			WebElement taskName = driver.findElement(By.xpath(HomePageElement.closeTaskName));
			String nameOfThirdTask = taskName.getText();
			if (thirdTaskName.equalsIgnoreCase(nameOfThirdTask)) {
				extentReportUtils.addLog(Status.INFO, "Task is  added:  " + thirdTaskName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			extentReportUtils.addLog(Status.INFO, "Task is not added: " + thirdTaskName);

		}

	}

	// Updating the values in existing task
	public void updateToDoTask() throws InterruptedException

	{

		extentReportUtils.createTest("TC_004 - Verify whether title and status are getting updated successfully");

		driver = commonDriver.getDriver();

		Thread.sleep(2000);

		WebElement element = driver.findElement(By.xpath(HomePageElement.editBtn));

		element.click();

		WebElement title = driver.findElement(By.id("title"));

		WebElement updateToDo = driver.findElement(By.xpath(HomePageElement.updateToDoText));

		String updateToDoValue = updateToDo.getText();

		String expectedResult = csvUtils.updateToDoExpecteResult;

		Thread.sleep(2000);

		if (updateToDoValue.equalsIgnoreCase(expectedResult)) {
			extentReportUtils.addLog(Status.INFO, "Update dialog Box is open");
		} else {
			extentReportUtils.addLog(Status.INFO, "Update dialog Box is not open");
		}

		title.clear();

		String updatedTitle = csvUtils.updatedTitle;

		Thread.sleep(2000);
		title.sendKeys(updatedTitle);

		WebElement updateBtn = driver.findElement(By.xpath(HomePageElement.updateBtn));
		updateBtn.click();

		try {
			WebElement taskUpdateMsg = driver.findElement(By.xpath(HomePageElement.taskUpdateSucces));

			extentReportUtils.addLog(Status.INFO,
					"Task updated successfully info message is displaying when title was updated");
		} catch (Exception e) {
			e.printStackTrace();
			extentReportUtils.addLog(Status.INFO,
					"Task updated successfully info message is not displaying when title was updated ");

		}

		Thread.sleep(2000);

		WebElement updatedTitleElement = driver.findElement(By.xpath(HomePageElement.updateTitleValue));
		String updatedTitleValue = updatedTitleElement.getText();

		if (updatedTitleValue.equalsIgnoreCase(updatedTitle))

		{
			extentReportUtils.addLog(Status.INFO, "Title updated successfully: " + updatedTitleValue);
		} else {
			extentReportUtils.addLog(Status.INFO, "Title is not updated: " + updatedTitleValue);
		}

		element.click();

		WebElement selectStatus = driver.findElement(By.xpath(HomePageElement.selectUpdateDropDown));

		String completeValue = csvUtils.selectCompleteValue;

		Select select = new Select(selectStatus);
		select.selectByVisibleText(completeValue);

		WebElement updateTaskBtn = driver.findElement(By.xpath(HomePageElement.updateBtn));
		updateTaskBtn.click();

		try {
			WebElement statusUpdateMsg = driver.findElement(By.xpath(HomePageElement.taskUpdateSucces));

			extentReportUtils.addLog(Status.INFO,
					"Task updated successfully info messge is displaying when status was updated ");
		} catch (Exception e) {
			e.printStackTrace();
			extentReportUtils.addLog(Status.INFO, "Task updated successfully info message is not displaying ");

		}

		Thread.sleep(2000);

		WebElement completetaskName = driver.findElement(By.xpath(HomePageElement.completedTaskName));
		String nameoffifthTask = completetaskName.getAttribute("class");

		if (nameoffifthTask.contains("completed"))

		{
			extentReportUtils.addLog(Status.INFO, "Status updated successfully: `" + nameoffifthTask);
		} else {
			extentReportUtils.addLog(Status.INFO, "Status is not updated: " + nameoffifthTask);
		}

		WebElement editElement = driver.findElement(By.xpath(HomePageElement.editBtn));

		editElement.click();
		WebElement UpdateWithNoChange = driver.findElement(By.xpath(HomePageElement.updateBtn));
		UpdateWithNoChange.click();
		try {
			WebElement noChangesMade = driver.findElement(By.xpath(HomePageElement.noChangeMade));

			extentReportUtils.addLog(Status.INFO, "No changes made info messge is displaying");

			WebElement cancelUpdateBtn = driver.findElement(By.xpath(HomePageElement.updateCancelBtn));
			cancelUpdateBtn.click();

		} catch (Exception e) {
			e.printStackTrace();
			extentReportUtils.addLog(Status.INFO,
					"No changes made info message is not displaying when nothing changed ");

			
		}

		Thread.sleep(2000);
		
		WebElement cancelElement = driver.findElement(By.xpath(HomePageElement.editBtn));

		cancelElement.click();

		WebElement cancelUpdateBtn = driver.findElement(By.xpath(HomePageElement.updateCancelBtn));
		cancelUpdateBtn.click();

		try {
			WebElement taskUpdateMsg = driver.findElement(By.xpath(HomePageElement.taskUpdateSucces));

			WebElement statusUpdateMsg = driver.findElement(By.xpath(HomePageElement.taskUpdateSucces));

			extentReportUtils.addLog(Status.INFO, "Task updated successfully when cancel button was pressed");

		}

		catch (Exception e) {
			e.printStackTrace();

			extentReportUtils.addLog(Status.INFO, "Task is not updated when cancel button was pressed");

			// TODO: handle exception
		}
	}

	// Deleting the task
	public void deleteToDoTask() throws InterruptedException

	{

		extentReportUtils.createTest("TC_005 - Verify whether task is getting deleted");

		driver = commonDriver.getDriver();

		Thread.sleep(2000);

		WebElement element = driver.findElement(By.xpath(HomePageElement.deleteBtn));

		element.click();

		Thread.sleep(2000);

		try {
			WebElement deleteBtn = driver.findElement(By.xpath(HomePageElement.deleteBtn));

			deleteBtn.click();

			WebElement taskDelete = driver.findElement(By.xpath(HomePageElement.deleteTaskSucess));

			extentReportUtils.addLog(Status.INFO,
					"Task deleted successfully info message is not displaying on home page");

			extentReportUtils.addLog(Status.INFO, "Task is not deleted");

		} catch (Exception e) {
			e.printStackTrace();
			extentReportUtils.addLog(Status.INFO, "Task deleted successfully info message is displaying on home page");
			extentReportUtils.addLog(Status.INFO, "Task deleted successfully");
		}

	}
}
