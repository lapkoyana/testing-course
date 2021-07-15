package ru.addressbook.tests;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {
	@Test
	public void testAddANewContact() throws Exception {
		am.getNavigationHelper().gotoHome();
		List<ContactData> before = am.getContactHelper().getContactList();
		am.getNavigationHelper().gotoAddNew();
		ContactData cd = new ContactData("Cvb", "Yui", "cvb@test.com", "test1");
		am.getContactHelper().createContact(cd, true);
		List<ContactData> after = am.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() + 1);
		
		before.add(cd);
		Comparator<? super ContactData> byId = (g1,g2)->Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(after, before);
		
//		cd.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
//		before.add(cd);
	}
}