package sodexo.pe.com.sodexo.domain.repository;

import sodexo.pe.com.sodexo.presentation.model.AddWishListInterface;
import sodexo.pe.com.sodexo.presentation.model.DeleteWishListInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCommerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetPromosInterface;

/**
 * Created by RONALD on 14/10/2016.
 */

public interface WishListRepository {
    void addToWishList(String dni, int asociateId,int promo, AddWishListInterface addWishListInterface);

    void getPromosFromWishList(String dni, GetPromosInterface getPromosInterface);

    void getCommercesFromWishList(String dni, GetCommerceInterface getCommerceInterface);

    void deleteFromWishList(String dni, int commerceId, int promoId, DeleteWishListInterface deleteWishListInterface);
}
