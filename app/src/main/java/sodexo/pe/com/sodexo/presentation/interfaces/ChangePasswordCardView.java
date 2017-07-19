package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.CardEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface ChangePasswordCardView {
    void showLoading();

    void hideLoading();

    void populateSpinner(List<CardEntity> list);

    void showError(String message);
}
