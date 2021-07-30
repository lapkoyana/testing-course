package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddAContactToAGroupTests extends TestBase{
	@BeforeMethod
	public void ensurePreconditions() {
		Contacts contacts = am.db().contacts();
		Groups groups = am.db().groups();
		if (contacts.size() == 0) {
			am.goTo().home();
			am.goTo().addNew();
			am.contact().create(new ContactData().withFirstName("first name").withLastName("last name"), true);
		}		
		if (groups.size() == 0) {
			am.goTo().groups();
			am.group().create(new GroupData().withName("test1"));
		}
		//go through the list of contacts and check if there are those who are not added to all groups
		int numOfContactsWithIncompleteNumberOfGroups = 0;
		for (ContactData c : contacts) {
			if (c.getGroups().size() < groups.size()) {
				numOfContactsWithIncompleteNumberOfGroups++;
			}
		}
		//if there is no contact that is not added to all groups, then create a new one
		if (numOfContactsWithIncompleteNumberOfGroups == 0) {
			am.goTo().home();
			am.goTo().addNew();
			am.contact().create(new ContactData().withFirstName("FIRST NAME").withLastName("LAST NAME"), true);
		}
	}
	
	@Test
	public void testAddAContactToAGroup() {
		am.goTo().home();
		Contacts before = am.db().contacts();
		Groups groups = am.db().groups();
		
		ContactData contact = null;
		for (ContactData c : before) {
			if (c.getGroups().size() < groups.size()) {
				contact = c;
				break;
			}
		}
		
		Groups tmpGroups = new Groups (groups);
		tmpGroups.removeAll(contact.getGroups());
		GroupData group = tmpGroups.iterator().next();
		
		am.contact().addGroup(contact, group);
		am.goTo().home();
		
		Contacts after = am.db().contacts();
		
		assertThat(after, equalTo(before.withAdded(contact, group)));
	}
}
