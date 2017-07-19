package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

public class PromoEntityData {
    @SerializedName("PromocionId")
    private int promoId;
    @SerializedName("Foto")
    private String photo;
    @SerializedName("Nombre")
    private String name;
    @SerializedName("PrecioLista")
    private double originalPrice;
    @SerializedName("PrecioPromocion")
    private double promoPrice;
    @SerializedName("Descripcion")
    private String description;
    @SerializedName("Logo")
    private String imageLogo;
    @SerializedName("EsRecomendado")
    private boolean isRecommended;
    @SerializedName("EsExclusivo")
    private boolean isExclusive;
    @SerializedName("UrlArchivo")
    private String cuponUrl;

    @SerializedName("Atencion")
    private String atention;
    @SerializedName("Direccion")
    private String address;
    @SerializedName("Telefono")
    private String phoneNumber;
    @SerializedName("TerminosYCondiciones")
    private String terms;

    public PromoEntityData(int promoId, String photo, String name, double originalPrice, double promoPrice, String description, String imageLogo, boolean isRecommended, boolean isExclusive, String cuponUrl, String atention, String address, String phoneNumber, String terms) {
        this.promoId = promoId;
        this.photo = photo;
        this.name = name;
        this.originalPrice = originalPrice;
        this.promoPrice = promoPrice;
        this.description = description;
        this.imageLogo = imageLogo;
        this.isRecommended = isRecommended;
        this.isExclusive = isExclusive;
        this.cuponUrl = cuponUrl;
        this.atention = atention;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.terms = terms;
    }

    public String getCuponUrl() {
        return cuponUrl;
    }

    public void setCuponUrl(String cuponUrl) {
        this.cuponUrl = cuponUrl;
    }

    public int getPromoId() {
        return promoId;
    }

    public void setPromoId(int promoId) {
        this.promoId = promoId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(double promoPrice) {
        this.promoPrice = promoPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(String imageLogo) {
        this.imageLogo = imageLogo;
    }

    public boolean isRecommended() {
        return isRecommended;
    }

    public void setRecommended(boolean recommended) {
        isRecommended = recommended;
    }

    public boolean isExclusive() {
        return isExclusive;
    }

    public void setExclusive(boolean exclusive) {
        isExclusive = exclusive;
    }

    public void setIsRecommended(boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public void setIsExclusive(boolean isExclusive) {
        this.isExclusive = isExclusive;
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

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }
}
