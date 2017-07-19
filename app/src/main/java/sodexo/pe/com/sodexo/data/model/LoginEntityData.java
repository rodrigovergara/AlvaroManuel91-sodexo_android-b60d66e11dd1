package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RONALD on 11/10/2016.
 */

public class LoginEntityData {
    @SerializedName("DNI")
    private String dni;
    @SerializedName("AP")
    private String lastName;
    @SerializedName("AM")
    private String firstName;
    @SerializedName("NOM")
    private String middleName;
    @SerializedName("ESTADO")
    private String state;
    @SerializedName("RUC")
    private String ruc;
    @SerializedName("PERFIL")
    private String profile;
    @SerializedName("CLI_ID")
    private String clientId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
