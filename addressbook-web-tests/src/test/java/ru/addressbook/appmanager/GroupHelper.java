package ru.addressbook.appmanager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

public class GroupHelper extends HelperBase {

	public GroupHelper(WebDriver wd) {
		super(wd);
	}

	public void returnToGroupPage() {
		click(By.linkText("group page"));
	}

	public void submitCreation() {
		click(By.name("submit"));
		click(By.xpath("//body"));
	}

	public void fillForm(GroupData groupData) {
		type(By.name("group_name"), groupData.getName());
		type(By.name("group_header"), groupData.getHeader());
		type(By.name("group_footer"), groupData.getFooter());
	}

	public void initCreation() {
		click(By.name("new"));
	}

	public void deleteSelected() {
		click(By.name("delete"));
	}
	
	public void selectById(int id) {
		wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
	}

	public void initModification() {
		click(By.name("edit"));
	}

	public void submitModification() {
		click(By.name("update"));
	}

	public void create(GroupData groupData) {
		initCreation();
		fillForm(groupData);
		submitCreation();
		returnToGroupPage();
	}
	
	public void modify(GroupData group) {
		selectById(group.getId());
		initModification();
		fillForm(group);
		submitModification();
		returnToGroupPage();
	}
	
	public void delete(GroupData group) {
		selectById(group.getId());
		deleteSelected();
		returnToGroupPage();
	}

	public boolean isThereAGroup() {
		return isElementPresent(By.name("selected[]"));
	}

	public int count() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public Groups all() {
		Groups groups = new Groups();
		List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
		for (WebElement element : elements) {
			String name = element.getText();
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			GroupData group = new GroupData()
					.withId(id).withName(name);
			groups.add(group);
		}
		return groups;
	}
}
