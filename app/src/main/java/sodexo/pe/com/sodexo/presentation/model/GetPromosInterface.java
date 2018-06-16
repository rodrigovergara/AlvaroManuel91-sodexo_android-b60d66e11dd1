package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.PromoEntity;

public interface GetPromosInterface {
    void onGetPromoSuccess(List<PromoEntity> promo);

    void onGetPromoError(String message);
}
