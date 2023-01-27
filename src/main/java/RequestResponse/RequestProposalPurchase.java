package RequestResponse;

import java.io.Serializable;

public class RequestProposalPurchase implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String address;
    private final int idWine,number;

    public RequestProposalPurchase(final int idW, final int n, final String a )
    {
        this.idWine = idW;
        this.number = n;
        this.address=a;
    }

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