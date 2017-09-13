package sodexo.pe.com.sodexo.domain.interactor.implement;

import sodexo.pe.com.sodexo.domain.entity.ReplacementCardEntity;
import sodexo.pe.com.sodexo.domain.interactor.PaymentInformationSummaryInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.GetReplenishmentAmount;
import sodexo.pe.com.sodexo.presentation.model.BaseParentInterface;

/**
 * Created by asahel on 9/8/17.
 */

public class PaymentInformationSummaryInteractorImplement implements PaymentInformationSummaryInteractor {

    private IntranetRepository intranetRepository;

    public PaymentInformationSummaryInteractorImplement(IntranetRepository intranetRepository) {
        this.intranetRepository = intranetRepository;
    }

    @Override
    public void getReplenishmentAmount(String cardNumber, String ubigeo, GetReplenishmentAmount callback) {
        intranetRepository.getReplenishmentAmount(cardNumber,ubigeo,callback);
    }

    @Override
    public void replaceCard(ReplacementCardEntity replacementCardEntity, BaseParentInterface baseParentInterface) {
        intranetRepository.replaceCard(replacementCardEntity, baseParentInterface);
    }
}
