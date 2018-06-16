package sodexo.pe.com.sodexo.data.datasource.rest.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sodexo.pe.com.sodexo.data.model.PlacesResponse;

/**
 * Created by RONALD on 10/10/2016.
 */

public interface GoogleServiceInterface {
    @GET("json")
    Call<PlacesResponse> getAllAddress(@Query ("address") String address,
                                       @Query ("components") String components);
}
