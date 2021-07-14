package ru.addressbook.appmanager;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import ru.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{
	
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

	public void selectContact() {
		click(By.name("selected[]"));
	}

	public void deletSelectedContacts() {
//		acceptNextAlert = true;
		click(By.xpath("//input[@value='Delete']"));
		wd.switchTo().alert().accept();
	}
}
