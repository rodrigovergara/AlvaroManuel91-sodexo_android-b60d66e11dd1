package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.presentation.model.GetCardDetailInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardsInterface;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface ViewCreditInteractor {
    void getCards(GetCardsInterface callback);

    void getDetailCard(CardEntity cardEntity, GetCardDetailInterface getCardDetailInterface);
}
