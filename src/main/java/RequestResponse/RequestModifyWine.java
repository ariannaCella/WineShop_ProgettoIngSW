package RequestResponse;

import java.io.Serializable;

public class RequestModifyWine  implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String note;
    private final String quantity, year;
    private final String price;

    public RequestModifyWine(final String q, final String p, final String n, final String y) {
        this.note = n;
        this.quantity = q;
        this.price = p;
        this.year = y;
    }

    public String getNote() {
        return note;
    }

    public String getPrice() {
        return this.price;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public String getYear() {
        return this.year;
    }
}