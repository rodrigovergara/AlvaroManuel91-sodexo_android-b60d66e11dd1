package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RONALD on 12/10/2016.
 */

public class CardDetailEntityData {
    @SerializedName("err")
    private boolean isError;
    @SerializedName("fecha")
    private String date;
    @SerializedName("total")
    private String total;
    @SerializedName("mensaje")
    private String message;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
