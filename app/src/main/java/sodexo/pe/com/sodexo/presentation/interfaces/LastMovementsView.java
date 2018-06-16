package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.CardTypeEntity;
import sodexo.pe.com.sodexo.domain.entity.MovementEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface LastMovementsView {
    void showLoadig();

    void hideLoading();

    void populateCardsType(List<CardTypeEntity> list);

    void showMovements(List<MovementEntity> list);

    void showError(String message);
}
