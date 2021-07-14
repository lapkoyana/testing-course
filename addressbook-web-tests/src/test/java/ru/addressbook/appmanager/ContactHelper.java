package ru.addressbook.appmanager;

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
		click(By.xpath("//input[@value='Delete']"));
		wd.switchTo().alert().accept();
	}

	public void gotoEditForm() {
		click(By.xpath("//img[@alt='Edit']"));
	}

	public void submitContactModification() {
		click(By.xpath("//div[@id='content']/form/input[22]"));
	}

	public void returnToHomePage() {
		click(By.linkText("home page"));
	}
}
