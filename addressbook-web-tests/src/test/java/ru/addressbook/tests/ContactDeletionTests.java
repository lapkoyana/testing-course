package ru.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
	@Test
	public void testContactDeletion() {
		am.getNavigationHelper().gotoHome();
		am.getContactHelper().selectContact();
		am.getContactHelper().deletSelectedContacts();
		am.getNavigationHelper().gotoHome();
	}
}
