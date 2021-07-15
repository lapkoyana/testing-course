package ru.addressbook.tests;

import org.testng.annotations.*;

import ru.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {
	@Test
	public void testDeleteGroup() throws Exception {
		am.getNavigationHelper().gotoGroups();
		if (!am.getGroupHelper().isThereAGroup()) {
			am.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
		}
		am.getGroupHelper().selectGroup();
		am.getGroupHelper().deleteSelectedGroups();
		am.getGroupHelper().returnToGroupPage();
	}
}
