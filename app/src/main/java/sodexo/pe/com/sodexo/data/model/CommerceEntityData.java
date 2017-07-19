package sodexo.pe.com.sodexo.data.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RONALD on 08/10/2016.
 */

public class CommerceEntityData {

    @SerializedName("AsociadoId")
    private int asociateId;
    @SerializedName("RUC")
    private String ruc;
    @SerializedName("RazonSocial")
    private String businessName;
    @SerializedName("Baner1")
    private String banner;
    @SerializedName("LogoRec")
    private String logo;
    @SerializedName("Nombre")
    private String name;
    @SerializedName("Latitud")
    private double latitude;
    @SerializedName("Longitud")
    private double longitude;

    @SerializedName("Atencion")
    private String atention;
    @SerializedName("Direccion")
    private String address;
    @SerializedName("Telefono")
    private String phoneNumber;

    public CommerceEntityData(int asociateId, String ruc, String businessName, String banner, String logo, String name, double latitude, double longitude, String atention, String address, String phoneNumber) {
        this.asociateId = asociateId;
        this.ruc = ruc;
        this.businessName = businessName;
        this.banner = banner;
        this.logo = logo;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.atention = atention;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

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

    public int getAsociateId() {
        return asociateId;
    }

    public void setAsociateId(int asociateId) {
        this.asociateId = asociateId;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
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
}
