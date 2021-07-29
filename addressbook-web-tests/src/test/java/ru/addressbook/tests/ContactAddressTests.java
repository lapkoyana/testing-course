package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
	@BeforeMethod
	public void ensurePreconditions() {
		if (am.db().contacts().size() == 0) {
			am.goTo().home();
			am.goTo().addNew();
			am.contact().create(new ContactData().withFirstName("first name")
					.withLastName("last name").withAddress("address"), true);
		}
	}
	
	@Test
	public void testContactAddress() {
		am.goTo().home();
		ContactData contact = am.db().contacts().iterator().next();
		ContactData contactInfoFromEditForm = am.contact().infoFromEditForm(contact);
		
		assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
	}
}