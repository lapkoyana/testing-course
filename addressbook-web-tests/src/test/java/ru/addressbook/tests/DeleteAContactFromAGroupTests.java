package ru.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

public class DeleteAContactFromAGroupTests extends TestBase{
	//it doesn't work yet
	
	@BeforeMethod
	public void ensurePreconditions() {
		boolean isContactInSomeGroup = false;
		Contacts contacts = am.db().contacts();
		Groups groups = am.db().groups();
		if (contacts.size() == 0) {
			//creating a new contact if there are no contacts
			am.goTo().home();
			am.goTo().addNew();
			ContactData contact = new ContactData().withFirstName("first name").withLastName("last name");
			am.contact().create(contact, true);
						
			if (groups.size() == 0) {
				//adding a contact to a new group, because there were no groups
				am.goTo().groups();
				GroupData group = new GroupData().withName("TEST1");
				am.group().create(group);
				
				am.contact().addGroup(contact, group);
				isContactInSomeGroup = true;
			} else {
				//adding a contact to any group
				GroupData group = groups.iterator().next();
				am.contact().addGroup(contact, group);
				isContactInSomeGroup = true;
			}
			contacts = am.db().contacts();
			groups = am.db().groups();
		}
		if (groups.size() == 0) {
			am.goTo().groups();
			GroupData group = new GroupData().withName("TEST1");
			am.group().create(group);
			
			ContactData contact = contacts.iterator().next();
			am.contact().addGroup(contact, group);
			isContactInSomeGroup = true;
		}
		//if there are groups and there are contacts, but no contact is added to any group
		if (!isContactInSomeGroup) {
			int contactHasBeenAddedToSomeGroup = 0;
			for (ContactData c : contacts) {
				if (c.getGroups().size() != 0) {
					contactHasBeenAddedToSomeGroup++;//можем здесь в принципе выходить из цикла, если нашли
				}
			}
			if (contactHasBeenAddedToSomeGroup == 0) {
				ContactData contact = contacts.iterator().next();
				GroupData group = groups.iterator().next();
				
				am.contact().addGroup(contact, group);
			}
		}
	}
	
	@Test
	public void testDeleteAContactFromAGroup() {
		am.goTo().home();
		
		Contacts before = am.db().contacts();
		Groups groups = am.db().groups();
		
		GroupData group = null;
		
		for (GroupData g : groups) {
			if (g.getContacts().size() != 0) {
				group = g;
			}
		}
		
		ContactData contact = group.getContacts().iterator().next();
		
		am.contact().deleteFromGroup(contact, group);
		
		am.goTo().home();
		
		Contacts after = am.db().contacts();
		
		assertThat(after, equalTo(before.withAdded(contact, group)));
	}
}
