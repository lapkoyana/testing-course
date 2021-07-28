package ru.addressbook.appmanager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

public class ApplicationManager {
	private final Properties properties;
	WebDriver wd;

	private GroupHelper groupHelper;
	private NavigationHelper navigationHelper;
	private SessionHelper sessionHelper;
	private ContactHelper contactHelper;
	private String browser;

	public ApplicationManager(String browser) {
		this.browser = browser;
		properties = new Properties();
	}

	public void init() throws IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
		if (browser.equals(BrowserType.FIREFOX)) {
			wd = new FirefoxDriver();
		} else if (browser.equals(BrowserType.CHROME)) {
			wd = new ChromeDriver();
		}
		wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		wd.get(properties.getProperty("web.baseUrl"));
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		sessionHelper = new SessionHelper(wd);
		contactHelper = new ContactHelper(wd);
		sessionHelper.login(properties.getProperty("web.adminLogin"),
				properties.getProperty("web.adminPassword"));
	}

	public void stop() {
		wd.quit();
	}

	public GroupHelper group() {
		return this.groupHelper;
	}

	public NavigationHelper goTo() {
		return this.navigationHelper;
	}

	public ContactHelper contact() {
		return this.contactHelper;
	}

}
