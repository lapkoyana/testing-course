package ru.addressbook.tests;

import org.testng.annotations.Test;

import ru.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{
	@Test
	public void testGroupModification() {
		am.getNavigationHelper().gotoGroups();
		am.getGroupHelper().selectGroup();
		am.getGroupHelper().initGroupModification();
		am.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
		am.getGroupHelper().submitGroupModification();
		am.getGroupHelper().returnToGroupPage();
	}
}
