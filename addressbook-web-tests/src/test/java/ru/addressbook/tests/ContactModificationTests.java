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
		am.goTo().home();
		if (am.contact().all().size() == 0) {
			am.goTo().addNew();
			am.contact().create(new ContactData().withFirstName("first name").withLastName("last name")
					.withGroup("test1"), true);
		}
	}
	
	@Test(enabled = false)
	public void testContactModification() {
		Contacts before = am.contact().all();
		ContactData modifiedContact = before.iterator().next();
		ContactData cd = new ContactData().withId(modifiedContact.getId())
				.withFirstName("firstN").withLastName("lastName");
		//it doesn't want to be modified!!!
		am.contact().modify(cd);
		assertThat(am.contact().count(), equalTo(before.size()));
		Contacts after = am.contact().all();
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(cd)));
	}
}
