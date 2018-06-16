package sodexo.pe.com.sodexo.data.repository;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import sodexo.pe.com.sodexo.data.datasource.rest.RestFilterLocalDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.FilterLocalDataStore;
import sodexo.pe.com.sodexo.data.mapper.FilterLocalDataMapper;
import sodexo.pe.com.sodexo.data.model.FilterLocalEntityData;
import sodexo.pe.com.sodexo.domain.repository.FilterLocalRepository;
import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;
import sodexo.pe.com.sodexo.presentation.model.GetFilterLocalInterface;

/**
 * Created by RONALD on 11/10/2016.
 */

public class FilterLocalDatRepository implements FilterLocalRepository {
    private FilterLocalDataMapper filterLocalDataMapper;

    public FilterLocalDatRepository(FilterLocalDataMapper filterLocalDataMapper) {
        this.filterLocalDataMapper = filterLocalDataMapper;
    }

    @Override
    public void getFilterLocal(LatLng latLng, int id, final GetFilterLocalInterface getFilterLocalInterface) {
        FilterLocalDataStore dataStore = new RestFilterLocalDataStore();
        dataStore.getFilterLocal(id, latLng, "", 0, 2000, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                getFilterLocalInterface.onGetFilterLocalError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                getFilterLocalInterface.onGetFilterLocalSuccess(filterLocalDataMapper.trasnformToLocalFilterEntity((List<FilterLocalEntityData>) object));
            }
        });
    }

    @Override
    public void getCommerceByUbigeo(String ubigeoCode, final GetFilterLocalInterface getFilterLocalInterface) {
        // Refactor, solo un llamado no varios
        FilterLocalDataStore dataStore = new RestFilterLocalDataStore();
        dataStore.getCommerceByUbigeo(ubigeoCode, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                getFilterLocalInterface.onGetFilterLocalError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                getFilterLocalInterface.onGetFilterLocalSuccess(filterLocalDataMapper.trasnformToLocalFilterEntity((List<FilterLocalEntityData>) object));
            }
        });
    }

    @Override
    public void getCommerceByUbigeo(String ubigeoCode, int typeCommerce, final GetFilterLocalInterface getFilterLocalInterface) {
        FilterLocalDataStore dataStore = new RestFilterLocalDataStore();
        dataStore.getCommerceByUbigeo(ubigeoCode, typeCommerce, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                getFilterLocalInterface.onGetFilterLocalError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                getFilterLocalInterface.onGetFilterLocalSuccess(filterLocalDataMapper.trasnformToLocalFilterEntity((List<FilterLocalEntityData>) object));
            }
        });
    }

    @Override
    public void getCommerceByRouletteOptions(int distance, int categoryId, int stars, final GetFilterLocalInterface getFilterLocalInterface) {
        FilterLocalDataStore dataStore = new RestFilterLocalDataStore();
        dataStore.getCommerceByRouletteOptions(distance, categoryId, stars, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                getFilterLocalInterface.onGetFilterLocalError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                getFilterLocalInterface.onGetFilterLocalSuccess(filterLocalDataMapper.trasnformToLocalFilterEntity((List<FilterLocalEntityData>) object));
            }
        });
    }
}
