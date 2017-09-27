package com.techno.matrimonial.Model;

import java.io.Serializable;

/**
 * Created by arbaz on 4/2/17.
 */
public class Ids implements Serializable {
    String to_user_id;

    public Ids(String to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(String to_user_id) {
        this.to_user_id = to_user_id;
    }
}
