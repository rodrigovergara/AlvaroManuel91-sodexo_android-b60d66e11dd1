package sodexo.pe.com.sodexo.presentation.interfaces;

/**
 * Created by ronaldvelasquez on 12/12/16.
 */

public interface RegisterWithCardView {
    void showLoading();

    void hideLoading();

    void showError(String error);

    void showSuccessRegister();

}
