package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.BlockingReasonEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface GetBlockingReasonsInterface {
    void onGetBlockingReasonsSuccess(List<BlockingReasonEntity> list);

    void onGetBlockingReasonsError(String message) ;
}
