package Actors;

public class Employee extends User{
	public Employee(String n, String s, String fc, String e, int p,String a, int adm, String user, String passw) {
		super(n,s,fc,e,p,a,0,user,passw);
	}
	public Employee() {
		super("","","","",0,"",0,"","");
	}
}
