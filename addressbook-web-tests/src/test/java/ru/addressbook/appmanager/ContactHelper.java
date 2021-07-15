package ru.addressbook.appmanager;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ru.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void submitContactCreation() {
		click(By.xpath("//div[@id='content']/form/input[21]"));
	}

	public void fillContactForm(ContactData contactData, boolean creation) {
		type(By.name("firstname"), contactData.getFirstName());
		type(By.name("lastname"), contactData.getLastName());
		type(By.name("email"), contactData.getEmail());

		if (creation) {
			new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}
	}

	public void selectContact(int index) {
		wd.findElements(By.name("selected[]")).get(index).click();
	}

	public void deletSelectedContacts() {
		click(By.xpath("//input[@value='Delete']"));
		wd.switchTo().alert().accept();
	}

	public void gotoEditForm(int index) {
		wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
//		click(By.xpath("//img[@alt='Edit']"));
	}

	public void submitContactModification() {
		click(By.xpath("//div[@id='content']/form/input[22]"));
	}

	public void returnToHomePage() {
		click(By.linkText("home page"));
	}

	public void createContact(ContactData contactData, boolean b) {
		fillContactForm(contactData, b);
		submitContactCreation();
		returnToHomePage();
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int getContactCount() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public List<ContactData> getContactList() {
		List<ContactData> contacts = new ArrayList<>();
		List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
		
		for(WebElement element : elements) {
			//тут исключение StaleElementReferenceException, нз почему
			//после того, как все элементы вроде бы перебрались, опять заходит в цикл
			//это вроде только при удалении
			String name = element.getText();
			//String id = element.findElement(By.tagName("input")).getAttribute("value");
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			String[] tds = name.split(" ");
			ContactData cd = new ContactData(tds[1], tds[0], tds[2], null, id);
			contacts.add(cd);
		}
		return contacts;
	}
}
