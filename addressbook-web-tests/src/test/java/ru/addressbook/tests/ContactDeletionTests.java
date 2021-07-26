package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		am.goTo().home();
		if (am.contact().all().size() == 0) {
			am.goTo().addNew();
			am.contact().create(new ContactData().withFirstName("first name")
					.withLastName("last name").withGroup("test1"), true);
		}
	}
	
	@Test
	public void testContactDeletion() {
		Contacts before = am.contact().all();
		ContactData cd = before.iterator().next();
		am.contact().delete(cd);
		am.goTo().home();
		assertThat(am.contact().count(), equalTo(before.size() - 1));
		Contacts after = am.contact().all();
		assertThat(after, equalTo(before.without(cd)));
	}
}
