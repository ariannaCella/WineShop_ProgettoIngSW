package RequestResponse;

import java.io.Serializable;

public class RequestProposalPurchase implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String username,address;
    private final int idWine,number;

    public RequestProposalPurchase(final String user, final int idW, final int n, final String a )
    {
        this.username = user;
        this.idWine = idW;
        this.number = n;
        this.address=a;
    }

    public String getUsername() {
        return username;
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