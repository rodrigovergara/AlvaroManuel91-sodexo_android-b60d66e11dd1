package sodexo.pe.com.sodexo.presentation.presenter;

import sodexo.pe.com.sodexo.domain.entity.CardEntity;

/**
 * Created by asahel on 8/29/17.
 */

public interface ReplaceCardPresenter extends BlockCardPresenter {

    void getReplacementCardNumbers();

    void getCardDetail(CardEntity cardEntity);
}
