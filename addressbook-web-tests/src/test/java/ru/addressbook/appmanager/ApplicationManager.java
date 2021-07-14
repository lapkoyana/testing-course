package ru.addressbook.appmanager;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
	
	FirefoxDriver wd;
	
	private GroupHelper groupHelper;
	private NavigationHelper navigationHelper;
	private SessionHelper sessionHelper;
	private ContactHelper contactHelper;
	
	public void init() {
		wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/group.php");
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		sessionHelper = new SessionHelper(wd);
		contactHelper = new ContactHelper(wd);
		sessionHelper.login("admin", "secret");
	}

	public void stop() {
		wd.quit();
	}

//	private boolean isElementPresent(By by) {
//	    try {
//	    	wd.findElement(by);
//	      return true;
//	    } catch (NoSuchElementException e) {
//	      return false;
//	    }
//	  }
//
//	private boolean isAlertPresent() {
//	    try {
//	    	wd.switchTo().alert();
//	      return true;
//	    } catch (NoAlertPresentException e) {
//	      return false;
//	    }
//	  }

	public GroupHelper getGroupHelper() {
		return this.groupHelper;
	}
	
	public NavigationHelper getNavigationHelper() {
		return this.navigationHelper;
	}
	
	public ContactHelper getContactHelper() {
		return this.contactHelper;
	}

}
