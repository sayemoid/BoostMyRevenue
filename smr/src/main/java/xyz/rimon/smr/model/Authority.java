package xyz.rimon.smr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SAyEM on 4/12/17.
 */

public class Authority {

    @SerializedName("authority")
    @Expose
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
