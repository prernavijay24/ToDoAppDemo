package in.todo.tests;

import org.openqa.selenium.WebDriver;

//All the element used on ToDoApp home page are defined here
public class HomePageElement {

	WebDriver driver;

	public static String noToDoText = "app_emptyText__11zUS";

	public static String addTaskBtn = "//*[@id='root']/div[1]/div/div[1]/button";

	public static String addToDoTitleText = "//*[@id='root']/div[1]/div/div[1]/div/div/form/h1";

	public static String addTasksubmitBtn = "//*[@id='root']/div[1]/div/div[1]/div/div/form/div/button[1]";
	
	public static String taskAddedSuccess = "//div[@role= 'status' and text()='Task added successfully']";

	public static String taskName = "//*[@id='root']/div[1]/div/div[2]/div/div[1]/div[2]/p[1]";

	public static String cancelTaskBtn = "//*[@id='root']/div[1]/div/div[1]/div/div/form/div/button[2]";

	public static String cancelTaskName = "//p[starts-with(text(),'To buy halloween costumes')]";

	public static String closeBtn = "//div[@class='modal_closeButton__Fg7AM']//*[@stroke='currentColor']";

	public static String closeTaskName = "//p[starts-with(text(),'To clean dishes')]";

	public static String editBtn = "//div[@class='todoItem_icon__+DYyU']//following-sibling::div";

	public static String updateToDoText = "//*[@id='root']/div[1]/div/div[2]/div[2]/div/form/h1";

	public static String updateBtn = "//button[@type='submit']";
	
	public static String updateCancelBtn = "//*[@id='root']/div[1]/div/div[2]/div[2]/div/form/div/button[2]";
	
	public static String taskUpdateSucces = "//div[@role= 'status' and text()='Task Updated successfully']";

	public static String updateTitleValue = "//*[@id=\"root\"]/div[1]/div/div[2]/div/div[1]/div[2]/p[1]";

	public static String selectUpdateDropDown = "//*[@id='type']";

	public static String completedTaskName = "//p[contains(@class ,'completed')]";

	public static String deleteBtn = "//div[@class='todoItem_icon__+DYyU']//*[@stroke='currentColor']";
	
	public static String deleteTaskSucess = "//div[@role= 'status' and text()='Todo Deleted Successfully']";
	
	public static String noChangeMade = "//div[@role= 'status' and text()='No changes made']";
}
