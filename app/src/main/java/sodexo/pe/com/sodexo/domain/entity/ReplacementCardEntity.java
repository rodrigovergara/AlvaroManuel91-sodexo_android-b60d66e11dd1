package sodexo.pe.com.sodexo.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by asahel on 9/8/17.
 */

public class ReplacementCardEntity implements Parcelable {
    private String deliveryPlace;
    private String address1;
    private String address2;
    private String contactName;
    private String phoneNumber;
    private String departmentId;
    private String provinceId;
    private String districtId;
    private String cardNumber;
    private String email;

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ReplacementCardEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.deliveryPlace);
        dest.writeString(this.address1);
        dest.writeString(this.address2);
        dest.writeString(this.contactName);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.departmentId);
        dest.writeString(this.provinceId);
        dest.writeString(this.districtId);
        dest.writeString(this.cardNumber);
        dest.writeString(this.email);
    }

    protected ReplacementCardEntity(Parcel in) {
        this.deliveryPlace = in.readString();
        this.address1 = in.readString();
        this.address2 = in.readString();
        this.contactName = in.readString();
        this.phoneNumber = in.readString();
        this.departmentId = in.readString();
        this.provinceId = in.readString();
        this.districtId= in.readString();
        this.cardNumber = in.readString();
        this.email = in.readString();
    }

    public static final Creator<ReplacementCardEntity> CREATOR = new Creator<ReplacementCardEntity>() {
        @Override
        public ReplacementCardEntity createFromParcel(Parcel source) {
            return new ReplacementCardEntity(source);
        }

        @Override
        public ReplacementCardEntity[] newArray(int size) {
            return new ReplacementCardEntity[size];
        }
    };
}
