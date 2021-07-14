package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import ru.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{
	FirefoxDriver wd;
	
	public ContactHelper(FirefoxDriver wd) {
		super(wd);
	}
	
	public void submitContactCreation() {
		click(By.xpath("//div[@id='content']/form/input[21]"));
	}

	public void fillContactForm(ContactData parameterObject) {
		type(By.name("firstname"), parameterObject.getFirstName());
		type(By.name("lastname"), parameterObject.getLastName());
		type(By.name("email"), parameterObject.getEmail());
	}
}
