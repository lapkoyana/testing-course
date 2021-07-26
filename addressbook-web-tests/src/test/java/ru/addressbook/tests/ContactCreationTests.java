package ru.addressbook.tests;

import org.testng.annotations.*;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
	@Test
	public void testAddANewContact() throws Exception {
		am.goTo().home();
		Contacts before = am.contact().all();
		am.goTo().addNew();
		ContactData cd = new ContactData().withFirstName("fn").withLastName("ln").withGroup("test1");
		am.contact().create(cd, true);
		assertThat(am.contact().count(), equalTo(before.size() + 1));	
		Contacts after = am.contact().all();
		assertThat(after, equalTo(before.withAdded(
				cd.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
	}
}