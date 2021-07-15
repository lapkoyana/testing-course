package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
	@Test
	public void testContactModification() {
		am.getNavigationHelper().gotoHome();
		int before = am.getContactHelper().getContactCount();
		if (!am.getContactHelper().isThereAContact()) {
			am.getNavigationHelper().gotoAddNew();
			am.getContactHelper().createContact(new ContactData("Cvb", "Yui", "cvb@test.com", "test1"), true);
			before = am.getContactHelper().getContactCount();
		}
		am.getContactHelper().gotoEditForm();
		am.getContactHelper().fillContactForm(new ContactData("Cvb", "Yui", "cvb@test.com", null), false);
		am.getContactHelper().submitContactModification();
		am.getContactHelper().returnToHomePage();
		int after = am.getContactHelper().getContactCount();
		Assert.assertEquals(after, before);
	}
}
