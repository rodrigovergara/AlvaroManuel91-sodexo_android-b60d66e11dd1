package sodexo.pe.com.sodexo.domain.entity;

/**
 * Created by RONALD on 12/10/2016.
 */

public class CardDetailEntity {
    private String date;
    private String total;
    private String message;
    private String cardNumber;

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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
