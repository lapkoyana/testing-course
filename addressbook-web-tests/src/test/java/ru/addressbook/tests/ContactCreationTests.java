package ru.addressbook.tests;

import org.testng.annotations.*;

import ru.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
	@Test
	public void testAddANewContact() throws Exception {
		am.getNavigationHelper().gotoHome();
		am.getNavigationHelper().gotoAddNew();
		am.getContactHelper().fillContactForm(new ContactData("Cvb", "Yui", "cvb@test.com", "test1"), true);
		am.getContactHelper().submitContactCreation();
	}
}