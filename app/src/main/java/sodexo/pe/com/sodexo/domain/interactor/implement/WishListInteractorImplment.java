package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;

import com.google.gson.Gson;

import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.data.repository.WishListDataRepository;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.domain.interactor.WishListInteractor;
import sodexo.pe.com.sodexo.domain.repository.WishListRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.model.AddWishListInterface;
import sodexo.pe.com.sodexo.presentation.model.DeleteWishListInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCommerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetPromosInterface;

/**
 * Created by RONALD on 14/10/2016.
 */

public class WishListInteractorImplment implements WishListInteractor {
    private WishListRepository wishListRepository;

    public WishListInteractorImplment(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    @Override
    public void addLocalAtWishlist(int asociateId, AddWishListInterface addWishListInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            wishListRepository.addToWishList(data.getDni(), asociateId, 0,addWishListInterface);
        } else {
            addWishListInterface.onAddWishListError("Debe logearse en la aplicación para poder guardar este comercio.");
        }
    }

    @Override
    public void addPromoAtWishlist(int promoId, AddWishListInterface addWishListInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            wishListRepository.addToWishList(data.getDni(), 0, promoId,addWishListInterface);
        } else {
            addWishListInterface.onAddWishListError("Debe logearse en la aplicación para poder guardar esta promoción.");
        }
    }

    @Override
    public void getCommerceWishList(GetCommerceInterface getCommerceInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            wishListRepository.getCommercesFromWishList(data.getDni(), getCommerceInterface);
        }
    }

    @Override
    public void getPromoWishList(GetPromosInterface getPromosInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            wishListRepository.getPromosFromWishList(data.getDni(), getPromosInterface);
        }
    }

    @Override
    public void deleteLocalFromWishlist(CommerceEntity commerceEntity, DeleteWishListInterface deleteWishListInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            wishListRepository.deleteFromWishList(data.getDni(), commerceEntity.getId(), 0, deleteWishListInterface);
        } else {
            deleteWishListInterface.onDeleteWishListError("Debe logearse en la aplicación para poder eliminar este comercio.");
        }

    }

    @Override
    public void deletePromoFromWishlist(PromoEntity promoEntity, DeleteWishListInterface deleteWishListInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            wishListRepository.deleteFromWishList(data.getDni(), 0, promoEntity.getPromoId(), deleteWishListInterface);
        } else {
            deleteWishListInterface.onDeleteWishListError("Debe logearse en la aplicación para poder eliminar esta promoción.");
        }

    }
}
