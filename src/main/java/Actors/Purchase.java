package Actors;

import java.io.Serializable;
import java.sql.Date;

public class Purchase implements Serializable {
    private int purchaseId, wineId, nBottles;
    private boolean signature=false, accepted=false;
    private String fiscalCode, fiscClient, address;
    private double price;
    private Date data;

    public Purchase(int pi, String fcode, String fclient, String addr, int wi, int nbott, double pr) {
        this.purchaseId=pi;
        this.fiscalCode=fcode;
        this.fiscClient=fclient;
        this.address=addr;
        this.wineId=wi;
        this.nBottles=nbott;
        this.price=pr;
    }

    public Purchase(int purchaseId, String fc, String fClient, String addr, int wid, int nbott, double price, boolean sign, boolean acc, Date data) {
        this.purchaseId=purchaseId;
        this.fiscalCode=fc;
        this.fiscClient=fClient;
        this.address=addr;
        this.wineId=wid;
        this.nBottles=nbott;
        this.price=price;
        this.signature=sign;
        this.accepted=acc;
        this.data=data;
    }
    public Purchase(int purchaseId, String fc, String fClient, String addr, int wid, int nbott, double price, boolean sign, boolean acc) {
        this.purchaseId=purchaseId;
        this.fiscalCode=fc;
        this.fiscClient=fClient;
        this.address=addr;
        this.wineId=wid;
        this.nBottles=nbott;
        this.price=price;
        this.signature=sign;
        this.accepted=acc;
        this.data=data;
    }
    public Purchase(int purchaseId,  int wid, int nbott, double price, boolean sign, boolean acc, Date data) {
        this.purchaseId=purchaseId;
        this.wineId=wid;
        this.nBottles=nbott;
        this.price=price;
        this.signature=sign;
        this.accepted=acc;
        this.data=data;
    }
    public Purchase(int purchaseId,  int wid, int nbott, double price, boolean sign, boolean acc) {
        this.purchaseId=purchaseId;
        this.wineId=wid;
        this.nBottles=nbott;
        this.price=price;
        this.signature=sign;
        this.accepted=acc;

    }

    public Purchase( int purchaseId,String addr, int wid, int nbott, double price, boolean sign, boolean acc) {
        this.address=addr;
        this.wineId=wid;
        this.nBottles=nbott;
        this.price=price;
        this.signature=sign;
        this.accepted=acc;
        this.purchaseId=purchaseId;
    }

    public Purchase() {
        this.purchaseId=0;
        this.fiscalCode=null;
        this.fiscClient=null;
        this.address=null;
        this.wineId=0;
        this.nBottles=0;
        this.price=0;
        this.signature=false;
        this.accepted=false;
        this.data=null;
    }

    public String infoPurchase(){
        return "Id:"+purchaseId +"\nCodice Fiscale fornitore: "+ fiscalCode +"\nCodice Fiscale cliente:"+fiscClient+"  indirizzo:"+address+"\nwineId:"+wineId+", numero bottiglie:"+nBottles+"Prezzo di vendita:"+price+"$\n\npresa visione impiegato:"+signature+"\nconsegnato:"+accepted+"\n";
    }
    public int getPurchaseId() {return purchaseId;}

    public void setPurchaseId(int purchaseId) {this.purchaseId = purchaseId;}

    public int getWineId() {return wineId;}

    public void setWineId(int wineId) {this.wineId = wineId;}

    public int getnBottles() {return nBottles;}

    public void setnBottles(int nBottles) {this.nBottles = nBottles;}

    public boolean getSignature() {return signature; }

    public void setSignature(boolean signature) {this.signature = signature;}

    public boolean getAccepted() {return accepted;}

    public void setAccepted(boolean accepted) {this.accepted = accepted;}

    public String getFiscalCode() {return fiscalCode;}

    public void setFiscalCode(String fiscalCode) {this.fiscalCode = fiscalCode;}

    public String getFiscClient() {return fiscClient;}

    public void setFiscClient(String fiscClient) {this.fiscClient = fiscClient;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
