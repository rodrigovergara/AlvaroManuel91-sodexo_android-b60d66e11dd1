package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.BlockingReasonEntity;
import sodexo.pe.com.sodexo.domain.entity.CardDetailEntity;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;

/**
 * Created by asahel on 8/17/17.
 */

public interface ReplaceCardView extends BaseParentView {

    void onBlockCardSuccess(String message);
    void populateReplacementCards(List<CardEntity> list);
    void populateReasonsSpinner(List<BlockingReasonEntity> list);
    void showCardDetail(CardDetailEntity cardDetail);

}
