package sodexo.pe.com.sodexo.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RONALD on 12/10/2016.
 */

public class UbigeoEntityData implements Parcelable {
    @SerializedName("departamento")
    private String department;
    @SerializedName("provincia")
    private String province;
    @SerializedName("distrito")
    private String district;
    @SerializedName("nombre")
    private String name;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.department);
        dest.writeString(this.province);
        dest.writeString(this.district);
        dest.writeString(this.name);
    }

    public UbigeoEntityData() {
    }

    protected UbigeoEntityData(Parcel in) {
        this.department = in.readString();
        this.province = in.readString();
        this.district = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<UbigeoEntityData> CREATOR = new Parcelable.Creator<UbigeoEntityData>() {
        @Override
        public UbigeoEntityData createFromParcel(Parcel source) {
            return new UbigeoEntityData(source);
        }

        @Override
        public UbigeoEntityData[] newArray(int size) {
            return new UbigeoEntityData[size];
        }
    };

    @Override
    public String toString() {
        return "UbigeoEntityData{" +
                "department='" + department + '\'' +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
