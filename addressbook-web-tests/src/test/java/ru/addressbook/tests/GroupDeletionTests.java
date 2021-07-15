package ru.addressbook.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;

import ru.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {
	@Test
	public void testDeleteGroup() throws Exception {
		am.getNavigationHelper().gotoGroups();
		if (!am.getGroupHelper().isThereAGroup()) {
			am.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
		}
		List<GroupData> before = am.getGroupHelper().getGroupList();
		am.getGroupHelper().selectGroup(before.size() - 1);
		am.getGroupHelper().deleteSelectedGroups();
		am.getGroupHelper().returnToGroupPage();
		List<GroupData> after = am.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size() - 1);

		before.remove(before.size() - 1);
		Assert.assertEquals(before, after);
	}
}
