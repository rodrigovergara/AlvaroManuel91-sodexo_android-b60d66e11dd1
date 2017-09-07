package sodexo.pe.com.sodexo.data.datasource.rest.retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sodexo.pe.com.sodexo.data.model.CardDetailEntityData;
import sodexo.pe.com.sodexo.data.model.CardEntityData;
import sodexo.pe.com.sodexo.data.model.CardTypeEntitiyData;
import sodexo.pe.com.sodexo.data.model.CellInfoEntityData;
import sodexo.pe.com.sodexo.data.model.LastMovementsResponse;
import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.data.model.BlockingReasonEntityData;
import sodexo.pe.com.sodexo.data.model.ServiceResponse;
import sodexo.pe.com.sodexo.data.model.UserEntityData;

/**
 * Created by RONALD on 07/10/2016.
 */

public interface SodexoIntranetServiceInterface {
    @POST("post/login")
    @FormUrlEncoded
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<ServiceResponse<LoginEntityData>> loginUser(@FieldMap Map<String, String> params);

    @GET("get/ListNumTar/{dni}")
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<ServiceResponse<List<CardEntityData>>> getCardsNumber(@Path("dni") String dni);

    @GET("get/ListNumTar/{dni}/{numberCard}")
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<CardDetailEntityData> getCreditCard(@Path("dni") String dni, @Path("numberCard") String numberCard);

    @GET("get/ConMovTar/{dni}")
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<ServiceResponse<List<CardTypeEntitiyData>>> getCardsType(@Path("dni") String dni);

    @GET("get/ConMovTar/{dni}/{cardType}")
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<LastMovementsResponse> getMovemenByCard(@Path("dni") String dni, @Path("cardType") int cardType);

    @GET("get/ListUsuario/{dni}/{ruc}")
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<UserEntityData> getUserData(@Path("dni") String dni, @Path("ruc") String ruc);

    @POST("post/ActulizarDatos")
    @FormUrlEncoded
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<ServiceResponse<Object>> updateUserData(@FieldMap Map<String, String> params);

    @POST("post/CambioClaveWeb")
    @FormUrlEncoded
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<ServiceResponse<Object>> changeWebPassword(@FieldMap Map<String, String> params);

    @POST("post/ActClaTar")
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    @FormUrlEncoded
    Call<ServiceResponse<Object>> changeCardPassword(@FieldMap Map<String, String> params);

    @GET("get/VerInfoTelefono/{dni}")
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<ServiceResponse<CellInfoEntityData>> getPhoneData(@Path("dni") String dni);

    @POST("post/GuardarInfoTelefono")
    @FormUrlEncoded
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<ServiceResponse<Object>> updateCellInfo(@FieldMap Map<String, String> params);

    @POST("post/ReposicionesNuevo")
    Call<ServiceResponse<Object>> replaceCard(@FieldMap Map<String, String> params);

    @GET("get/BloqueoTarjeta/{cardNumber}/{reasonId}")
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<ServiceResponse<Object>> blockCard(/*@FieldMap Map<String, String> params*/@Path("cardNumber") String cardNumber,@Path("reasonId") String reasonId);

    @GET("get/ListNumTarRepo/{dni}")
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<ServiceResponse<List<CardEntityData>>> getReplacementCardNumbers(@Path("dni") String dni);

    @GET("get/MotivoBloq/")
    @Headers({"X-API-KEY: 89cae64e572daa4b4e5dbd95edf4dd90"})
    Call<List<BlockingReasonEntityData>>getBlockingReasons();
}
