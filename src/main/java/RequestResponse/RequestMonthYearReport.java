package RequestResponse;

import java.io.Serializable;

public class RequestMonthYearReport implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int year,month;

    public RequestMonthYearReport(final int y, final int m )
    {
        this.year = y;
        this.month = m;

    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {return this.month;}

}
