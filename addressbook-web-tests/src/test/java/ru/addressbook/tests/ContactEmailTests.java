package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactEmailTests extends TestBase{
	
	@BeforeMethod
	public void ensurePreconditions() {
		if (am.db().contacts().size() == 0) {
			am.goTo().home();
			am.goTo().addNew();
			am.contact().create(new ContactData().withFirstName("first name")
					.withLastName("last name").withEmail1("email1").withEmail3("email3"), true);
		}
	}
	
	@Test
	public void testContactEmails() {
		am.goTo().home();
		ContactData contact = am.db().contacts().iterator().next();
		ContactData contactInfoFromEditForm = am.contact().infoFromEditForm(contact);
		
		assertThat(mergeEmails(contact), equalTo(mergeEmails(contactInfoFromEditForm)));
		
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
