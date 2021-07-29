package ru.addressbook.model;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "addressbook")
public class ContactData {
	@Id
	@Column(name = "id")
	private int id;
	
	@Expose
	@Column(name = "firstname")
	private String firstName;
	
	@Expose
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "email")
	@Type(type = "text")
	private String email1;
	
	@Column(name = "email2")
	@Type(type = "text")
	private String email2;
	
	@Column(name = "email3")
	@Type(type = "text")
	private String email3;
	
	@Transient
	private String allEmails;
	
	@Transient
	private String group;
	
	@Column(name = "home")
	@Type(type = "text")
	private String homePhone;
	
	@Column(name = "mobile")
	@Type(type = "text")
	private String mobilePhone;
	
	@Column(name = "work")
	@Type(type = "text")
	private String workPhone;
	
	@Transient
	private String allPhones;
	
	@Column(name = "address")
	@Type(type = "text")
	private String address;
	
	@Column(name = "photo")
	@Type(type = "text")
	private String photo;
	
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

	public File getPhoto() {
		if (photo != null) {
			return new File(photo);
		}
		else return null;
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

	public ContactData withPhoto(File photo) {
		this.photo = photo.getPath();
		return this;
	}
	
	public ContactData withAllPhones(String allPhones) {
		this.allPhones = allPhones;
		return this;
	}

	@Override
	public String toString() {
		return "ContactData [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email1=" + email1
				+ ", email2=" + email2 + ", email3=" + email3 + ", group=" + group + ", homePhone=" + homePhone
				+ ", mobilePhone=" + mobilePhone + ", workPhone=" + workPhone + ", address=" + address + ", photo="
				+ photo + "]\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email1 == null) ? 0 : email1.hashCode());
		result = prime * result + ((email2 == null) ? 0 : email2.hashCode());
		result = prime * result + ((email3 == null) ? 0 : email3.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((homePhone == null) ? 0 : homePhone.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		result = prime * result + ((workPhone == null) ? 0 : workPhone.hashCode());
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
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email1 == null) {
			if (other.email1 != null)
				return false;
		} else if (!email1.equals(other.email1))
			return false;
		if (email2 == null) {
			if (other.email2 != null)
				return false;
		} else if (!email2.equals(other.email2))
			return false;
		if (email3 == null) {
			if (other.email3 != null)
				return false;
		} else if (!email3.equals(other.email3))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (homePhone == null) {
			if (other.homePhone != null)
				return false;
		} else if (!homePhone.equals(other.homePhone))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobilePhone == null) {
			if (other.mobilePhone != null)
				return false;
		} else if (!mobilePhone.equals(other.mobilePhone))
			return false;
		if (workPhone == null) {
			if (other.workPhone != null)
				return false;
		} else if (!workPhone.equals(other.workPhone))
			return false;
		return true;
	}
}