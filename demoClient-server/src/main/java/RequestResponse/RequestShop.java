package RequestResponse;

import Actors.Wine;

import java.io.Serializable;

public class RequestShop implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int number,button;
    private final Wine wine;
    private final String type;

    public RequestShop(final int n, final Wine w ,final String t,final int b)
    {
        this.number = n;
        this.wine = w;
        this.type=t;
        this.button=b;
    }

    public int getButton() {return button;}

    public int getNumber() {
        return this.number;
    }

    public Wine getWine()
    {
        return this.wine;
    }
    public String getType(){return  this.type;}
}

