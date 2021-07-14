package ru.addressbook.tests;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase{
  @Test
  public void testDeleteGroup() throws Exception {
	  am.getNavigationHelper().clickToGroups();
	  am.getGroupHelper().selectGroup();
	  am.getGroupHelper().deleteSelectedGroups();
	  am.getGroupHelper().returnToGroupPage();
  }
}
