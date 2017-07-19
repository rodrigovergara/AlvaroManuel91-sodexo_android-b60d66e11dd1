package sodexo.pe.com.sodexo.data.repository;

import java.util.List;

import sodexo.pe.com.sodexo.data.datasource.rest.RestPromoDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.PromoDataStore;
import sodexo.pe.com.sodexo.data.mapper.PromoDataMapper;
import sodexo.pe.com.sodexo.data.model.PromoEntityData;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.domain.repository.PromoRepository;
import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;
import sodexo.pe.com.sodexo.presentation.model.GetFilterPromoInterface;
import sodexo.pe.com.sodexo.presentation.model.GetPromosInterface;

/**
 * Created by RONALD on 09/10/2016.
 */

public class PromoDataRepository implements PromoRepository {
    private final PromoDataMapper promoDataMapper;

    public PromoDataRepository(PromoDataMapper promoDataMapper) {
        this.promoDataMapper = promoDataMapper;
    }

    @Override
    public void getAllPromos(final GetPromosInterface callback) {
        PromoDataStore dataStore = new RestPromoDataStore();
        dataStore.getAllPromos(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onGetPromoError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                if (object != null) {
                    callback.onGetPromoSuccess(promoDataMapper.transformToPromoEntityList((List<PromoEntityData>) object));
                }
            }
        });
    }

    @Override
    public void getFilteredPromo(String query, final GetFilterPromoInterface getFilterPromoInterface) {
        PromoDataStore dataStore = new RestPromoDataStore();
        dataStore.getFilteredPromos(query, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                getFilterPromoInterface.onGetFilterPromoError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                getFilterPromoInterface.onGetFilterPromoSuccess(promoDataMapper.transformToPromoEntityList((List<PromoEntityData>) object));
            }
        });
    }
}
