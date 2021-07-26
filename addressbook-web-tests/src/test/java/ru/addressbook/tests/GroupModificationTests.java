package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		am.goTo().groups();
		if (am.group().all().size() == 0) {
			am.group().create(new GroupData().withName("test1"));
		}
	}
	
	@Test
	public void testGroupModification() {
		Groups before = am.group().all();
		GroupData modifiedGroup = before.iterator().next();
		GroupData group = new GroupData()
				.withId(modifiedGroup.getId()).withName("test1")
				.withHeader("test2").withFooter("test3");
		am.group().modify(group);
		Groups after = am.group().all();
		assertEquals(after.size(), before.size());
		assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
	}
}
