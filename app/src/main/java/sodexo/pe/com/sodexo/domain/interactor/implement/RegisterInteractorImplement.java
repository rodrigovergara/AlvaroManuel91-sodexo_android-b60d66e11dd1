package sodexo.pe.com.sodexo.domain.interactor.implement;

import sodexo.pe.com.sodexo.domain.interactor.RegisterInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.model.RegisterInterface;

/**
 * Created by ronaldvelasquez on 12/12/16.
 */

public class RegisterInteractorImplement implements RegisterInteractor {
    private IntranetRepository repository;

    public RegisterInteractorImplement(IntranetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registerUser(String dni, String card, String password, String confirmPassword, String email, RegisterInterface registerInterface) {
        repository.registerUserWithCard(dni, card, password, confirmPassword, email, registerInterface);
    }

    @Override
    public void registerUserWithoutCard(String dni, String ruc, String password, String confirmPassword, String email, String name, String lastName, RegisterInterface registerInterface) {
        repository.registerUserWithoutCard(dni, ruc, password, confirmPassword, email, name, lastName, registerInterface);
    }
}
