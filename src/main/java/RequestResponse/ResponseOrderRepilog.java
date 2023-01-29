package RequestResponse;

import Actors.Client;
import Actors.Wine;

import java.io.Serializable;
import java.sql.Date;

public class ResponseOrderRepilog implements Serializable{
    private static final long serialVersionUID = 1L;

    private final Client client;
    private final Wine wine;
    private final int nBottle;
    private final Date date;
    private final double price;

    public ResponseOrderRepilog(final Client c, final Wine w, final Date d , final int n, final double p)
    {
        this.client = c;
        this.wine = w;
        this.date = d;
        this.price=p;
        this.nBottle=n;
    }

    public int getBottle() {
        return nBottle;
    }

    public Wine getWine() {
        return wine;
    }
    public Date getDate() {
        return date;
    }
    public Client getClient() {
        return client;
    }
    public double getPrice() {
        return price;
    }
}
