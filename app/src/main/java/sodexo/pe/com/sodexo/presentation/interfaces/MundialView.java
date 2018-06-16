package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.BlogEntity;

/**
 * Created by Bit on 14/06/2018.
 */

public interface MundialView {
    void showError(String message);


    void showLoading();

    void openMundial();
    void hideLoading();


}
