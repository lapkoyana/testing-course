package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
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
	public void testContactModification() {
		Contacts before = am.contact().all();
		ContactData modifiedContact = before.iterator().next();
		ContactData cd = new ContactData().withId(modifiedContact.getId())
				.withFirstName("firstN").withLastName("lastName").withEmail("e m a i l");
		//it doesn't want to be modified!!!
		am.contact().modify(cd);
		Contacts after = am.contact().all();
		assertEquals(after.size(), before.size());
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(cd)));
	}
}
