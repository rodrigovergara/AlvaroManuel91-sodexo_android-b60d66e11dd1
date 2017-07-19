package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RONALD on 11/10/2016.
 */

public class FilterLocalEntityData {
    @SerializedName("AsociadoId")
    private int asociateId;
    @SerializedName("RUC")
    private String ruc;
    @SerializedName("RazonSocial")
    private String businessName;
    @SerializedName("Baner1")
    private String banner1;
    @SerializedName("Baner2")
    private String banner2;
    @SerializedName("Descripcion")
    private String description;
    @SerializedName("Direccion")
    private String address;
    @SerializedName("Logo")
    private String logo;
    @SerializedName("LogoRec")
    private String logoRec;
    @SerializedName("Nombre")
    private String name;
    @SerializedName("Telefono")
    private String phone;
    @SerializedName("Latitud")
    private double latitude;
    @SerializedName("Longitud")
    private double longitude;
    @SerializedName("DistritoId")
    private int districtId;
    @SerializedName("Carta")
    private String newsLetter;
    @SerializedName("arrTarjetaBE")
    private List<CardBEEntityData> cards;

    @SerializedName("Atencion")
    private String atention;

    public String getAtention() {
        return atention;
    }

    public void setAtention(String atention) {
        this.atention = atention;
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

    public String getBanner1() {
        return banner1;
    }

    public void setBanner1(String banner1) {
        this.banner1 = banner1;
    }

    public String getBanner2() {
        return banner2;
    }

    public void setBanner2(String banner2) {
        this.banner2 = banner2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogoRec() {
        return logoRec;
    }

    public void setLogoRec(String logoRec) {
        this.logoRec = logoRec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(String newsLetter) {
        this.newsLetter = newsLetter;
    }

    public List<CardBEEntityData> getCards() {
        return cards;
    }

    public void setCards(List<CardBEEntityData> cards) {
        this.cards = cards;
    }
}
