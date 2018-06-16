package sodexo.pe.com.sodexo.presentation.presenter.implement;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.interactor.LoginInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.LoginInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.interfaces.LoginView;
import sodexo.pe.com.sodexo.presentation.model.LoginInterface;
import sodexo.pe.com.sodexo.presentation.presenter.LoginPresenter;

/**
 * Created by RONALD on 12/10/2016.
 */

public class LoginPresenterImplement implements LoginPresenter {
    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImplement(LoginView view) {
        IntranetRepository intranetRepository = new IntranetDataRepository(new IntranetDataMapper());
        this.view = view;
        this.interactor = new LoginInteractorImplement(intranetRepository);
    }

    @Override
    public void login(final String dni, final String password) {
        if (TextUtils.isEmpty(dni) || TextUtils.isEmpty(password)) {
            view.showMessageError("Error");
        } else {
            view.showLoading();
            interactor.login(dni, password, new LoginInterface() {
                @Override
                public void onLoginSuccess() {
                    view.hideLoading();
                    if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getBoolean(SodexoApplication.SAVE, false)) {
                         PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).edit().putString("usuario", dni).putString("password", password).commit();

                    }
                    view.navigateToIntranetOptions();
                }

                @Override
                public void onLoginError(String message) {
                    view.hideLoading();
                    view.showMessageError(message);
                }
            });
        }
    }
}
