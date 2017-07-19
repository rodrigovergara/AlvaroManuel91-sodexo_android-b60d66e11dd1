package sodexo.pe.com.sodexo.presentation.presenter.implement;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.interactor.ChangePasswordInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.ChangePasswordInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.ChangePassworWebView;
import sodexo.pe.com.sodexo.presentation.model.ChangePasswordInterface;
import sodexo.pe.com.sodexo.presentation.presenter.ChangePassworsPresenter;

/**
 * Created by RONALD on 12/10/2016.
 */

public class ChangePassworsPresenterImplement implements ChangePassworsPresenter {
    private ChangePassworWebView view;
    private ChangePasswordInteractor interactor;

    public ChangePassworsPresenterImplement(ChangePassworWebView view) {
        IntranetRepository repository = new IntranetDataRepository(new IntranetDataMapper());
        this.view = view;
        this.interactor = new ChangePasswordInteractorImplement(repository);
    }

    @Override
    public void changePassword(String password, String newPassword, String repeatNewPassword) {
        if (!newPassword.equals(repeatNewPassword)){
            view.showError("La claves no son iguales") ;
        } else {
            view.showLoading();
            interactor.changePasswordWeb(password, newPassword, new ChangePasswordInterface() {
                @Override
                public void onChangePasswordSuccess() {
                    view.hideLoading();
                    view.showSuccessMessage("Clave cambiada correctamente");
                }

                @Override
                public void onChangePasswordError(String message) {
                    view.hideLoading();
                    view.showError(message);
                }
            });
        }
    }
}
