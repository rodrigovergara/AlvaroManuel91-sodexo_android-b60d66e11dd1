package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.entity.BlockingReasonEntity;
import sodexo.pe.com.sodexo.domain.entity.ReplacementCardEntity;
import sodexo.pe.com.sodexo.domain.entity.ReplenishmentAmountEntity;
import sodexo.pe.com.sodexo.domain.interactor.PaymentInformationSummaryInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.PaymentInformationSummaryInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.GetReplenishmentAmount;
import sodexo.pe.com.sodexo.presentation.interfaces.PaymentInformationSummaryView;
import sodexo.pe.com.sodexo.presentation.model.BaseParentInterface;
import sodexo.pe.com.sodexo.presentation.model.GetBlockingReasonsInterface;
import sodexo.pe.com.sodexo.presentation.presenter.PaymentInformationSummaryPresenter;

/**
 * Created by asahel on 9/8/17.
 */

public class PaymentInformationSummaryPresenterImplement implements PaymentInformationSummaryPresenter {

    PaymentInformationSummaryView paymentInformationSummaryView;
    PaymentInformationSummaryInteractor paymentInformationSummaryInteractor;

    public PaymentInformationSummaryPresenterImplement(PaymentInformationSummaryView paymentInformationSummaryView) {
        IntranetRepository intranetRepository = new IntranetDataRepository(new IntranetDataMapper());
        this.paymentInformationSummaryView = paymentInformationSummaryView;
        this.paymentInformationSummaryInteractor = new PaymentInformationSummaryInteractorImplement(intranetRepository);
    }

    @Override
    public void getReplenishmentAmount(String cardNumber, String ubigeo) {
        paymentInformationSummaryView.showLoading();
        paymentInformationSummaryInteractor.getReplenishmentAmount(cardNumber,ubigeo,new GetReplenishmentAmount() {
            @Override
            public void onSuccess(List<ReplenishmentAmountEntity> list) {
                paymentInformationSummaryView.hideLoading();
                if(list != null && !list.isEmpty())
                    paymentInformationSummaryView.onGetReplenishmentAmount(list.get(0));
                else
                    paymentInformationSummaryView.showError("Ocurrió un error al obtener la direccion. Vuelva a intentarlo más tarde.");
            }

            @Override
            public void onError(String message) {
                paymentInformationSummaryView.hideLoading();
                paymentInformationSummaryView.showError(message);
            }
        });
    }

    @Override
    public void replaceCard(ReplacementCardEntity replacementCardEntity) {
        paymentInformationSummaryView.showLoading();
        paymentInformationSummaryInteractor.replaceCard(replacementCardEntity, new BaseParentInterface() {
            @Override
            public void onSuccess(String message) {
                paymentInformationSummaryView.hideLoading();
                paymentInformationSummaryView.onReplaceCardSuccess(message);
            }

            @Override
            public void onError(String message) {
                paymentInformationSummaryView.hideLoading();
                paymentInformationSummaryView.showError(message);
            }
        });
    }
}
