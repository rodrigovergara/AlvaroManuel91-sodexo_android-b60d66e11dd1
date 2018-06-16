package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.presentation.model.LoginInterface;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface LoginInteractor {
    void login(String dni, String password, LoginInterface callback);
}
