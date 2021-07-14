package ru.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddANewContactTest {
	  private WebDriver wd;

	  @BeforeClass(alwaysRun = true)
	  public void setUp() throws Exception {
	    wd = new FirefoxDriver();
	    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    login("admin", "secret");
	  }

	private void login(String username, String password) {
		wd.get("http://localhost/addressbook/");
	    wd.findElement(By.name("user")).click();
	    wd.findElement(By.name("user")).clear();
	    wd.findElement(By.name("user")).sendKeys(username);
	    wd.findElement(By.name("pass")).click();
	    wd.findElement(By.name("pass")).clear();
	    wd.findElement(By.name("pass")).sendKeys(password);
	    wd.findElement(By.xpath("//input[@value='Login']")).click();
	}

	  @Test
	  public void testAddANewContact() throws Exception {
	    clickToAddNew();
	    fillContactForm(new ContactData("Cvb", "Yui", "cvb@test.com"));
	    submitContactCreation();
	  }

	private void submitContactCreation() {
		wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
	}

	private void fillContactForm(ContactData parameterObject) {
		wd.findElement(By.name("firstname")).click();
	    wd.findElement(By.name("firstname")).clear();
	    wd.findElement(By.name("firstname")).sendKeys(parameterObject.getFirstName());
	    wd.findElement(By.name("lastname")).click();
	    wd.findElement(By.name("lastname")).clear();
	    wd.findElement(By.name("lastname")).sendKeys(parameterObject.getLastName());
	    wd.findElement(By.name("email")).click();
	    wd.findElement(By.name("email")).clear();
	    wd.findElement(By.name("email")).sendKeys(parameterObject.getEmail());
	}

	private void clickToAddNew() {
		wd.findElement(By.linkText("add new")).click();
	}

	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
	    wd.quit();
	  }

	  private boolean isAlertPresent() {
	    try {
	      wd.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }
	}