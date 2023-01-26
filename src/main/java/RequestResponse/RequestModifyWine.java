package RequestResponse;

import java.io.Serializable;

public class RequestModifyWine  implements Serializable {

    private final String note;
    private final int quantity,year;
    private final double price;

    public RequestModifyWine(final int q, final double p, final String n, final int y )
    {
        this.note = n;
        this.quantity = q;
        this.price = p;
        this.year=y;
    }

    public String getNote() {
        return note;
    }
    public double getPrice()
    {
        return this.price;
    }
    public int getQuantity()
    {
        return this.quantity;
    }
    public int getYear()
    {
        return this.year;
    }
}