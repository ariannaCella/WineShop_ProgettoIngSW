package RequestResponse;

import java.io.Serializable;

public class RequestSearchWine implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String txt,attribute;

    public RequestSearchWine(final String txt, final String a )
    {
        this.txt = txt;
        this.attribute = a;
    }

    public String getTxt() {
        return this.txt;
    }

    public String getAttribute()
    {
        return this.attribute;
    }

}
