package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RONALD on 13/10/2016.
 */

public class UserEntityData {
    @SerializedName("DIRECCION")
    private String address;
    @SerializedName("DPTO")
    private String department;
    @SerializedName("PROV")
    private String provincie;
    @SerializedName("DIST")
    private String district;
    @SerializedName("CORREO")
    private String email;
    @SerializedName("CARGO")
    private String cargo;
    @SerializedName("SEXO")
    private String sex;
    @SerializedName("TF")
    private String phone;
    @SerializedName("CELULAR")
    private String cellPhone;
    @SerializedName("RS")
    private String businessName;
    @SerializedName("AP")
    private String lastName;
    @SerializedName("AM")
    private String middleName;
    @SerializedName("NOM")
    private String name;
    @SerializedName("DNI")
    private String dni;
    @SerializedName("BEN_FCH_INGRESO")
    private String date;

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

    public String getProvincie() {
        return provincie;
    }

    public void setProvincie(String provincie) {
        this.provincie = provincie;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
