package sodexo.pe.com.sodexo.domain.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RONALD on 12/10/2016.
 */

public class CardTypeEntity {
    private String cardId;
    private String cardDescription;

    public CardTypeEntity(String cardId, String cardDescription) {
        this.cardId = cardId;
        this.cardDescription = cardDescription;
    }

    public CardTypeEntity() {
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }
}
