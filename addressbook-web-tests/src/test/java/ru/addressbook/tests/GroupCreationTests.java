package ru.addressbook.tests;

import org.testng.annotations.*;

import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class GroupCreationTests extends TestBase {
	@Test
	public void testGroupCreation() throws Exception {
		am.goTo().groups();
		Groups before = am.group().all();
		GroupData group = new GroupData().withName("test1");
		am.group().create(group);
		Groups after = am.group().all();
		assertEquals(after.size(), before.size() + 1);
		assertThat(after, equalTo(before.withAdded(
				group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
	}
}
