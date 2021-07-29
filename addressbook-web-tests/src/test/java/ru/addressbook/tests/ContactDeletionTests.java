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
		if (am.db().contacts().size() == 0) {
			am.goTo().home();
			am.goTo().addNew();
			am.contact().create(new ContactData().withFirstName("first name")
					.withLastName("last name"), true);
		}
	}
	
	@Test
	public void testContactDeletion() {
		//for me, this test only works in debug mode
		Contacts before = am.db().contacts();
		ContactData cd = before.iterator().next();
		am.contact().delete(cd);
		am.goTo().home();
		assertThat(am.contact().count(), equalTo(before.size() - 1));
		Contacts after = am.db().contacts();
		assertThat(after, equalTo(before.without(cd)));
	}
}
