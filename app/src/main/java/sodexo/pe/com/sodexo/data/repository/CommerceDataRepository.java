package sodexo.pe.com.sodexo.data.repository;

import java.util.List;

import sodexo.pe.com.sodexo.data.datasource.rest.RestCommerceDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.CommerceDataStore;
import sodexo.pe.com.sodexo.data.mapper.CommerceDataMapper;
import sodexo.pe.com.sodexo.data.model.CommerceEntityData;
import sodexo.pe.com.sodexo.domain.repository.CommerceRepository;
import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;
import sodexo.pe.com.sodexo.presentation.model.GetCommerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetFilterComerceInterface;

/**
 * Created by RONALD on 10/10/2016.
 */

public class CommerceDataRepository implements CommerceRepository {

    private final CommerceDataMapper commerceDataMapper;

    public CommerceDataRepository(CommerceDataMapper commerceDataMapper) {
        this.commerceDataMapper = commerceDataMapper;
    }
    @Override
    public void getCommerces(final GetCommerceInterface callback) {
        CommerceDataStore dataStore = new RestCommerceDataStore();
        dataStore.getAllCommerces(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onGetCommerceError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                if (object != null) {
                    callback.onGetCommerceSuccess(commerceDataMapper.transformToCommerceEntityList((List<CommerceEntityData>) object));
                }
            }
        });
    }

    @Override
    public void getFilteredCommerces(String query, final GetFilterComerceInterface callback) {
        CommerceDataStore dataStore = new RestCommerceDataStore();
        dataStore.getFilterCommerces(query,new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onGetFilterCommerceError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                if (object != null) {
                    callback.onGetFilterCommerceSuccess(commerceDataMapper.transformToCommerceEntityList((List<CommerceEntityData>) object));
                }
            }
        });
    }


}
