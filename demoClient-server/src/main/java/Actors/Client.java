package Actors;

import java.io.Serializable;

public class Client extends User  {
	public Client(String n, String s, String fc, String e, int p,  String a, int adm, String user, String passw) {
		super(n,s,fc,e,p,a,adm,user,passw);
	}

	public Client (String n, String s, String fc, String e, int p,  String a, String user){
		super(n,s,fc,e,p,a,user);
	}
	public Client() {
		super("","","","",0,"",0,"","");
	}
}