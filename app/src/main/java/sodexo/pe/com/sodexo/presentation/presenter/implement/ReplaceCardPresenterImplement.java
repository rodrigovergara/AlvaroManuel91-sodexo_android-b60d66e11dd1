package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.entity.BlockingReasonEntity;
import sodexo.pe.com.sodexo.domain.entity.CardDetailEntity;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.domain.interactor.BlockCardInteractor;
import sodexo.pe.com.sodexo.domain.interactor.ReplaceCardInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.BlockCardInteractorImplement;
import sodexo.pe.com.sodexo.domain.interactor.implement.ReplaceCardInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.ReplaceCardView;
import sodexo.pe.com.sodexo.presentation.model.BaseParentInterface;
import sodexo.pe.com.sodexo.presentation.model.GetBlockingReasonsInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardDetailInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardsInterface;
import sodexo.pe.com.sodexo.presentation.presenter.ReplaceCardPresenter;

/**
 * Created by asahel on 8/29/17.
 */

public class ReplaceCardPresenterImplement implements ReplaceCardPresenter {

    private ReplaceCardView view;
    private BlockCardInteractor blockCardInteractor;
    private ReplaceCardInteractor replaceCardInteractor;

    public ReplaceCardPresenterImplement(ReplaceCardView view) {
        IntranetRepository intranetRepository = new IntranetDataRepository(new IntranetDataMapper());
        this.view = view;
        this.blockCardInteractor = new BlockCardInteractorImplement(intranetRepository);
        this.replaceCardInteractor = new ReplaceCardInteractorImplement(intranetRepository);
    }

    @Override
    public void blockCard(String cardNumber,String reasonId) {
        view.showLoading();
        blockCardInteractor.blockCard(cardNumber,reasonId, new BaseParentInterface() {
            @Override
            public void onSuccess(String message) {
                view.hideLoading();
                view.onBlockCardSuccess(message);
            }

            @Override
            public void onError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }

    @Override
    public void getReplacementCardNumbers() {
        view.showLoading();
        replaceCardInteractor.getReplacementCardNumbers(new GetCardsInterface() {
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
        replaceCardInteractor.getCardDetail(cardEntity, new GetCardDetailInterface() {
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

    @Override
    public void getBlockingReasons() {
        view.showLoading();
        blockCardInteractor.getBlockingReasons(new GetBlockingReasonsInterface() {
            @Override
            public void onGetBlockingReasonsSuccess(List<BlockingReasonEntity> list) {
                view.hideLoading();
                view.populateReasonsSpinner(list);
            }

            @Override
            public void onGetBlockingReasonsError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }
}
