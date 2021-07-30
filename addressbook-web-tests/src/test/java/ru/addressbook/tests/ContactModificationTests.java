package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		if (am.db().contacts().size() == 0) {
			am.goTo().home();
			am.goTo().addNew();
			am.contact().create(new ContactData().withFirstName("first name").withLastName("last name"), true);
		}
	}
	
	@Test
	public void testContactModification() {
		Contacts before = am.db().contacts();
		ContactData modifiedContact = before.iterator().next();
		ContactData cd = new ContactData().withId(modifiedContact.getId())
				.withFirstName("first имя").withLastName("last имя");
		am.contact().modify(cd);
		am.goTo().home();
		assertThat(am.contact().count(), equalTo(before.size()));
		Contacts after = am.db().contacts();
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(cd)));
	}
}
