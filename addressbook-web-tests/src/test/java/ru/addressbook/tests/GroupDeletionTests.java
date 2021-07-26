package ru.addressbook.tests;

import org.testng.annotations.*;

import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {
	@BeforeMethod
	public void ensurePreconditions() {
		am.goTo().groups();
		if (am.group().all().size() == 0) {
			am.group().create(new GroupData().withName("test1"));
		}
	}
	
	@Test
	public void testDeleteGroup() throws Exception {
		Groups before = am.group().all();
		GroupData deleteGroup = before.iterator().next();
		am.group().delete(deleteGroup);
		Groups after = am.group().all();
		assertEquals(after.size(), before.size() - 1);
		assertThat(after, equalTo(before.without(deleteGroup)));
	}
}
