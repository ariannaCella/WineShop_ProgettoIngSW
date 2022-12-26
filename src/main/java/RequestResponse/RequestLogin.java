package RequestResponse;
import java.io.Serializable;
public class RequestLogin implements Serializable{
    private static final long serialVersionUID = 1L;

    private final String username,password,table;

    public RequestLogin(final String u, final String p, final String t )
    {
        this.username = u;
        this.password = p;
        this.table = t;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword()
    {
        return this.password;
    }
    public String getTable()
    {
        return this.table;
    }
}
