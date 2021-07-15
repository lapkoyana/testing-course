package ru.addressbook.model;

public class GroupData {
	private int id;
	private final String name;
	private final String header;
	private final String footer;

	public GroupData(String name, String header, String footer) {
		this.name = name;
		this.header = header;
		this.footer = footer;
		this.id = Integer.MAX_VALUE;
	}
	
	public GroupData(String name, String header, String footer, int id) {
		this.name = name;
		this.header = header;
		this.footer = footer;
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "GroupData [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void setId(int id) {
		this.id = id;
	}
}