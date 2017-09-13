package sodexo.pe.com.sodexo.presentation.presenter;

import sodexo.pe.com.sodexo.domain.entity.ReplacementCardEntity;

/**
 * Created by asahel on 9/8/17.
 */

public interface PaymentInformationSummaryPresenter {

    void getReplenishmentAmount(String cardNumber,String ubigeo);

    void replaceCard(ReplacementCardEntity replacementCardEntity);

}
