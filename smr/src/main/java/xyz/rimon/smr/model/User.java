package xyz.rimon.smr.model;

import android.location.Address;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import xyz.rimon.smr.commons.Commons;

/**
 * Created by SAyEM on 4/12/17.
 */

public class User {
    @SerializedName("id")
    private Long id;
    @SerializedName("created")
    private Long created;
    @SerializedName("lastUpdated")
    private Long lastUpdated;
    @SerializedName("name")
    private String name;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("address")
    private Address address;
    @SerializedName("enabled")
    private Boolean enabled;
    @SerializedName("accountNonExpired")
    private Boolean accountNonExpired;
    @SerializedName("accountNonLocked")
    private Boolean accountNonLocked;
    @SerializedName("credentialsNonExpired")
    private Boolean credentialsNonExpired;

    private User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.username = Commons.sliceUsername(email);
    }

    public User(String name, String username, String email) {
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        Date date = new Date();
        date.setTime(this.created);
        return date;
    }

    public void setCreated(Date created) {
        this.created = created.getTime();
    }

    public Date getLastUpdatedDate() {
        Date lastUpdated = new Date();
        lastUpdated.setTime(this.lastUpdated);
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated.getTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", created=" + created +
                ", lastUpdated=" + lastUpdated +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                '}';
    }
}
