package ru.addressbook;

import org.testng.annotations.*;

public class AddANewContactTest extends TestBase{
	  @Test
	  public void testAddANewContact() throws Exception {
	    clickToAddNew();
	    fillContactForm(new ContactData("Cvb", "Yui", "cvb@test.com"));
	    submitContactCreation();
	  }
}