package ru.addressbook.tests;

import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
	@Test
	public void testContactDeletion() {
		am.getNavigationHelper().gotoHome();
		am.getNavigationHelper().gotoAddNew();
		if (!am.getContactHelper().isThereAContact()) {
			am.getContactHelper().createContact(new ContactData("Cvb", "Yui", "cvb@test.com", "test1"), true);
		}
		am.getContactHelper().selectContact();
		am.getContactHelper().deletSelectedContacts();
		am.getNavigationHelper().gotoHome();
	}
}
