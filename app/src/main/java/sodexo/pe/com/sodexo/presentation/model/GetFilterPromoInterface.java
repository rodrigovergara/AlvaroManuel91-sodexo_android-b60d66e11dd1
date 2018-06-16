package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;

/**
 * Created by Alejandra on 3/01/2017.
 */
public interface GetFilterPromoInterface {
    void onGetFilterPromoSuccess(List<PromoEntity> list);

    void onGetFilterPromoError(String message);
}
