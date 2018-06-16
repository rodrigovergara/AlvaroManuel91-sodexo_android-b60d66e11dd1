package sodexo.pe.com.sodexo.domain.interactor.implement;

import sodexo.pe.com.sodexo.domain.interactor.LoginInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.model.LoginInterface;

/**
 * Created by RONALD on 12/10/2016.
 */

public class LoginInteractorImplement implements LoginInteractor {
    private IntranetRepository intranetRepository;

    public LoginInteractorImplement(IntranetRepository intranetRepository) {
        this.intranetRepository = intranetRepository;
    }

    @Override
    public void login(String dni, String password, LoginInterface callback) {
        intranetRepository.login(dni, password, callback);
    }
}
