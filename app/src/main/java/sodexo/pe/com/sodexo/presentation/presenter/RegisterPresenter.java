package sodexo.pe.com.sodexo.presentation.presenter;

/**
 * Created by ronaldvelasquez on 12/12/16.
 */

public interface RegisterPresenter {
    void registerWithCard(String dni, String card, String password, String confirmPassword, String email);

    void registerWithoutCard(String dni, String ruc, String password, String confirmPassword, String name, String lastName, String email);

}
