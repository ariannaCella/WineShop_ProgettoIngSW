package Actors;

public class Shipper extends User{
	public Shipper(String n, String s, String fc, String e, int p,String a, int adm, String user, String passw) {
		super(n,s,fc,e,p,a,0,user,passw);
	}
	public Shipper() {
		super("","","","",0,"",0,"","");
	}
}