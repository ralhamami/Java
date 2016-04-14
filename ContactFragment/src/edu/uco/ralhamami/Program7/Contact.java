package edu.uco.ralhamami.Program7;

import java.util.Comparator;

public class Contact implements Comparable<Contact>{
	private String lName;
	private String fName;
	private String phoneNum;
	private String email;
	private String web;
	
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	
	public static Comparator<Contact> ContactLastNameComparator 
    = new Comparator<Contact>() {

public int compare(Contact contact1, Contact contact2) {

String contactlName1 = contact1.getlName().toUpperCase();
String contactlName2 = contact2.getlName().toUpperCase();

//ascending order
return contactlName1.compareTo(contactlName2);

//descending order
//return fruitName2.compareTo(fruitName1);
}

};

	@Override
	public int compareTo(Contact arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
