package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{
	
	public NavigationHelper(WebDriver wd) {
		super(wd);
	}

	public void clickToGroups() {
		click(By.linkText("groups"));
	}

	public void clickToAddNew() {
		click(By.linkText("add new"));
	}

	public void clickToHome() {
		click(By.linkText("home"));
	}
}
