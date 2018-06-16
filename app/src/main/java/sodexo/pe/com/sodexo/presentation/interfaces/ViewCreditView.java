package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.CardDetailEntity;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface ViewCreditView extends BaseParentView{

    void populateReplacementCards(List<CardEntity> list);

    void showCardDetail(CardDetailEntity cardDetail);
}
