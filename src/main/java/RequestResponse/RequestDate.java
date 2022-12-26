package RequestResponse;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class RequestDate implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Date Begin,End;

    public RequestDate(final Date b, final Date e )
    {
        this.Begin = b;
        this.End = e;

    }

    public Date getBegin() {
        return Begin;
    }

    public Date getEnd()
    {
        return this.End;
    }

}
