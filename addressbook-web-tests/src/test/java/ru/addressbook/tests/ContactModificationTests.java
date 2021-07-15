package ru.addressbook.tests;

import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
	@Test
	public void testContactModification() {
		am.getNavigationHelper().gotoHome();
		am.getNavigationHelper().gotoAddNew();
		if (!am.getContactHelper().isThereAContact()) {
			am.getContactHelper().createContact(new ContactData("Cvb", "Yui", "cvb@test.com", "test1"), true);
		}
		am.getContactHelper().gotoEditForm();
		am.getContactHelper().fillContactForm(new ContactData("Cvb", "Yui", "cvb@test.com", null), false);
		am.getContactHelper().submitContactModification();
		am.getContactHelper().returnToHomePage();
	}
}
