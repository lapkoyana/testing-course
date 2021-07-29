package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		if (am.db().groups().size() == 0) {
			am.goTo().groups();
			am.group().create(new GroupData().withName("test1"));
		}
	}
	
	@Test
	public void testGroupModification() {
		Groups before = am.db().groups();
		GroupData modifiedGroup = before.iterator().next();
		GroupData group = new GroupData()
				.withId(modifiedGroup.getId()).withName("test1")
				.withHeader("test2").withFooter("test3");
		am.goTo().groups();
		am.group().modify(group);
		assertThat(am.group().count(), equalTo(before.size()));
		Groups after = am.db().groups();
		assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
	}
}
