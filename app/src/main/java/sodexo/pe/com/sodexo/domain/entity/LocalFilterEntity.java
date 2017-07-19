package sodexo.pe.com.sodexo.domain.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RONALD on 11/10/2016.
 */

public class LocalFilterEntity {
    private String businessName;
    private String banner;
    private String logo;
    private String name;
    private double latitude;
    private double longitude;
    private String atention;
    private String address;
    private String phoneNumber;

    public String getAtention() {
        return atention;
    }

    public void setAtention(String atention) {
        this.atention = atention;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
