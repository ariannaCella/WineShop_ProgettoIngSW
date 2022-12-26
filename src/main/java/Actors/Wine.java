package Actors;

import com.example.democlientserver.ModelDBMS;

import java.io.Serializable;

public class Wine implements Serializable {
	String name,producer,origin,notes,vines,img;
	int year,wineId, quantity;
	int nSales=0;
	int quality; //from one to ten stars
	double price;



	public Wine(int wi, String n, String p, String o, String note, String v, int y, int nsale, int qn, int ql, double pr, String i) {
		this.wineId=wi;
		this.name=n;
		this.producer=p;
		this.origin=o;
		this.notes=note;
		this.vines=v;
		this.year=y;
		this.nSales=nsale;
		this.quantity=qn;
		this.quality=ql;
		this.price= pr;
		this.img=i;
	}

    public Wine() {
		this.wineId=0;
		this.name=null;
		this.producer=null;
		this.origin=null;
		this.notes=null;
		this.vines=null;
		this.year=0;
		this.nSales=0;
		this.quantity=0;
		this.quality=0;
		this.price= 0;
		this.img=null;
    }

    public void updatePrice() { // ci sarà da chiamare quando verrà venduto il vino
		this.price+=nSales*0.50;
	}

	public void updateQuality() { // ci sarà da chiamare quando verrà venduto il vino
		this.quality+=nSales*0.05;
	}
	public void updateQuantity() { // ci sarà da chiamare quando verrà venduto il vino

		this.quantity-=nSales;

	}

	public void addSales(int nsales) { //verrà chiamata quando vendo vino prima updatePrice
		this.nSales=this.nSales+nsales;
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

	public int getnSales() {
		return nSales;
	}

	public void setnSales(int nSales) {
		this.nSales = nSales;
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

	public void setPrice(int price) {
		this.price = price;
	}
}

