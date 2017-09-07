package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.presentation.model.BaseParentInterface;
import sodexo.pe.com.sodexo.presentation.model.GetBlockingReasonsInterface;
import sodexo.pe.com.sodexo.presentation.model.UpdateUserInfoOnterface;

/**
 * Created by asahel on 8/16/17.
 */

public interface BlockCardInteractor {

    void blockCard(String cardNumber,String reasonId, BaseParentInterface baseParentInterface);

    void getBlockingReasons(GetBlockingReasonsInterface callback);
}
