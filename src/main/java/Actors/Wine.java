package Actors;

import com.example.democlientserver.ModelDBMS;

import java.io.Serializable;

public class Wine implements Serializable {
	String name,producer,origin,notes,vines,img, fcSupplier;
	int year,wineId, quantity;
	int sales;
	int quality; //from one to ten stars
	double price;



	public Wine(int wi, String n, String p, String o, String note, String v, int y, int nsale, int qn, int ql, double pr, String i, String fcSupplier) {
		this.wineId=wi;
		this.name=n;
		this.producer=p;
		this.origin=o;
		this.notes=note;
		this.vines=v;
		this.year=y;
		this.sales=nsale;
		this.quantity=qn;
		this.quality=ql;
		this.price= pr;
		this.img=i;
		this.fcSupplier=fcSupplier;
	}
	public Wine(int wi, String n, String p, String o, String note, String v, int y, int nsale, int qn, int ql, double pr, String i) {
		this.wineId=wi;
		this.name=n;
		this.producer=p;
		this.origin=o;
		this.notes=note;
		this.vines=v;
		this.year=y;
		this.sales=nsale;
		this.quantity=qn;
		this.quality=ql;
		this.price= pr;
		this.img=i;
		this.fcSupplier="";
	}

    public Wine() {
		this.wineId=0;
		this.name=null;
		this.producer=null;
		this.origin=null;
		this.notes=null;
		this.vines=null;
		this.year=0;
		this.sales=0;
		this.quantity=0;
		this.quality=0;
		this.price= 0;
		this.img=null;
		this.fcSupplier="";
    }

    public void updatePrice() { // ci sarà da chiamare quando verrà venduto il vino
		this.price+=sales*0.50;
	}

	public void updateQuality() { // ci sarà da chiamare quando verrà venduto il vino
		this.quality+=sales*0.05;
	}
	public void updateQuantity() { // ci sarà da chiamare quando verrà venduto il vino

		this.quantity-=sales;

	}

	public void addSales(int nsales) { //verrà chiamata quando vendo vino prima updatePrice
		this.sales=this.sales+nsales;
	}
	public String infoWine(){
		return name +", "+ producer +", origine:"+origin+"\nNote:"+notes+"\nVitigni:"+vines+", anno di produzione:"+year+"\nQualità:"+quality+"\nPrezzo di vendita:"+price+"$";
	}
	public String getImgWine(){
		return img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFcSupplier() {
		return fcSupplier;
	}

	public void setFcSupplier(String fcSupplier) {
		this.fcSupplier = fcSupplier;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getVines() {
		return vines;
	}

	public void setVines(String vines) {
		this.vines = vines;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getWineId() {
		return wineId;
	}

	public void setWineId(int wineId) {
		this.wineId = wineId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int nSales) {
		this.sales = nSales;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

