package ru.addressbook.tests;

import org.testng.annotations.*;

import ru.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{
  @Test
  public void testGroupCreation() throws Exception {
	  am.clickToGroups();
	  am.initGroupCreation();
	  am.fillGroupForm(new GroupData("test1", "test2", "test3"));
	  am.submitGroupCreation();
	  am.returnToGroupPage();
	  am.logout();
  }
}
