package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import ru.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {
	@Test
	public void testGroupCreation() throws Exception {
		am.getNavigationHelper().gotoGroups();
		int before = am.getGroupHelper().getGroupCount();
		am.getGroupHelper().createGroup(new GroupData("test1", null, null));
		int after = am.getGroupHelper().getGroupCount();
		Assert.assertEquals(after, before + 1);
	}
}
