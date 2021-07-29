package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactPhoneTests extends TestBase{
	@BeforeMethod
	public void ensurePreconditions() {
		if (am.db().contacts().size() == 0) {
			am.goTo().home();
			am.goTo().addNew();
			am.contact().create(new ContactData().withFirstName("first name")
					.withLastName("last name").withMobilePhone("+7(111)").withWorkPhone("22 22 32"), true);
		}
	}
	
	@Test
	public void testContactPhones() {
		am.goTo().home();
		ContactData contact = am.db().contacts().iterator().next();
		ContactData contactInfoFromEditForm = am.contact().infoFromEditForm(contact);
		
		assertThat(mergePhones(contact), equalTo(mergePhones(contactInfoFromEditForm)));
	}
	
	//in fact, I don't need it anymore
	//because I get everything from the database
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
