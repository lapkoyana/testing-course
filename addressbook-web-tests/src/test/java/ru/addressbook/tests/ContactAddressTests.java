package ru.addressbook.tests;

import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
	@Test
	public void testContactAddress() {
		am.goTo().home();
		ContactData contact = am.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = am.contact().infoFromEditForm(contact);
		
		assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
	}
}