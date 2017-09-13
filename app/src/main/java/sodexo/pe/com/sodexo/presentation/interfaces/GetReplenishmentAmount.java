package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.ReplenishmentAmountEntity;

/**
 * Created by asahel on 9/8/17.
 */

public interface GetReplenishmentAmount {

    void onSuccess(List<ReplenishmentAmountEntity> list);

    void onError(String message);

}
