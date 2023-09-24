package commonLibs.implementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import commonLibs.contracts.IDriver;

public class CommonDriver implements IDriver {

	private WebDriver driver;

	private String currentWorkingDirectory;

	public WebDriver getDriver() {
		return driver;
	}

	public CommonDriver(String browserType) throws Exception {
		currentWorkingDirectory = System.getProperty("user.dir");
		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + "/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserType.equalsIgnoreCase("chrome-headless")) {

			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + "/drivers/chromedriver.exe");

			ChromeOptions chrOptions = new ChromeOptions();
			chrOptions.addArguments("--headless");
			//driver = new ChromeDriver();

		}

		else {
			throw new Exception("Invalid Browser type " + browserType);
		}

		driver.manage().window().maximize();
	}

	public void navigateToUrl(String url) throws Exception {

		driver.get(url);

	}

	public void refresh() throws Exception {
		driver.navigate().refresh();
	}

	public void navigateBack() throws Exception {
		driver.navigate().back();
	}

	public void navigateForward() throws Exception {
		driver.navigate().forward();

	}

	public String getTitle() throws Exception {
		return driver.getTitle();

	}

	public String getCurrentURl() throws Exception {

		return driver.getCurrentUrl();
	}

	public void close() throws Exception {

		if (driver != null) {
			driver.close();
		}

	}

	public void closeAllBrowser() throws Exception {
		// TODO Auto-generated method stub
		if (driver != null) {
			driver.quit();
		}
	}

}
