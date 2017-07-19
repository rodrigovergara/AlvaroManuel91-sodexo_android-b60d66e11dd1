package sodexo.pe.com.sodexo.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RONALD on 05/10/2016.
 */

public class PromoEntity implements Parcelable {

    private int promoId;

    private String photo;

    private String name;

    private String description;

    private String imageLogo;

    private String cuponUrl;

    private String atention;
    private String address;
    private String phoneNumber;
    private String terms;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.promoId);
        dest.writeString(this.photo);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.imageLogo);
        dest.writeString(this.atention);
        dest.writeString(this.address);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.terms);
    }

    public PromoEntity() {
    }

    protected PromoEntity(Parcel in) {
        this.promoId = in.readInt();
        this.photo = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.imageLogo = in.readString();
        this.atention = in.readString();
        this.address = in.readString();
        this.phoneNumber = in.readString();
        this.terms = in.readString();
    }

    public static final Parcelable.Creator<PromoEntity> CREATOR = new Parcelable.Creator<PromoEntity>() {
        @Override
        public PromoEntity createFromParcel(Parcel source) {
            return new PromoEntity(source);
        }

        @Override
        public PromoEntity[] newArray(int size) {
            return new PromoEntity[size];
        }
    };
}
