package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.domain.entity.CardTypeEntity;
import sodexo.pe.com.sodexo.presentation.interfaces.GetAllMovements;
import sodexo.pe.com.sodexo.presentation.model.GetAllTypeCards;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface LastMovementsInteractor {
    void getAllCardsType(GetAllTypeCards callback);

    void getMovements(CardTypeEntity typeEntity, GetAllMovements callback);
}
