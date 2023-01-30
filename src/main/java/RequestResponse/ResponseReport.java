package RequestResponse;
import Actors.*;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseReport implements Serializable{
    private static final long serialVersionUID = 1L;

    private final int nBottlesAvailable;
    private final double income, averageVote;
    private final double expenses;
    private final int nBottleSold;
    private final ArrayList<WineSold> wineSold;
    public ResponseReport(final double i, final double e, final int ba , final int bs, final ArrayList<WineSold> ws, final double av)
    {
        this.income = i;
        this.expenses= e;
        this.nBottlesAvailable = ba;
        this.nBottleSold=bs;
        this.wineSold=ws;
        this.averageVote=av;
    }

    public double getAverageVote() {
        return averageVote;
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

