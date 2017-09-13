package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.domain.entity.ReplacementCardEntity;
import sodexo.pe.com.sodexo.presentation.interfaces.GetReplenishmentAmount;
import sodexo.pe.com.sodexo.presentation.model.BaseParentInterface;

/**
 * Created by asahel on 9/8/17.
 */

public interface PaymentInformationSummaryInteractor {

    void getReplenishmentAmount(String cardNumber, String ubigeo, GetReplenishmentAmount callback);

    void replaceCard(ReplacementCardEntity replacementCardEntity, BaseParentInterface baseParentInterface);

}
