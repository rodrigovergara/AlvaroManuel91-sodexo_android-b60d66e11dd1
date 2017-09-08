package sodexo.pe.com.sodexo.domain.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asahel on 9/7/17.
 */

public class ShippingAddressEntity {

    private String address;

    private String department;

    private String departmentId;

    private String province;

    private String provinceId;

    private String district;

    private String districtId;

    public ShippingAddressEntity(String address, String department, String departmentId, String province, String provinceId, String district, String districtId) {
        this.address = address;
        this.department = department;
        this.departmentId = departmentId;
        this.province = province;
        this.provinceId = provinceId;
        this.district = district;
        this.districtId = districtId;
    }

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
        return "ShippingAddressData{" +
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
