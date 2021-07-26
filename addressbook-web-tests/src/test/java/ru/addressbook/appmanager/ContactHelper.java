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
	
	public int count() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public void fillContactForm(ContactData contactData, boolean creation) {
		type(By.name("firstname"), contactData.getFirstName());
		type(By.name("lastname"), contactData.getLastName());

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
		wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
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
		contactCache = null;
		returnToHomePage();
	}

	public void modify(ContactData cd) {
		gotoEditFormById(cd.getId());
		fillContactForm(cd, false);
		submitContactModification();
		contactCache = null;
		returnToHomePage();
	}
	
	public void delete(ContactData cd) {
		selectById(cd.getId());
		deletSelected();
		contactCache = null;
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int getContactCount() {
		return wd.findElements(By.name("selected[]")).size();
	}

	private Contacts contactCache = null;
	
	public Contacts all() {
		if (contactCache != null) {
			return new Contacts(contactCache);
		}
		contactCache = new Contacts();
		List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));

		for (WebElement element : elements) {
			List<WebElement> cells = element.findElements(By.tagName("td"));
			int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
			String firstName = cells.get(2).getText();
			String lastName = cells.get(1).getText();
			String allPhones = cells.get(5).getText();
			String allEmails = cells.get(4).getText();
			String address = cells.get(3).getText();
			ContactData cd = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
					.withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
			contactCache.add(cd);
		}
		return new Contacts(contactCache);
	}

	public ContactData infoFromEditForm(ContactData contact) {
		gotoEditFormById(contact.getId());
		String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
		String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
		String home = wd.findElement(By.name("home")).getAttribute("value");
		String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
		String work = wd.findElement(By.name("work")).getAttribute("value");
		
		String email1 = wd.findElement(By.name("email")).getAttribute("value");
		String  email2 = wd.findElement(By.name("email2")).getAttribute("value");
		String email3 = wd.findElement(By.name("email3")).getAttribute("value");
		
		String address = wd.findElement(By.name("address")).getAttribute("value");
		
		wd.navigate().back();
		return new ContactData().withId(contact.getId()).withFirstName(firstName)
				.withLastName(lastName).withHomePhone(home)
				.withMobilePhone(mobile).withWorkPhone(work)
				.withEmail1(email1).withEmail2(email2).withEmail3(email3)
				.withAddress(address);
	}
}
