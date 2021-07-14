package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{
	
	public NavigationHelper(FirefoxDriver wd) {
		super(wd);
	}

	public void clickToGroups() {
		click(By.linkText("groups"));
	}

	public void clickToAddNew() {
		click(By.linkText("add new"));
	}
}
