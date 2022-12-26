package RequestResponse;

import java.io.Serializable;

public class RequestSearchWineId implements Serializable {

    private static final long serialVersionUID = 1L;
    private final int id;

    public RequestSearchWineId(final int id )
    {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}