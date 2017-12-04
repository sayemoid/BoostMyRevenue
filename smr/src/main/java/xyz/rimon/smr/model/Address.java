package xyz.rimon.smr.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SAyEM on 4/12/17.
 */

public class Address {
    @SerializedName("country")
    private String country;
    @SerializedName("city")
    private String city;
    @SerializedName("district")
    private String district;
    @SerializedName("upazila")
    private String upazila;
    @SerializedName("area")
    private String area;
    @SerializedName("mapDirectionFrom")
    private String mapDirectionFrom;
    @SerializedName("commaSeperatedString")
    private String commaSeperatedString;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getUpazila() {
        return upazila;
    }

    public void setUpazila(String upazila) {
        this.upazila = upazila;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMapDirectionFrom() {
        return mapDirectionFrom;
    }

    public void setMapDirectionFrom(String mapDirectionFrom) {
        this.mapDirectionFrom = mapDirectionFrom;
    }

    public String getCommaSeperatedString() {
        return commaSeperatedString;
    }

    public void setCommaSeperatedString(String commaSeperatedString) {
        this.commaSeperatedString = commaSeperatedString;
    }
}
