package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import ru.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
	@Test
	public void testAddANewContact() throws Exception {
		am.getNavigationHelper().gotoHome();
		int before = am.getContactHelper().getContactCount();
		am.getNavigationHelper().gotoAddNew();
		am.getContactHelper().createContact(new ContactData("Cvb", "Yui", "cvb@test.com", "test1"), true);
		int after = am.getContactHelper().getContactCount();
		Assert.assertEquals(after, before + 1);

	}
}