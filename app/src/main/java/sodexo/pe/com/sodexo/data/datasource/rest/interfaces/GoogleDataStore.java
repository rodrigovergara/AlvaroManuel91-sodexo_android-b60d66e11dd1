package sodexo.pe.com.sodexo.data.datasource.rest.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.data.model.PlaceResultData;

/**
 * Created by RONALD on 10/10/2016.
 */
public interface GoogleDataStore {
    List<PlaceResultData> getAllAddress(String address);
}
