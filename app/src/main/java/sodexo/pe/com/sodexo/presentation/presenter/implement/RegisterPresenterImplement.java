package sodexo.pe.com.sodexo.presentation.presenter.implement;

import android.text.TextUtils;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.interactor.RegisterInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.RegisterInteractorImplement;
import sodexo.pe.com.sodexo.presentation.interfaces.RegisterWithCardView;
import sodexo.pe.com.sodexo.presentation.interfaces.RegisterWithoutCardView;
import sodexo.pe.com.sodexo.presentation.model.RegisterInterface;
import sodexo.pe.com.sodexo.presentation.presenter.RegisterPresenter;

/**
 * Created by ronaldvelasquez on 12/12/16.
 */

public class RegisterPresenterImplement implements RegisterPresenter {
    private RegisterWithCardView view;
    private RegisterInteractor interactor;
    private RegisterWithoutCardView withoutCardView;

    public RegisterPresenterImplement(RegisterWithCardView view) {
        this.view = view;
        interactor = new RegisterInteractorImplement(new IntranetDataRepository(new IntranetDataMapper()));
    }

    public RegisterPresenterImplement(RegisterWithoutCardView withoutCardView) {
        this.withoutCardView = withoutCardView;
        interactor = new RegisterInteractorImplement(new IntranetDataRepository(new IntranetDataMapper()));
    }

    @Override
    public void registerWithCard(String dni, String card, String password, String confirmPassword, String email) {
        if (validateData(dni, card, password, confirmPassword, email)) {
            view.showLoading();
            interactor.registerUser(dni, card, password, confirmPassword, email, new RegisterInterface() {
                @Override
                public void onRegisterSuccess() {
                    view.hideLoading();
                    view.showSuccessRegister();
                }

                @Override
                public void onRegisterError(String error) {
                    view.hideLoading();

                }
            });
        } else {
            view.showError("Deben de llenarse todos los datos correctamente");
        }
    }

    private boolean validateData(String dni, String card, String password, String confirmPassword, String email) {
        return !(TextUtils.isEmpty(dni) || TextUtils.isEmpty(card) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(email));
    }

    @Override
    public void registerWithoutCard(String dni, String ruc, String password, String confirmPassword, String name, String lastName, String email) {
        if (validateDataWithoutCard(dni, ruc, password, confirmPassword,name, lastName,email)) {
            withoutCardView.showLoading();
            interactor.registerUserWithoutCard(dni, ruc, password, confirmPassword, email, name, lastName, new RegisterInterface() {
                @Override
                public void onRegisterSuccess() {
                    withoutCardView.hideLoading();
                    withoutCardView.registerSuccess();

                }

                @Override
                public void onRegisterError(String error) {
                    withoutCardView.hideLoading();
                    withoutCardView.showError(error);
                }
            });
        } else {
            withoutCardView.showError("Deben de llenarse todos los datos correctament");
        }
    }

    private boolean validateDataWithoutCard(String dni, String ruc, String password, String confirmPassword, String name, String lastName, String email) {
        return !(TextUtils.isEmpty(dni) /*|| TextUtils.isEmpty(ruc)*/ || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(lastName));

    }
}
