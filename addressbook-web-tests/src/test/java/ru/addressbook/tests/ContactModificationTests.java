package ru.addressbook.tests;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {
	@Test
	public void testContactModification() {
		am.getNavigationHelper().gotoHome();
		if (!am.getContactHelper().isThereAContact()) {
			am.getNavigationHelper().gotoAddNew();
			am.getContactHelper().createContact(new ContactData("Cvb", "Yui", "cvb@test.com", "test1"), true);
		}
		List<ContactData> before = am.getContactHelper().getContactList();
		am.getContactHelper().gotoEditForm(before.size() - 1);
		ContactData cd = new ContactData("Cvb", "Yui", "cvb@test.com", null, before.get(before.size() - 1).getId()); 
		am.getContactHelper().fillContactForm(cd, false);
		am.getContactHelper().submitContactModification();
		am.getContactHelper().returnToHomePage();
		List<ContactData> after = am.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size());
		
		before.remove(before.size() - 1);
		before.add(cd);
		Comparator<? super ContactData> byId = (g1,g2)->Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(after, before);
	}
}
