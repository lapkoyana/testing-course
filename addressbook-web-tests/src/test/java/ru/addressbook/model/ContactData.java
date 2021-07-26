package ru.addressbook.model;

public class ContactData {
	private int id;
	private String firstName;
	private String lastName;
	
	private String email1;
	private String email2;
	private String email3;
	private String allEmails;
	
	private String group;
	
	private String homePhone;
	private String mobilePhone;
	private String workPhone;
	private String allPhones;
	
	private String address;
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail1() {
		return email1;
	}
	public String getEmail2() {
		return email2;
	}
	public String getEmail3() {
		return email3;
	}

	public String getGroup() {
		return group;
	}
	
	public String getAllEmails() {
		return allEmails;
	}

	public int getId() {
		return id;
	}

	public String getAllPhones() {
		return allPhones;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public String getAddress() {
		return address;
	}

	public ContactData withId(int id) {
		this.id = id;
		return this;
	}
	
	public ContactData withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public ContactData withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public ContactData withEmail1(String email1) {
		this.email1 = email1;
		return this;
	}
	
	public ContactData withEmail2(String email2) {
		this.email2 = email2;
		return this;
	}
	
	public ContactData withEmail3(String email3) {
		this.email3 = email3;
		return this;
	}
	
	public ContactData withGroup(String group) {
		this.group = group;
		return this;
	}
	
	public ContactData withHomePhone(String homePhone) {
		this.homePhone = homePhone;
		return this;
	}
	
	public ContactData withWorkPhone(String workPhone) {
		this.workPhone = workPhone;
		return this;
	}

	public ContactData withMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public ContactData withAllEmails(String allEmails) {
		this.allEmails = allEmails;
		return this;
	}
	
	public ContactData withAddress(String address) {
		this.address = address;
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactData other = (ContactData) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContactData [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

	public ContactData withAllPhones(String allPhones) {
		this.allPhones = allPhones;
		return this;
	}
}