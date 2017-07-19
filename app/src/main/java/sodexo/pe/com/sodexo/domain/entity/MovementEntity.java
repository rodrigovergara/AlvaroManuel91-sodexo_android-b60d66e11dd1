package sodexo.pe.com.sodexo.domain.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RONALD on 12/10/2016.
 */

public class MovementEntity {
    private String message;
    private String date;
    private String cardNumber;
    private String operation;
    private String mount;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMount() {
        return mount;
    }

    public void setMount(String mount) {
        this.mount = mount;
    }
}
