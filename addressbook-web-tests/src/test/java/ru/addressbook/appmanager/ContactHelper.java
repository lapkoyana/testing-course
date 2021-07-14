package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import ru.addressbook.model.ContactData;

public class ContactHelper {
	FirefoxDriver wd;
	
	public ContactHelper(FirefoxDriver wd) {
		this.wd = wd;
	}
	
	public void submitContactCreation() {
		wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
	}

	public void fillContactForm(ContactData parameterObject) {
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
}
