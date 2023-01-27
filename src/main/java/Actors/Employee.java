package Actors;

public class Employee extends User{
	public Employee(String n, String s, String fc, String e, int p,String a, int adm, String user, String passw) {
		super(n,s,fc,e,p,a,adm,user,passw);
	}
	public Employee (String n, String s, String fc, String e, int p,  String a, String user){
		super(n,s,fc,e,p,a,user);
	}
	public Employee() {
		super("","","","",0,"",0,"","");
	}

	public Employee(String name, String surname, String fiscalCode, String email, int phone, String address, int adm, String username) {
		super(name,surname,fiscalCode,email,phone,address,adm,username);
	}
}
