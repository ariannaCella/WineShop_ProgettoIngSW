package Actors;

import java.io.Serializable;

public abstract class User implements Serializable {
	private String name,surname,fiscalCode,address,email, username, password;
	private int phone;
	private int admin;
	
	public User(String n, String s, String fc, String e, int p, String a, String user) {
		this.name=n;
		this.surname=s;
		this.fiscalCode=fc;
		this.address=a;
		this.email=e;
		this.phone=p;
		this.admin=0;
		this.username=user;
		this.password="";
	}
	public User(String n, String s, String fc,  String e, int p,String a, int adm, String user, String passw) {
		this.name=n;
		this.surname=s;
		this.fiscalCode=fc;
		this.address=a;
		this.email=e;
		this.phone=p;
		this.admin=adm;
		this.username=user;
		this.password=passw;
	}
	public String infoUser(){
		return name +" "+ surname +", \nCodice Fiscale:"+fiscalCode+"\nIndirizzo: "+address+",  Cell:"+phone+"\n";
	}
	public String infoLogin(){
		return name+ " "+ surname+"\nusername: "+username+"\npassword: "+password+"\n";
	}
	public  void setName(String n) {
		this.name=n;
	}
	public void setSurname(String s) {
		this.surname=s;
	}
	public void setFiscalCode(String c) {
		this.fiscalCode=c;
	}
	public void setAddress(String i) {
		this.address=i;
	}
	public  void setEmail(String e) {
		this.email=e;
	}
	public void setPhone(int p) {
		this.phone=p;
	}
	public void setUsername(String username) {this.username = username;}
	public void setPassword(String password) {this.password = password;}
	public void setAdmin(int admin) {this.admin = admin;}

	public String getName() {
		return name;
	}
	public String getSurname() {
		return this.surname;
	}
	public String getFiscalCode() {
		return this.fiscalCode;
	}
	public String getAddress() {
		return this.address;
	}
	public  String getEmail() {
		return this.email;
	}
	public int getPhone() {
		return this.phone;
	}
	public String getUsername() {return username;}
	public String getPassword() {return password;}
	public int getAdmin() {return admin;}

}
