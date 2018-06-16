package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RONALD on 12/10/2016.
 */

public class LastMovementsResponse {
    @SerializedName("err")
    private boolean isError;
    @SerializedName("mensaje")
    private String message;
    @SerializedName("consulta")
    private List<MovementEntityData> data;

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

    public List<MovementEntityData> getData() {
        return data;
    }

    public void setData(List<MovementEntityData> data) {
        this.data = data;
    }

    public class MovementEntityData {
        @SerializedName("FECHA")
        private String date;
        @SerializedName("TARJETA")
        private String cardNumber;
        @SerializedName("OPERACION")
        private String operation;
        @SerializedName("MONTO")
        private String mount;

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
}
