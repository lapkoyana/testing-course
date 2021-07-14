package ru.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase{
  @Test
  public void testGroupCreation() throws Exception {
	clickToGroups();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();
    logout();
  }
}
