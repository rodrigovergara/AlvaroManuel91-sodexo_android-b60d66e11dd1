package sodexo.pe.com.sodexo.presentation.interfaces;

import sodexo.pe.com.sodexo.domain.entity.ReplenishmentAmountEntity;

/**
 * Created by asahel on 9/8/17.
 */

public interface PaymentInformationSummaryView extends BaseParentView {

    void onGetReplenishmentAmount(ReplenishmentAmountEntity replenishmentAmountEntity);
    void onReplaceCardSuccess(String message);
}
