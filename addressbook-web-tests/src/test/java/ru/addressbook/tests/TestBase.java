package ru.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import ru.addressbook.appmanager.ApplicationManager;

public class TestBase{
	
	protected static final ApplicationManager am = new ApplicationManager(BrowserType.FIREFOX);

	@BeforeSuite(alwaysRun = true)
	public void setUp() throws Exception {
	    am.init();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		am.stop();
	  }

}
