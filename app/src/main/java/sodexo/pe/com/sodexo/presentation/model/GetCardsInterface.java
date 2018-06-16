package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.CardEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface GetCardsInterface {
    void onGetCardsSucces(List<CardEntity> list);

    void onGetCardsError(String message) ;
}
