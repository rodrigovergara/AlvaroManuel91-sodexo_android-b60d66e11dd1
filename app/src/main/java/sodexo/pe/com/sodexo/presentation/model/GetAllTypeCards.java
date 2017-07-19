package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.CardTypeEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface GetAllTypeCards {
    void onGetAllTypeCardsSuccess(List<CardTypeEntity> list);

    void onGetAllTypeCardsError(String message);
}
