package sodexo.pe.com.sodexo.presentation.presenter;

import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;

/**
 * Created by RONALD on 14/10/2016.
 */

public interface WishListPresenter {
    void addLocalToWishlist(CommerceEntity commerceEntity);

    void addPromoToWishlist(PromoEntity promoEntity);

    void getCommerceWishList();

    void getPromoWishList();

    void deleteLocalToWishlist(CommerceEntity commerceEntity);

    void deletePromoToWishList(PromoEntity promoEntity);
}
