package ru.addressbook.model;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ForwardingSet;

public class Contacts extends ForwardingSet<ContactData>{

	private Set<ContactData> delegate;
	
	@Override
	protected Set<ContactData> delegate() {
		return delegate;
	}
	
	public Contacts(Contacts contacts) {
		this.delegate = new HashSet<ContactData>(contacts.delegate);
	}
	
	public Contacts() {
		this.delegate = new HashSet<ContactData>();
	}
	
	public Contacts(Collection<ContactData> contacts) {
		this.delegate = new HashSet<ContactData>(contacts);
	}

	public Contacts withAdded(ContactData cd) {
		Contacts contacts = new Contacts(this);
		if (cd.getAddress() == null)
			cd.withAddress("");
		if (cd.getEmail1() == null)
			cd.withEmail1("");
		if (cd.getEmail2() == null)
			cd.withEmail2("");
		if (cd.getEmail3() == null)
			cd.withEmail3("");
		if (cd.getMobilePhone() == null)
			cd.withMobilePhone("");
		if (cd.getHomePhone() == null)
			cd.withHomePhone("");
		if (cd.getWorkPhone() == null)
			cd.withWorkPhone("");
		if (cd.getPhoto() == null)
			cd.withPhoto(new File(""));
		contacts.add(cd);
		return contacts;
	}
	
	public Contacts without(ContactData cd) {
		Contacts contacts = new Contacts(this);
		contacts.remove(cd);
		return contacts;
	}
}
