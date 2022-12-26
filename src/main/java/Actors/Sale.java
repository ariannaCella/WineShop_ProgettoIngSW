package Actors;

import java.io.Serializable;

public class Sale implements Serializable {
    private int saleId, wineId, nBottles;
    private boolean signature = false, accepted = false;
    private String fiscalCode, address;
    private double price;
    private int d; //contiene la data corrente

    public Sale(int saleId, int wineId, int nBottles, boolean signature, boolean accepted, String fiscalCode, String address, double price, int d) {
        this.saleId = saleId;
        this.wineId = wineId;
        this.nBottles = nBottles;
        this.signature = signature;
        this.accepted = accepted;
        this.fiscalCode = fiscalCode;
        this.address = address;
        this.price = price;
        this.d = d;
    }

    public Sale() {
        this.saleId = 0;
        this.wineId = 0;
        this.nBottles = 0;
        this.signature = false;
        this.accepted = false;
        this.fiscalCode = null;
        this.address = null;
        this.price = 0;
        this.d = 0;
    }

    public String infoSale() {
        return "Id:" + saleId + "\nCodice Fiscale: " + fiscalCode + "  indirizzo:" + address + "\nwineId:" + wineId + ", numero bottiglie:" + nBottles + "Prezzo di vendita:" + price + "$\n\npresa visione impiegato:" + signature + "\nconsegnato:" + accepted + "\n";
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getWineId() {
        return wineId;
    }

    public void setWineId(int wineId) {
        this.wineId = wineId;
    }

    public int getnBottles() {
        return nBottles;
    }

    public void setnBottles(int nBottles) {
        this.nBottles = nBottles;
    }

    public boolean getSignature() {
        return signature;
    }

    public void setSignature(boolean signature) {
        this.signature = signature;
    }

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
}