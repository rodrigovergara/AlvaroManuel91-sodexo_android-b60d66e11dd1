package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.entity.CardDetailEntity;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.domain.interactor.ViewCreditInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.ViewCreditInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.ViewCreditView;
import sodexo.pe.com.sodexo.presentation.model.GetCardDetailInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardsInterface;
import sodexo.pe.com.sodexo.presentation.presenter.ViewCreditPresenter;

/**
 * Created by RONALD on 12/10/2016.
 */

public class ViewCreditPresenterImplement implements ViewCreditPresenter {
    private ViewCreditView view;
    private ViewCreditInteractor interactor;

    public ViewCreditPresenterImplement(ViewCreditView view) {
        IntranetRepository intranetRepository = new IntranetDataRepository(new IntranetDataMapper());
        this.view = view;
        interactor = new ViewCreditInteractorImplement(intranetRepository);
    }

    @Override
    public void getNumberCards() {
        view.showLoading();
        interactor.getCards(new GetCardsInterface() {
            @Override
            public void onGetCardsSucces(List<CardEntity> list) {
                view.hideLoading();
                view.populateReplacementCards(list);
            }

            @Override
            public void onGetCardsError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });

    }

    @Override
    public void getCardDetail(CardEntity cardEntity) {
        view.showLoading();
        interactor.getDetailCard(cardEntity, new GetCardDetailInterface() {
            @Override
            public void onGetCardDetailSuccess(CardDetailEntity cardDetail) {
                view.hideLoading();
                view.showCardDetail(cardDetail);
            }

            @Override
            public void onGetCardDetailError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }
}
