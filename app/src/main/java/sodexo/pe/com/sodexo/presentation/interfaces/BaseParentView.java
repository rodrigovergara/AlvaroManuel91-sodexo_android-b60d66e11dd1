package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.CardDetailEntity;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;

/**
 * Created by asahel on 16/06/2017.
 */

public interface BaseParentView {
    void showLoading();

    void hideLoading();

    void showError(String message);
    
}
