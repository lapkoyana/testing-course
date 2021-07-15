package ru.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ru.addressbook.appmanager.ApplicationManager;

public class TestBase{
	
	ApplicationManager am = new ApplicationManager(BrowserType.FIREFOX);

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
	    am.init();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		am.stop();
	  }

}
