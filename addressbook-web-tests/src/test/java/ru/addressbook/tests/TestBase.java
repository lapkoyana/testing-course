package ru.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ru.addressbook.appmanager.ApplicationManager;

public class TestBase{
	
	ApplicationManager am = new ApplicationManager();

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
	    am.init();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		am.stop();
	  }

}
