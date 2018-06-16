package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.presentation.model.AddWishListInterface;
import sodexo.pe.com.sodexo.presentation.model.DeleteWishListInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCommerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetPromosInterface;

/**
 * Created by RONALD on 14/10/2016.
 */

public interface WishListInteractor {
    void addLocalAtWishlist(int asociateId, AddWishListInterface addWishListInterface);

    void addPromoAtWishlist(int promoId, AddWishListInterface addWishListInterface);

    void getCommerceWishList(GetCommerceInterface getCommerceInterface);

    void getPromoWishList(GetPromosInterface getPromosInterface);

    void deleteLocalFromWishlist(CommerceEntity commerceEntity, DeleteWishListInterface deleteWishListInterface);

    void deletePromoFromWishlist(PromoEntity promoEntity, DeleteWishListInterface deleteWishListInterface);
}

