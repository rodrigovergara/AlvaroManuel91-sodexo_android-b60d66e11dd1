package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.LocalFilterEntity;


public interface GetFilterLocalInterface {
    void onGetFilterLocalSuccess(List<LocalFilterEntity> list);

    void onGetFilterLocalError(String message);
}
