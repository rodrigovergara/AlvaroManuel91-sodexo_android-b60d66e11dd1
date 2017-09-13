package sodexo.pe.com.sodexo.domain.entity;

/**
 * Created by asahel on 9/8/17.
 */

public class ReplenishmentAmountEntity {

    private double amount;

    public ReplenishmentAmountEntity(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
