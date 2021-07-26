package ru.addressbook.tests;

import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactPhoneTests extends TestBase{
	@Test
	public void testContactPhones() {
		am.goTo().home();
		ContactData contact = am.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = am.contact().infoFromEditForm(contact);
		
		assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
	}
	
	private String mergePhones(ContactData contact) {
		return Arrays.asList(contact.getHomePhone(),
				contact.getMobilePhone(),
				contact.getWorkPhone())
			.stream()
			.filter((s) -> !s.equals(""))
			.map(ContactPhoneTests::cleaned)
			.collect(Collectors.joining("\n"));
	}

	public static String cleaned(String phone) {
		return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
	}
}
