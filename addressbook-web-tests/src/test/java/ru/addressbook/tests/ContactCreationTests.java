package ru.addressbook.tests;

import org.testng.annotations.*;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {
	@Test
	public void testAddANewContact() throws Exception {
		am.goTo().home();
		Contacts before = am.contact().all();
		am.goTo().addNew();
		ContactData cd = new ContactData().withFirstName("fn").withLastName("ln").withEmail("email")
				.withGroup("test1");
		am.contact().create(cd, true);
		Contacts after = am.contact().all();
		assertEquals(after.size(), before.size() + 1);	
		assertThat(after, equalTo(before.withAdded(
				cd.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
	}
}