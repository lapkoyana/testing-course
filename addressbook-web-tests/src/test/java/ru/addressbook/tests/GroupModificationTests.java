package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {
	@Test
	public void testGroupModification() {
		am.getNavigationHelper().gotoGroups();
		int before = am.getGroupHelper().getGroupCount();
		if (!am.getGroupHelper().isThereAGroup()) {
			am.getGroupHelper().createGroup(new GroupData("test1", null, null));
		}
		am.getGroupHelper().selectGroup(before - 1);
		am.getGroupHelper().initGroupModification();
		am.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
		am.getGroupHelper().submitGroupModification();
		am.getGroupHelper().returnToGroupPage();
		int after = am.getGroupHelper().getGroupCount();
		Assert.assertEquals(after, before);
	}
}
