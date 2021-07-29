package ru.addressbook.tests;

import org.testng.annotations.*;

import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		if (am.db().groups().size() == 0) {
			am.goTo().groups();
			am.group().create(new GroupData().withName("test1"));
		}
	}
	
	@Test
	public void testDeleteGroup() throws Exception {
		Groups before = am.db().groups();
		am.goTo().groups();
		GroupData deleteGroup = before.iterator().next();
		am.group().delete(deleteGroup);
		assertThat(am.group().count(), equalTo(before.size() - 1));
		Groups after = am.db().groups();
		assertThat(after, equalTo(before.without(deleteGroup)));
	}
}
