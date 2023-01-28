package RequestResponse;

import java.io.Serializable;

public class RequestProposalPurchase implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String address;
    private final int idWine,number,casse;

    public RequestProposalPurchase(final int idW, final int n, final String a, final int c )
    {
        this.idWine = idW;
        this.number = n;
        this.address=a;
        this.casse=c;
    }

    public int getCasse() {return this.casse;}

    public int getNumberBottles()
    {
        return this.number;
    }
    public String getAddress()
    {
        return this.address;
    }
    public int getIdWine(){return this.idWine;}
}