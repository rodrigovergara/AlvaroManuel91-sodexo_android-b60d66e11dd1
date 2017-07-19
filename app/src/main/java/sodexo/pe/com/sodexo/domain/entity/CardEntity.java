package sodexo.pe.com.sodexo.domain.entity;

/**
 * Created by RONALD on 12/10/2016.
 */

public class CardEntity {
    private String cardNumber;
    private String cardCode;

    public CardEntity(String cardNumber, String cardCode) {
        this.cardNumber = cardNumber;
        this.cardCode = cardCode;
    }

    public CardEntity() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }
}
