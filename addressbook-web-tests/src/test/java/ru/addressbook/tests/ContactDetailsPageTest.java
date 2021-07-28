package ru.addressbook.tests;

import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsPageTest extends TestBase{
	@Test
	public void testContactDetails() {
		//it works only if the fields from the main page are filled in
		//and takes into account the image
		am.goTo().home();
		ContactData contact = am.contact().all().iterator().next();
		ContactData contactInfoFromDetailsForm = am.contact().infoFromDetailsForm(contact);
		
		assertThat(contact.getFirstName(), equalTo(contactInfoFromDetailsForm.getFirstName()));
		assertThat(contact.getLastName(), equalTo(contactInfoFromDetailsForm.getLastName()));
		assertThat(contact.getAddress(), equalTo(contactInfoFromDetailsForm.getAddress()));
		assertThat(contact.getAllEmails(), equalTo(contactInfoFromDetailsForm.getAllEmails()));
		assertThat(contact.getAllPhones(), equalTo(contactInfoFromDetailsForm.getAllPhones()));
	}
}
