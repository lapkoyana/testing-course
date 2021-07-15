package ru.addressbook.tests;

import org.testng.annotations.*;

import ru.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{
  @Test
  public void testGroupCreation() throws Exception {
	  am.getNavigationHelper().gotoGroups();
	  am.getGroupHelper().initGroupCreation();
	  am.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
	  am.getGroupHelper().submitGroupCreation();
	  am.getGroupHelper().returnToGroupPage();
  }
}
