package RequestResponse;
import Actors.*;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseReport implements Serializable{
    private static final long serialVersionUID = 1L;

    private final int nBottlesAvailable;
    private final double income;
    private final double expenses;
    private final int nBottleSold;
    private final ArrayList<WineSold> wineSold;
    public ResponseReport(final double i, final double e, final int ba , final int bs, final ArrayList<WineSold> ws)
    {
        this.income = i;
        this.expenses= e;
        this.nBottlesAvailable = ba;
        this.nBottleSold=bs;
        this.wineSold=ws;
    }

    public ArrayList<WineSold> getWineSold() {
        return wineSold;
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

