package sodexo.pe.com.sodexo.presentation.presenter;

import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;

/**
 * Created by RONALD on 07/10/2016.
 */

public interface MainPresenter {

    void showPromo();

    void showCommerces();

    void showIntranet();

    void addToWishList(PromoEntity promo);

    void deletePromoToWishList(PromoEntity promo);

    void deleteCommerceToWishList(CommerceEntity commerce);
}
