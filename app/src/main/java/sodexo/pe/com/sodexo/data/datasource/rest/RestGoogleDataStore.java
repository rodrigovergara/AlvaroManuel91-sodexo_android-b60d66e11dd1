package sodexo.pe.com.sodexo.data.datasource.rest;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.GoogleDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.retrofit.GoogleApiclient;
import sodexo.pe.com.sodexo.data.model.PlaceResultData;
import sodexo.pe.com.sodexo.data.model.PlacesResponse;

/**
 * Created by RONALD on 10/10/2016.
 */

public class RestGoogleDataStore implements GoogleDataStore {
    @Override
    public List<PlaceResultData> getAllAddress(String address) {
        try {
            Response<PlacesResponse> response = GoogleApiclient.getGoogleApiClient().getAllAddress(address, "administrative_area:Lima|country:Peru").execute();
            if (response.isSuccessful()) {
                return response.body().getResults();
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
