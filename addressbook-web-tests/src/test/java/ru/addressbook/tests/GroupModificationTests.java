package ru.addressbook.tests;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {
	@Test
	public void testGroupModification() {
		am.getNavigationHelper().gotoGroups();
		if (!am.getGroupHelper().isThereAGroup()) {
			am.getGroupHelper().createGroup(new GroupData("test1", null, null));
		}
		List<GroupData> before = am.getGroupHelper().getGroupList();
		am.getGroupHelper().selectGroup(before.size() - 1);
		am.getGroupHelper().initGroupModification();
		GroupData group = new GroupData("test1", "test2", "test3", before.get(before.size() - 1).getId());
		am.getGroupHelper().fillGroupForm(group);
		am.getGroupHelper().submitGroupModification();
		am.getGroupHelper().returnToGroupPage();
		List<GroupData> after = am.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size());
		
		before.remove(before.size() - 1);
		before.add(group);
		Comparator<? super GroupData> byId = (g1,g2)->Integer.compare(g1.getId(), g2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(after, before);
	}
}
