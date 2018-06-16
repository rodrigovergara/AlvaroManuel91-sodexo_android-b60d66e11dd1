package sodexo.pe.com.sodexo.data.repository;

import java.util.List;

import sodexo.pe.com.sodexo.data.datasource.rest.RestGoogleDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.GoogleDataStore;
import sodexo.pe.com.sodexo.data.mapper.GoogleDataMapper;
import sodexo.pe.com.sodexo.domain.entity.PlaceEntity;
import sodexo.pe.com.sodexo.domain.repository.GoogleRepository;

/**
 * Created by RONALD on 10/10/2016.
 */

public class GoogleDataRepository implements GoogleRepository{

    @Override
    public List<PlaceEntity> getAllAddress(String address) {
        GoogleDataStore googleDataStore = new RestGoogleDataStore();
        return GoogleDataMapper.transformPlaceResultDataToPlaceEntity(googleDataStore.getAllAddress(address));
    }
}
