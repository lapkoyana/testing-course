package ru.addressbook.tests;

import org.testng.annotations.*;

public class DeleteGroupTest extends TestBase{
  @Test
  public void testDeleteGroup() throws Exception {
	  am.clickToGroups();
	  am.selectGroup();
	  am.deleteSelectedGroups();
	  am.returnToGroupPage();
  }
}
