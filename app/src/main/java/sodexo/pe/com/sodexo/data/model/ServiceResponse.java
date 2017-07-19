package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RONALD on 11/10/2016.
 */

public class ServiceResponse<T> {
    @SerializedName("err")
    private boolean isError;
    @SerializedName("mensaje")
    private String message;
    @SerializedName("data")
    private T data;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
