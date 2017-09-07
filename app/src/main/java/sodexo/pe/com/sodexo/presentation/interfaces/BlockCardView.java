package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.BlockingReasonEntity;
import sodexo.pe.com.sodexo.domain.entity.CardDetailEntity;

/**
 * Created by asahel on 8/17/17.
 */

public interface BlockCardView extends ViewCreditView{

    void onBlockCardSuccess(String message);
    void populateReasonsSpinner(List<BlockingReasonEntity> list);
}
