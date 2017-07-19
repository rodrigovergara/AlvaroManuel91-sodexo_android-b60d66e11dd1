package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.domain.interactor.ChangePasswordInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.ChangePasswordInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.ChangePasswordCardView;
import sodexo.pe.com.sodexo.presentation.model.ChangePasswordInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardsInterface;
import sodexo.pe.com.sodexo.presentation.presenter.ChangePasswordCardPresenter;

/**
 * Created by RONALD on 12/10/2016.
 */

public class ChangePasswordCardPresenterImplement implements ChangePasswordCardPresenter {
    private ChangePasswordCardView view;
    private ChangePasswordInteractor changePasswordInteractor;

    public ChangePasswordCardPresenterImplement(ChangePasswordCardView view) {
        IntranetRepository intranetRepository = new IntranetDataRepository(new IntranetDataMapper());
        this.view = view;
        this.changePasswordInteractor = new ChangePasswordInteractorImplement(intranetRepository);
    }

    @Override
    public void changePassword(CardEntity cardEntity, String password, String newPassword, String repeatNewPassword) {
        view.showLoading();
        changePasswordInteractor.changePasswordCard(cardEntity, password, newPassword, repeatNewPassword, new ChangePasswordInterface() {
            @Override
            public void onChangePasswordSuccess() {
                view.hideLoading();
                view.showError("La clave se cambio correctamente");
            }

            @Override
            public void onChangePasswordError(String message) {
                view.hideLoading();
                view.showError(message);

            }
        });
    }

    @Override
    public void getNumberCards() {
        view.showLoading();
        changePasswordInteractor.getCards(new GetCardsInterface() {
            @Override
            public void onGetCardsSucces(List<CardEntity> list) {
                view.hideLoading();
                view.populateSpinner(list);
            }

            @Override
            public void onGetCardsError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }
}
