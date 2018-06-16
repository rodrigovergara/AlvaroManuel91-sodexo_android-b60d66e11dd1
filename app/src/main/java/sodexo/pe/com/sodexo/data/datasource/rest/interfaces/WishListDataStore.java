package sodexo.pe.com.sodexo.data.datasource.rest.interfaces;

import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;

/**
 * Created by RONALD on 14/10/2016.
 */

public interface WishListDataStore {
    void addToWishList(String dni, int asociateId, int promoId, RepositoryCallback callback);

    void deleteFromWishList(String dni, int commerceId, int promoId, RepositoryCallback callback);

    void getPromosFromWishList(String dni, RepositoryCallback callback);

    void getCommercesFromWishList(String dni, RepositoryCallback callback);
}
