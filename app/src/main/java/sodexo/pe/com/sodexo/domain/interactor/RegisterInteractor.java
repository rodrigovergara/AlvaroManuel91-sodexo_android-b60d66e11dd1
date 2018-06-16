package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.presentation.model.RegisterInterface;

/**
 * Created by ronaldvelasquez on 12/12/16.
 */

public interface RegisterInteractor {
    void registerUser(String dni, String card, String password, String confirmPassword, String email, RegisterInterface registerInterface);

    void registerUserWithoutCard(String dni, String ruc, String password, String confirmPassword, String email, String name, String lastName, RegisterInterface registerInterface);

}
