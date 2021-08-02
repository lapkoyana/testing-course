package ru.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import ru.addressbook.appmanager.ApplicationManager;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Collectors;

@Listeners(MyTestListener.class)
public class TestBase{
	
	protected static final ApplicationManager am =
			new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

	@BeforeSuite(alwaysRun = true)
	public void setUp(ITestContext context) throws Exception {
	    am.init();
	    context.setAttribute("am", am);
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		am.stop();
	  }

	public void verifyGroupListInIU() {
		if (Boolean.getBoolean("verifyUI")) {
			Groups dbGroups = am.db().groups();
			Groups uiGroups = am.group().all();
			
			assertThat(uiGroups, equalTo(dbGroups.stream()
						.map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
						.collect(Collectors.toSet())));
		}
	}
}
