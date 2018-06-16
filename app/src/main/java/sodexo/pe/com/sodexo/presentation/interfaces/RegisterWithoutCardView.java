package sodexo.pe.com.sodexo.presentation.interfaces;

/**
 * Created by ronaldvelasquez on 12/12/16.
 */

public interface RegisterWithoutCardView {
    void showError(String error);

    void hideLoading();

    void registerSuccess();


    void showLoading();

}
