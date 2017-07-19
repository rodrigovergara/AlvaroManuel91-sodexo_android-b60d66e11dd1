package sodexo.pe.com.sodexo.data.datasource.rest.retrofit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sodexo.pe.com.sodexo.data.model.BlogEntityData;
import sodexo.pe.com.sodexo.data.model.CommerceEntityData;
import sodexo.pe.com.sodexo.data.model.FilterLocalEntityData;
import sodexo.pe.com.sodexo.data.model.IntranetOptionEntityData;
import sodexo.pe.com.sodexo.data.model.PromoEntityData;
import sodexo.pe.com.sodexo.data.model.QuizDetailEntityData;
import sodexo.pe.com.sodexo.data.model.QuizEntityData;
import sodexo.pe.com.sodexo.data.model.ServiceResponse;
import sodexo.pe.com.sodexo.data.model.TokenResponse;
import sodexo.pe.com.sodexo.data.model.ValidatedQuizResponse;

public interface SodexoServiceInterface {

    @GET("GetPromociones")
    Call<List<PromoEntityData>> getPromo(@Query("q") String q, @Query("CategoriaId") int categoryId);

    @GET("GetAsociados")
    Call<List<CommerceEntityData>> getCommerce(@Query("q") String q, @Query("CategoriaId") int categoryId, @Query("DistritoId") int districtId);

    @GET("GetAsociadosFiltrado")
    Call<List<FilterLocalEntityData>> getFilterLocal(@Query("Descripcion") String description, @Query("CategoriaId") int categoryId, @Query("UbigeoDistrito") String ubigeo, @Query("Lat") double latitude, @Query("Lon") double longitude, @Query("Radio") int radio);

    @POST("PostAgregarWishlist")
    @FormUrlEncoded
    Call<ServiceResponse<Object>> addToWishList(@FieldMap HashMap<String, Object> params);

    @POST("PostEliminarWishlist")
    @FormUrlEncoded
    Call<ServiceResponse<Object>> deleteToWishList(@FieldMap HashMap<String, Object> params);

    @GET("GetWishlistPromocion")
    Call<List<PromoEntityData>> getPromoWishList(@Query("Usuario") String dni);

    @GET("GetWishlistAsociados")
    Call<List<CommerceEntityData>> getCommerceWishList(@Query("Usuario") String dni);

    @GET("GetOpcionesIntranet")
    Call<List<IntranetOptionEntityData>> getIntranetOption(@Query("Usuario") String userId);

    @GET("GetEntradasBlog")
    Call<List<BlogEntityData>> getBlogs(@Query("Usuario") String userId);

    @GET("GetEncuestas")
    Call<List<QuizEntityData>> getQuizzes(@Query("Usuario") String usuarioId);

    @GET("GetDetalleEncuesta")
    Call<QuizDetailEntityData> getQuizDetail(@Query("Usuario") String usuarioId, @Query("EncuestaId") int quizId);

    @POST("PostRegistroUsuarioConTarjeta")
    @FormUrlEncoded
    Call<ServiceResponse<Object>> registerWithCard(@FieldMap HashMap<String, Object> param);

    @POST("PostRegistroUsuarioSinTarjeta")
    @FormUrlEncoded
    Call<ServiceResponse<Object>> registerWithoutCard(@FieldMap HashMap<String, Object> param);

    @POST("PostResolverEncuesta")
    Call<ValidatedQuizResponse> sendResponse(@Body HashMap<String, Object> params);

    @POST("PostAppToken")
    @FormUrlEncoded
    Call<TokenResponse> postAppToken(@FieldMap Map<String, String> params);
}
