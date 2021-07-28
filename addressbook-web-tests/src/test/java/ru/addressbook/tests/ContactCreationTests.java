package ru.addressbook.tests;

import org.testng.annotations.*;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ContactCreationTests extends TestBase {
	
	@DataProvider
	public Iterator<Object[]> validContacts() throws IOException{
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
			String json = "";
			String line = reader.readLine();
			while (line != null) {
				json += line;
				line = reader.readLine();
			}
			Gson gson = new Gson();
			//I'll pretend that it works for me
			List<ContactData> contacts = gson.fromJson(json, new TypeToken<ArrayList<ContactData>>(){}.getType());
			return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
		}
	}
	
	@Test(dataProvider = "validContacts")
	public void testAddANewContact(ContactData cd) throws Exception {
		am.goTo().home();
		Contacts before = am.contact().all();
		am.goTo().addNew();
		am.contact().create(cd, true);
		assertThat(am.contact().count(), equalTo(before.size() + 1));	
		Contacts after = am.contact().all();
		assertThat(after, equalTo(before.withAdded(
				cd.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
	}
}