package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
	@Test
	public void testContactDeletion() {
		am.getNavigationHelper().gotoHome();
		int before = am.getContactHelper().getContactCount();
		if (!am.getContactHelper().isThereAContact()) {
			am.getNavigationHelper().gotoAddNew();
			am.getContactHelper().createContact(new ContactData("Cvb", "Yui", "cvb@test.com", "test1"), true);
			before = am.getContactHelper().getContactCount();
		}
		am.getContactHelper().selectContact();
		am.getContactHelper().deletSelectedContacts();
		am.getNavigationHelper().gotoHome();
		int after = am.getContactHelper().getContactCount();
		Assert.assertEquals(after, before - 1);
	}
}
