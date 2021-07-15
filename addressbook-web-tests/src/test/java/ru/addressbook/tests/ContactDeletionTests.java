package ru.addressbook.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
	@Test
	public void testContactDeletion() {
		am.getNavigationHelper().gotoHome();
		if (!am.getContactHelper().isThereAContact()) {
			am.getNavigationHelper().gotoAddNew();
			am.getContactHelper().createContact(new ContactData("Cvb", "Yui", "cvb@test.com", "test1"), true);
		}
		List<ContactData> before = am.getContactHelper().getContactList();
		am.getContactHelper().selectContact(before.size() - 1);
		am.getContactHelper().deletSelectedContacts();
		am.getNavigationHelper().gotoHome();
		//�� getContactList ���������� ������-��, ������� �� �������
		//�� ���-����� ����� ����������� ���������� �������� ������ ����, �� � ���
		List<ContactData> after = am.getContactHelper().getContactList();
//		Assert.assertEquals(after, before - 1);
		
		//�� ��� ���� �� ���������, ������ ��� �� 24 ������� ����������
		before.remove(before.size() - 1);
		Assert.assertEquals(before, after);
	}
}
