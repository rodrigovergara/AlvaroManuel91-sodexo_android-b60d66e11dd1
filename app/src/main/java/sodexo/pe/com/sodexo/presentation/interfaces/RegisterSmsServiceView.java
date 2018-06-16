package sodexo.pe.com.sodexo.presentation.interfaces;

import sodexo.pe.com.sodexo.domain.entity.CellInfoEntity;

/**
 * Created by RONALD on 13/10/2016.
 */

public interface RegisterSmsServiceView {
    void showLoading();

    void hideLoading();

    void showError(String message);

    void showInfo(CellInfoEntity entity);

    void showSuccess(String message);
}
