package ink.akira.orderapi.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by akira on 2019/3/28.
 */
public class DemoObj implements Serializable{
    private static final long serialVersionUID = -458011114751174176L;
    private String user;
    private Date birth;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
