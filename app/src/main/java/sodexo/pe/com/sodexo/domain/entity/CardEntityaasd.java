package sodexo.pe.com.sodexo.domain.entity;

/**
 * Created by RONALD on 07/10/2016.
 */

public class CardEntityaasd {
    private int id;
    private String numberCard;
    private String cardType;

    public CardEntityaasd(int id, String numberCard, String cardType) {
        this.id = id;
        this.numberCard = numberCard;
        this.cardType = cardType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
