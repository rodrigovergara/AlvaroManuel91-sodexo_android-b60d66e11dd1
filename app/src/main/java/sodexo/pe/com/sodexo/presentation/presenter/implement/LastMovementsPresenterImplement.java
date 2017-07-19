package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.entity.CardTypeEntity;
import sodexo.pe.com.sodexo.domain.entity.MovementEntity;
import sodexo.pe.com.sodexo.domain.interactor.LastMovementsInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.LastMovementsInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.GetAllMovements;
import sodexo.pe.com.sodexo.presentation.interfaces.LastMovementsView;
import sodexo.pe.com.sodexo.presentation.model.GetAllTypeCards;
import sodexo.pe.com.sodexo.presentation.presenter.LastMovementsPresenter;

/**
 * Created by RONALD on 12/10/2016.
 */

public class LastMovementsPresenterImplement implements LastMovementsPresenter {
    private LastMovementsView view;
    private LastMovementsInteractor interactor;

    public LastMovementsPresenterImplement(LastMovementsView view) {
        this.view = view;
        IntranetRepository intranetRepository = new IntranetDataRepository(new IntranetDataMapper());
        interactor = new LastMovementsInteractorImplement(intranetRepository);
    }

    @Override
    public void getTypeCards() {
        view.showLoadig();
        interactor.getAllCardsType(new GetAllTypeCards() {
            @Override
            public void onGetAllTypeCardsSuccess(List<CardTypeEntity> list) {
                view.hideLoading();
                view.populateCardsType(list);
            }

            @Override
            public void onGetAllTypeCardsError(String message) {
                view.hideLoading();
            }
        });
    }

    @Override
    public void getMovements(CardTypeEntity cardTypeEntity) {
        view.showLoadig();
        interactor.getMovements(cardTypeEntity, new GetAllMovements() {
            @Override
            public void onGetAllMovementsSuccess(List<MovementEntity> list) {
                view.hideLoading();
                view.showMovements(list);
            }

            @Override
            public void onGetAllMovementsError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }
}
