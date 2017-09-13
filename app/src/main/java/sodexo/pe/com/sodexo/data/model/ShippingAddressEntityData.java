package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asahel on 9/7/17.
 */

public class ShippingAddressEntityData {

    @SerializedName("DIRECCION")
    private String address;

    @SerializedName("DEPARTAMENTO")
    private String department;

    @SerializedName("DEP_ID")
    private String departmentId;

    @SerializedName("PROVINCIA")
    private String province;

    @SerializedName("PROVINCIA_ID")
    private String provinceId;

    @SerializedName("DISTRITO")
    private String district;

    @SerializedName("DISTRITO_ID")
    private String districtId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    @Override
    public String toString() {
        return "ShippingAddressEntityData{" +
                "address='" + address + '\'' +
                ", department='" + department + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", province='" + province + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", district='" + district + '\'' +
                ", districtId='" + districtId + '\'' +
                '}';
    }
}
