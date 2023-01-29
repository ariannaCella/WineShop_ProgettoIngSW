package Actors;

import java.io.Serializable;

public class WineSold implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int ID,wineSold;

    public WineSold(final int i, final int w)
    {
        this.ID = i;
        this.wineSold = w;
    }

    public int getID() {
        return this.ID;
    }

    public int getWineSold()
    {
        return this.wineSold;
    }
}
