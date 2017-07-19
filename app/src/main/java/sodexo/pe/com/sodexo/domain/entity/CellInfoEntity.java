package sodexo.pe.com.sodexo.domain.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RONALD on 13/10/2016.
 */

public class CellInfoEntity {
    private String celular;
    private String registerSms;
    private String operator;

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRegisterSms() {
        return registerSms;
    }

    public void setRegisterSms(String registerSms) {
        this.registerSms = registerSms;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
