package ru.addressbook.tests;

import org.testng.annotations.*;

import ru.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{
	  @Test
	  public void testAddANewContact() throws Exception {
		  am.getNavigationHelper().clickToHome();
		  am.getNavigationHelper().clickToAddNew();
		  am.getContactHelper().fillContactForm(new ContactData("Cvb", "Yui", "cvb@test.com"));
		  am.getContactHelper().submitContactCreation();
	  }
}