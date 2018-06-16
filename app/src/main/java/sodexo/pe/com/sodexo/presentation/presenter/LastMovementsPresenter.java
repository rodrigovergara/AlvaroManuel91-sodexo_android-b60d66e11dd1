package sodexo.pe.com.sodexo.presentation.presenter;

import sodexo.pe.com.sodexo.domain.entity.CardTypeEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface LastMovementsPresenter {
    void getTypeCards();

    void getMovements(CardTypeEntity cardTypeEntity);
}
