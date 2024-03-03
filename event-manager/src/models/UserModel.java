package models;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String username;
    private String usermail;
    private String userpassword;

    public UserModel(String username, String usermail, String userpassword) {
        this.username = username;
        this.usermail = usermail;
        this.userpassword = userpassword;
    }
}