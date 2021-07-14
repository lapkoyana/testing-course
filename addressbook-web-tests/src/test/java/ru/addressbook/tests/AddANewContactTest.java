package ru.addressbook.tests;

import org.testng.annotations.*;

import ru.addressbook.model.ContactData;

public class AddANewContactTest extends TestBase{
	  @Test
	  public void testAddANewContact() throws Exception {
		  am.clickToAddNew();
		  am.fillContactForm(new ContactData("Cvb", "Yui", "cvb@test.com"));
		  am.submitContactCreation();
	  }
}