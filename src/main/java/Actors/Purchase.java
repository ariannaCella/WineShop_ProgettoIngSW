package Actors;

import java.io.Serializable;

public class Purchase implements Serializable {
    private int purchaseId, wineId, nBottles;
    private boolean signature=false, accepted=false;
    private String fiscalCode, fiscClient, address;
    private float price;

    public Purchase(int pi, String fcode, String fclient, String addr, int wi, int nbott, float pr) {
        this.purchaseId=pi;
        this.fiscalCode=fcode;
        this.fiscClient=fclient;
        this.address=addr;
        this.wineId=wi;
        this.nBottles=nbott;
        this.price=pr;
    }

    public Purchase(int purchaseId, String fc, String fClient, String addr, int wid, int nbott, float price, boolean sign, boolean acc) {
        this.purchaseId=purchaseId;
        this.fiscalCode=fc;
        this.fiscClient=fClient;
        this.address=addr;
        this.wineId=wid;
        this.nBottles=nbott;
        this.price=price;
        this.signature=sign;
        this.accepted=acc;
    }

    public Purchase( String addr, int wid, int nbott, float price, boolean sign, boolean acc) {
        this.address=addr;
        this.wineId=wid;
        this.nBottles=nbott;
        this.price=price;
        this.signature=sign;
        this.accepted=acc;
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

    public float getPrice() {return price;}

    public void setPrice(float price) {this.price = price;}
}
