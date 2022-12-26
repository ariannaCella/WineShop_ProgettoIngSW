package Actors;

public class Supplier extends User{
	public Supplier(String n, String s, String fc, String e, int p,String a, int adm, String user, String passw) {
		super(n,s,fc,e,p,a,0,user,passw);
	}
	public Supplier() {
		super("","","","",0,"",0,"","");
	}
}