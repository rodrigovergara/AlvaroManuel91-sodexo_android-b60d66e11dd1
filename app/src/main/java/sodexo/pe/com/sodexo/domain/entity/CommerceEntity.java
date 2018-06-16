package sodexo.pe.com.sodexo.domain.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class CommerceEntity implements Parcelable {
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.businessName);
        dest.writeString(this.banner);
        dest.writeString(this.logo);
        dest.writeString(this.name);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.address);
        dest.writeString(this.atention);
        dest.writeString(this.phoneNumber);
    }

    public CommerceEntity() {
    }

    protected CommerceEntity(Parcel in) {
        this.businessName = in.readString();
        this.banner = in.readString();
        this.logo = in.readString();
        this.name = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.address = in.readString();
        this.atention = in.readString();
        this.phoneNumber = in.readString();
    }

    public static final Parcelable.Creator<CommerceEntity> CREATOR = new Parcelable.Creator<CommerceEntity>() {
        @Override
        public CommerceEntity createFromParcel(Parcel source) {
            return new CommerceEntity(source);
        }

        @Override
        public CommerceEntity[] newArray(int size) {
            return new CommerceEntity[size];
        }
    };
}
