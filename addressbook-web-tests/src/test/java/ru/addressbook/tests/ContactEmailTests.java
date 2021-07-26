package ru.addressbook.tests;

import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactEmailTests extends TestBase{
	@Test
	public void testContactEmails() {
		am.goTo().home();
		ContactData contact = am.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = am.contact().infoFromEditForm(contact);
		
		assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
		
	}

	private Object mergeEmails(ContactData contact) {
		return Arrays.asList(contact.getEmail1(),
				contact.getEmail2(),
				contact.getEmail3())
			.stream()
			.filter((s) -> !s.equals(""))
			.collect(Collectors.joining("\n"));
	}
}
