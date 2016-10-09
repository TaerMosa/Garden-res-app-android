package Logic;

import android.widget.CheckBox;

public class First {

	private String name;  // meal name
	private String description;  // meal des
	private String dob; // meal price
	

	public First() {
		// TODO Auto-generated constructor stub
	}
	
//  ----------constructor 
	public First(String name, String description, String dob ,CheckBox box) {
		super();
		this.name = name;
		this.description = description;
		this.dob = dob;
		
	
	}

//-------------getters \ setters ---------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
}
