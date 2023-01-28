package RequestResponse;
import Actors.*;

import java.io.Serializable;

public class ResponseReport implements Serializable{
    private static final long serialVersionUID = 1L;

    private final int nBottlesAvailable;
    private final double income;
    private final double expenses;
    private final int nBottleSold;

    public ResponseReport(final double i, final double e, final int ba , final int bs)
    {
        this.income = i;
        this.expenses= e;
        this.nBottlesAvailable = ba;
        this.nBottleSold=bs;
    }

    public double getIncome() {
        return income;
    }

    public int getnBottlesAvailable() {
        return nBottlesAvailable;
    }

    public int getnBottleSold() {
        return nBottleSold;
    }

    public double getExpenses() {
        return expenses;
    }

}

