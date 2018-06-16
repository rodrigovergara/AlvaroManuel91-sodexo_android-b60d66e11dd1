package sodexo.pe.com.sodexo.presentation.interfaces;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface LoginView {
    void showMessageError(String error);

    void navigateToIntranetOptions();

    void showLoading();

    void hideLoading();
}
