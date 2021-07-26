package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		am.goTo().home();
		if (am.contact().all().size() == 0) {
			am.goTo().addNew();
			am.contact().create(new ContactData().withFirstName("first name").withLastName("last name")
					.withEmail("myEmail").withGroup("test1"), true);
		}
	}
	
	@Test
	public void testContactDeletion() {
		Contacts before = am.contact().all();
		ContactData cd = before.iterator().next();
		am.contact().delete(cd);
		am.goTo().home();

		Contacts after = am.contact().all();
		assertEquals(after.size(), before.size() - 1);
		assertThat(after, equalTo(before.without(cd)));
	}
}
