package sodexo.pe.com.sodexo.data.repository;

import java.util.List;

import sodexo.pe.com.sodexo.data.datasource.rest.RestWishlistDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.WishListDataStore;
import sodexo.pe.com.sodexo.data.mapper.CommerceDataMapper;
import sodexo.pe.com.sodexo.data.mapper.PromoDataMapper;
import sodexo.pe.com.sodexo.data.model.CommerceEntityData;
import sodexo.pe.com.sodexo.data.model.PromoEntityData;
import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;
import sodexo.pe.com.sodexo.domain.repository.WishListRepository;
import sodexo.pe.com.sodexo.presentation.model.AddWishListInterface;
import sodexo.pe.com.sodexo.presentation.model.DeleteWishListInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCommerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetPromosInterface;

/**
 * Created by RONALD on 14/10/2016.
 */

public class WishListDataRepository implements WishListRepository {
    @Override
    public void addToWishList(String dni, int asociateId, int promoid, final AddWishListInterface addWishListInterface) {
        WishListDataStore dataStore = new RestWishlistDataStore();
        dataStore.addToWishList(dni, asociateId, promoid, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                addWishListInterface.onAddWishListError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                addWishListInterface.onAddWishListSuccess();
            }
        });
    }

    @Override
    public void getPromosFromWishList(String dni, final GetPromosInterface getPromosInterface) {
        WishListDataStore dataStore = new RestWishlistDataStore();
        final PromoDataMapper dataMapper = new PromoDataMapper();
        dataStore.getPromosFromWishList(dni, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                getPromosInterface.onGetPromoError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                getPromosInterface.onGetPromoSuccess(dataMapper.transformToPromoEntityList((List<PromoEntityData>) object));
            }
        });
    }

    @Override
    public void getCommercesFromWishList(String dni, final GetCommerceInterface getCommerceInterface) {
        WishListDataStore dataStore = new RestWishlistDataStore();
        final CommerceDataMapper dataMapper = new CommerceDataMapper();
        dataStore.getCommercesFromWishList(dni, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                getCommerceInterface.onGetCommerceError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                getCommerceInterface.onGetCommerceSuccess(dataMapper.transformToCommerceEntityList((List<CommerceEntityData>) object));
            }
        });
    }

    @Override
    public void deleteFromWishList(String dni, int commerceId, int promoId, final DeleteWishListInterface deleteWishListInterface) {
        WishListDataStore dataStore = new RestWishlistDataStore();
        dataStore.deleteFromWishList(dni, commerceId, promoId, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                deleteWishListInterface.onDeleteWishListError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                deleteWishListInterface.onDeleteWishListSuccess();
            }
        });
    }
}
