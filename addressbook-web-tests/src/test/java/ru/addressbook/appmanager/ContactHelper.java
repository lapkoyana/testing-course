package ru.addressbook.appmanager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;

public class ContactHelper extends HelperBase {

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void submitContactCreation() {
		click(By.xpath("//div[@id='content']/form/input[21]"));
	}
	
	private void submitOfAddingAContactToTheGroup() {
		click(By.xpath("//input[@name='add']"));
	}
	
	private void submitDeletion() {
	    wd.findElement(By.xpath("//input[@name='remove']")).click();
	}
	
	public int count() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public void fillContactForm(ContactData contactData, boolean creation) {
		type(By.name("firstname"), contactData.getFirstName());
		type(By.name("lastname"), contactData.getLastName());
		
		type(By.name("email"), contactData.getEmail1());
		type(By.name("email2"), contactData.getEmail2());
		type(By.name("email3"), contactData.getEmail3());
		
		type(By.name("address"), contactData.getAddress());

		type(By.name("home"), contactData.getHomePhone());
		type(By.name("mobile"), contactData.getMobilePhone());
		type(By.name("work"), contactData.getWorkPhone());

		if (contactData.getPhoto() != null) {
			attach(By.name("photo"), contactData.getPhoto());
		}
		if (creation) {
			if (contactData.getGroups().size() != 0) {
				Assert.assertTrue(contactData.getGroups().size() == 1);
				new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
			}
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
	
	private void selectGroup(String groupName) {
		wd.findElement(By.xpath("//select[@name='to_group']")).click();
	    new Select(wd.findElement(By.xpath("//select[@name='to_group']"))).selectByVisibleText(groupName);	    
	}
	
	private void selectGroupWhenDeleting(String groupName) {
		wd.findElement(By.name("group")).click();
	    new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
	}

	public void deletSelected() {
		click(By.xpath("//input[@value='Delete']"));
		wd.switchTo().alert().accept();
	}
	
	public void gotoEditFormById(int id) {
		wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
	}
	
	public void gotoDetailsFormById(int id) {
		wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[7]/a", id))).click();
	}
	
	private void goToTheContactListOfThisGroup(String groupName) {
	    wd.findElement(By.linkText("group page \"" + groupName + "\"")).click();
	}

	public void submitContactModification() {
		click(By.xpath("//div[@id='content']/form/input[22]"));
	}

	public void returnToHomePage() {
		click(By.linkText("home page"));
	}
	
	public void addGroup(ContactData contact, GroupData group) {
		selectById(contact.getId());
		selectGroup(group.getName());
		submitOfAddingAContactToTheGroup();
		goToTheContactListOfThisGroup(group.getName());
	}
	
	public void deleteFromGroup(ContactData contact, GroupData group) {
		selectGroupWhenDeleting(group.getName());
		selectById(contact.getId());
		submitDeletion();
		goToTheContactListOfThisGroup(group.getName());
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

	//as if all the fields specified on the main page are filled in
	public ContactData infoFromDetailsForm(ContactData contact) {
		gotoDetailsFormById(contact.getId());
		String allContent = wd.findElement(By.xpath("//div[@id='content']")).getText();
		
		String[] content = allContent.split("\n\n");
		
		String[] names = null;
		String address = null, phoneNumbers = null, emails = null;
		
		if (isElementPresent(By.xpath("//div[@id='content']/img"))) {
			names = content[0].split(" ");
			address = content[1];
			
			phoneNumbers = content[2].replaceAll(" ", "").replaceAll("[-()]", "").replaceAll(":", "").replaceAll("[(A-Z)]", "");
			emails = content[3];
		} else {
			String[] namesAndAddress = content[0].split("\n");
			names = namesAndAddress[0].split(" ");
			address = namesAndAddress[1];
			
			phoneNumbers = content[1].replaceAll(" ", "").replaceAll("[-()]", "").replaceAll(":", "").replaceAll("[(A-Z)]", "");
			emails = content[2];
		}
		
		wd.navigate().back();
		return new ContactData().withId(contact.getId()).withFirstName(names[0]).withLastName(names[1])
				.withAddress(address).withAllEmails(emails).withAllPhones(phoneNumbers);
	}
}
