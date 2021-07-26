package ru.addressbook.model;

public class GroupData {
	private int id;
	private String name;
	private String header;
	private String footer;

	public String getName() {
		return name;
	}

	public String getHeader() {
		return header;
	}

	public String getFooter() {
		return footer;
	}

	public int getId() {
		return id;
	}
	
	public GroupData withName (String name) {
		this.name = name;
		return this;
	}
	
	public GroupData withId(int id) {
		this.id = id;
		return this;
	}
	
	public GroupData withFooter(String footer) {
		this.footer = footer;
		return this;
	}
	
	public GroupData withHeader(String header) {
		this.header = header;
		return this;
	}

	@Override
	public String toString() {
		return "GroupData [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		GroupData other = (GroupData) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}