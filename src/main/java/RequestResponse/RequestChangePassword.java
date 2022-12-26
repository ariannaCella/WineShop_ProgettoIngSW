package RequestResponse;

import java.io.Serializable;

public class RequestChangePassword implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String username,oldpassword, newpassword;

    public RequestChangePassword(final String u, final String o, final String n ) {
        this.username = u;
        this.oldpassword = o;
        this.newpassword = n;
    }

    public String getUsername() {
        return username;
    }

    public String getOldpassword()
    {
        return this.oldpassword;
    }
    public String getNewpassword()
    {
        return this.newpassword;
    }
}
