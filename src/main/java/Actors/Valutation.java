package Actors;

import java.sql.Date;
import java.util.Calendar;

public class Valutation {
    private final int vote;
    private final Date date;

    public Valutation(final int v, final Date d)
    {
        this.vote = v;
        this.date = d;
    }

    public int getVote() {
        return this.vote;
    }

    public Date getDate()
    {
        return this.date;
    }
}
