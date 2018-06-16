package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RONALD on 11/10/2016.
 */

public class CardBEEntityData {
    @SerializedName("TarjetaId")
    private int cardId;
    @SerializedName("Nombre")
    private String name;
    @SerializedName("Logo")
    private String logo;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
