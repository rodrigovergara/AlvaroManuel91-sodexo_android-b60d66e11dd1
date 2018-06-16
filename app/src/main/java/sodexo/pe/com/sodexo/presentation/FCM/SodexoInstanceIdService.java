package sodexo.pe.com.sodexo.presentation.FCM;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sodexo.pe.com.sodexo.data.datasource.rest.retrofit.ApiClient;
import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.data.model.TokenResponse;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;

/**
 * Created by Alejandra on 1/02/2017.
 */
public class SodexoInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCM_TOKEN", "Refreshed token: " + refreshedToken);
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            Log.d("primero",json);
            if(!TextUtils.isEmpty(data.getDni())){
                Map<String, String> params = new HashMap<String, String>();
                params.put("Usuario", data.getDni());
                params.put("AppToken", refreshedToken);
                params.put("Dispositivo","AND");
                Call<TokenResponse> call = ApiClient.getSodexoApiClient().postAppToken(params);
                call.enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        if(response.body().getUserId()!=0){
                            Log.d("refresh_token","sent from service");
                        }
                        else{
                            Log.d("refresh_token","error sent from service");
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {

                    }
                });
            }
        }

    }
}
