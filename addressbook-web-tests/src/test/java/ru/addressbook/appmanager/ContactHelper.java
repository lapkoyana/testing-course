package ru.addressbook.appmanager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

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

	public void select(int index) {
		wd.findElements(By.name("selected[]")).get(index).click();
	}
	
	private void selectById(int id) {
		wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
	}

	public void deletSelected() {
		click(By.xpath("//input[@value='Delete']"));
		wd.switchTo().alert().accept();
	}
	
	public void gotoEditFormById(int id) {
		wd.findElement(By.xpath("//input[@value='" + id + "']/../..//img[@alt='Edit']")).click();
	}

	public void submitContactModification() {
		click(By.xpath("//div[@id='content']/form/input[22]"));
	}

	public void returnToHomePage() {
		click(By.linkText("home page"));
	}

	public void create(ContactData contactData, boolean b) {
		fillContactForm(contactData, b);
		submitContactCreation();
		returnToHomePage();
	}

	public void modify(ContactData cd) {
		gotoEditFormById(cd.getId());
		fillContactForm(cd, false);
		submitContactModification();
		returnToHomePage();
	}
	
	public void delete(ContactData cd) {
		selectById(cd.getId());
		deletSelected();
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int getContactCount() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public Contacts all() {
		Contacts contacts = new Contacts();
		List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));

		for (WebElement element : elements) {
			String name = element.getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			String[] tds = name.split(" ");
			ContactData cd = new ContactData().withId(id).withFirstName(tds[1])
					.withLastName(tds[0]).withEmail(tds[2]);
			contacts.add(cd);
		}
		return contacts;
	}
}
