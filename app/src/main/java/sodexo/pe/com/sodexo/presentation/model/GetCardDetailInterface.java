package sodexo.pe.com.sodexo.presentation.model;

import sodexo.pe.com.sodexo.domain.entity.CardDetailEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface GetCardDetailInterface {
    void onGetCardDetailSuccess(CardDetailEntity cardDetail);

    void onGetCardDetailError(String message);
}
