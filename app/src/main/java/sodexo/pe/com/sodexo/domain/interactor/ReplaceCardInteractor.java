package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.presentation.model.GetBlockingReasonsInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardDetailInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardsInterface;

/**
 * Created by asahel on 8/30/17.
 */

public interface ReplaceCardInteractor {

    void getReplacementCardNumbers(GetCardsInterface callback);

    void getBlockingReasons(GetBlockingReasonsInterface callback);

    void getCardDetail(CardEntity cardEntity, GetCardDetailInterface getCardDetailInterface);

}
