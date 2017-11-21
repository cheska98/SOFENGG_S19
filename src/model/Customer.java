package model;

public class Customer {
	
	public static final String TABLE_NAME = "customers";
	public static final String CUSTOMER_ID = "ID";
	public static final String CUSTOMER_NAME = "Name";
	public static final String CUSTOMER_COMPANY = "Company";
	public static final String CUSTOMER_CONTACT = "Contact";
	
	private int id;
	private String name;
	private String company;
	private String contact;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
}
