package com.example.mobilecontact;

public class Contact 
{
	 
    //private variables
    int _id;
    String first_name;
    String email_id;
    String phone_id;
    
 
    // Empty constructor
    public Contact()
    {
 
    }
    
    // constructor
    public Contact(int id, String firstname, String email, String phone)
    {
        this._id = id;
        this.first_name = firstname;
        this.email_id = email;
        this.phone_id = phone;
    }
 
    // constructor
    public Contact(String firstname, String email, String phone)
    {
        this.first_name = firstname;
        this.email_id = email;
        this.phone_id = phone;
    }

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPhone_id() {
		return phone_id;
	}

	public void setPhone_id(String phone_id) {
		this.phone_id = phone_id;
	}
	
}