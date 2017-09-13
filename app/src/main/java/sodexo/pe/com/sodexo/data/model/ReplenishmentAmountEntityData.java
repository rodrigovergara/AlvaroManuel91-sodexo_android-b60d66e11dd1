package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asahel on 9/8/17.
 */

public class ReplenishmentAmountEntityData {

    @SerializedName("MONTO")
    private double amount;

    public ReplenishmentAmountEntityData(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
