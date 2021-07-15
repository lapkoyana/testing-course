package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import ru.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {
	@Test
	public void testDeleteGroup() throws Exception {
		am.getNavigationHelper().gotoGroups();
		int before = am.getGroupHelper().getGroupCount();
		if (!am.getGroupHelper().isThereAGroup()) {
			am.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
		}
		am.getGroupHelper().selectGroup(before - 1);
		am.getGroupHelper().deleteSelectedGroups();
		am.getGroupHelper().returnToGroupPage();
		int after = am.getGroupHelper().getGroupCount();
		Assert.assertEquals(after, before - 1);
	}
}
