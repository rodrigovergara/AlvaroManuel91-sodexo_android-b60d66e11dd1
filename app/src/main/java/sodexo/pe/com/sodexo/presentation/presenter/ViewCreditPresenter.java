package sodexo.pe.com.sodexo.presentation.presenter;

import sodexo.pe.com.sodexo.domain.entity.CardEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface ViewCreditPresenter {
    void getNumberCards();

    void getCardDetail(CardEntity cardEntity);
}
