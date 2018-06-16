package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.CardTypeEntity;
import sodexo.pe.com.sodexo.domain.entity.MovementEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface GetAllMovements {
    void onGetAllMovementsSuccess(List<MovementEntity> list);

    void onGetAllMovementsError(String message);
}
