package ru.addressbook.tests;

import org.testng.annotations.*;

import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
	@Test
	public void testGroupCreation() throws Exception {
		am.goTo().groups();
		Groups before = am.group().all();
		GroupData group = new GroupData().withName("test1");
		am.group().create(group);
		assertThat(am.group().count(), equalTo(before.size() + 1));
		Groups after = am.group().all();
		assertThat(after, equalTo(before.withAdded(
				group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
	}
	
	@Test
	public void testBadGroupCreation() throws Exception {
		am.goTo().groups();
		Groups before = am.group().all();
		GroupData group = new GroupData().withName("test1'");
		am.group().create(group);
		assertThat(am.group().count(), equalTo(before.size()));
		Groups after = am.group().all();
		assertThat(after, equalTo(before));
	}
}
