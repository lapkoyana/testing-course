package ru.addressbook.model;

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
	
	public Contacts withAdded(ContactData cd) {
		Contacts contacts = new Contacts(this);
		contacts.add(cd);
		return contacts;
	}
	
	public Contacts without(ContactData cd) {
		Contacts contacts = new Contacts(this);
		contacts.remove(cd);
		return contacts;
	}
}
