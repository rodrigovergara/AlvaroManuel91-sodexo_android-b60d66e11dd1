package sodexo.pe.com.sodexo.presentation.interfaces;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface ChangePassworWebView {
    void showError(String message);

    void showSuccessMessage(String message);

    void showLoading();

    void hideLoading();

}
