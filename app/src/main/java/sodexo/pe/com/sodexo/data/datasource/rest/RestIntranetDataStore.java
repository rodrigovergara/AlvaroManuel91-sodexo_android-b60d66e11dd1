package sodexo.pe.com.sodexo.data.datasource.rest;

import android.util.ArrayMap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.IntranetDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.retrofit.ApiClient;
import sodexo.pe.com.sodexo.data.model.BlogEntityData;
import sodexo.pe.com.sodexo.data.model.CardDetailEntityData;
import sodexo.pe.com.sodexo.data.model.CardEntityData;
import sodexo.pe.com.sodexo.data.model.CardTypeEntitiyData;
import sodexo.pe.com.sodexo.data.model.CellInfoEntityData;
import sodexo.pe.com.sodexo.data.model.IntranetOptionEntityData;
import sodexo.pe.com.sodexo.data.model.LastMovementsResponse;
import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.data.model.QuizDetailEntityData;
import sodexo.pe.com.sodexo.data.model.QuizEntityData;
import sodexo.pe.com.sodexo.data.model.QuizResponseEntityData;
import sodexo.pe.com.sodexo.data.model.ServiceResponse;
import sodexo.pe.com.sodexo.data.model.UserEntityData;
import sodexo.pe.com.sodexo.data.model.ValidatedQuizResponse;
import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;

/**
 * Created by RONALD on 11/10/2016.
 */

public class RestIntranetDataStore implements IntranetDataStore {

    @Override
    public void login(String dni, String password, final RepositoryCallback repositoryCallback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("dni", dni);
        params.put("clave", password);
        Call<ServiceResponse<LoginEntityData>> call = ApiClient.getSodexoIntranetApiClient().loginUser(params);
        call.enqueue(new Callback<ServiceResponse<LoginEntityData>>() {
            @Override
            public void onResponse(Call<ServiceResponse<LoginEntityData>> call, Response<ServiceResponse<LoginEntityData>> response) {
                if (response.isSuccessful()) {
                    repositoryCallback.onSuccess(response.body().getData());
                } else {
                    repositoryCallback.onError("El usuario y/o password son incorrectos");
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<LoginEntityData>> call, Throwable t) {
                repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
            }
        });
    }

    @Override
    public void getCards(String dni, final RepositoryCallback repositoryCallback) {
        Call<ServiceResponse<List<CardEntityData>>> call = ApiClient.getSodexoIntranetApiClient().getCardsNumber(dni);
        call.enqueue(new Callback<ServiceResponse<List<CardEntityData>>>() {
            @Override
            public void onResponse(Call<ServiceResponse<List<CardEntityData>>> call, Response<ServiceResponse<List<CardEntityData>>> response) {
                if (response.isSuccessful()) {
                    repositoryCallback.onSuccess(response.body().getData());
                } else {
                    repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<List<CardEntityData>>> call, Throwable t) {
                repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
            }
        });
    }

    @Override
    public void getCardDetail(String dni, String cardNumber, final RepositoryCallback callback) {
        Call<CardDetailEntityData> call = ApiClient.getSodexoIntranetApiClient().getCreditCard(dni, cardNumber);
        call.enqueue(new Callback<CardDetailEntityData>() {
            @Override
            public void onResponse(Call<CardDetailEntityData> call, Response<CardDetailEntityData> response) {
                if (response.isSuccessful()) {
                    try {
                        callback.onSuccess(response.body());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        callback.onError("Tarjeta Bloqueada");
                    }
                } else {
                    callback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                }

            }

            @Override
            public void onFailure(Call<CardDetailEntityData> call, Throwable t) {
                callback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
            }
        });
    }

    @Override
    public void getCardsType(String dni, final RepositoryCallback repositoryCallback) {
        Call<ServiceResponse<List<CardTypeEntitiyData>>> call = ApiClient.getSodexoIntranetApiClient().getCardsType(dni);
        call.enqueue(new Callback<ServiceResponse<List<CardTypeEntitiyData>>>() {
            @Override
            public void onResponse(Call<ServiceResponse<List<CardTypeEntitiyData>>> call, Response<ServiceResponse<List<CardTypeEntitiyData>>> response) {
                if (response.isSuccessful()) {
                    try {
                        repositoryCallback.onSuccess(response.body().getData());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                    }
                } else {
                    repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                }

            }

            @Override
            public void onFailure(Call<ServiceResponse<List<CardTypeEntitiyData>>> call, Throwable t) {
                repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
            }
        });
    }

    @Override
    public void getLastMovements(String dni, String cardTypeId, final RepositoryCallback callback) {
        Call<LastMovementsResponse> call = ApiClient.getSodexoIntranetApiClient().getMovemenByCard(dni, Integer.valueOf(cardTypeId));
        call.enqueue(new Callback<LastMovementsResponse>() {
            @Override
            public void onResponse(Call<LastMovementsResponse> call, Response<LastMovementsResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        callback.onSuccess(response.body());
                    } catch (Exception ex) {
                        callback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                    }
                } else {
                    callback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                }


            }

            @Override
            public void onFailure(Call<LastMovementsResponse> call, Throwable t) {
                callback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
            }
        });
    }

    @Override
    public void changePasswordWeb(String dni, String password, String newPassword, final RepositoryCallback repositoryCallback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("dni", dni);
        params.put("ClaveActualTextBox", password);
        params.put("ClaveNuevaTextBox", newPassword);
        params.put("ReClaveNuevaTextBox", newPassword);
//        params.put("X-API-KEY", "89cae64e572daa4b4e5dbd95edf4dd90");
        Call<ServiceResponse<Object>> call = ApiClient.getSodexoIntranetApiClient().changeWebPassword(params);
        call.enqueue(new Callback<ServiceResponse<Object>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Object>> call, Response<ServiceResponse<Object>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        repositoryCallback.onError(response.body().getMessage());
                    } else {
                        repositoryCallback.onSuccess(response.body());
                    }
                } else {
                    repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                }

            }

            @Override
            public void onFailure(Call<ServiceResponse<Object>> call, Throwable t) {
                repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
            }
        });
    }

    @Override
    public void changePasswordCard(String dni, String cardNumber, String password, String newPassword, String repeatPassword, final RepositoryCallback repositoryCallback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("dni", dni);
        params.put("tarjeta", cardNumber);
        params.put("ClaveActual", password);
        params.put("ClaveNueva", newPassword);
        params.put("ClaveNueva2", repeatPassword);
        Call<ServiceResponse<Object>> call = ApiClient.getSodexoIntranetApiClient().changeCardPassword(params);
        call.enqueue(new Callback<ServiceResponse<Object>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Object>> call, Response<ServiceResponse<Object>> response) {
                if (response.isSuccessful()) {
                    repositoryCallback.onSuccess(response.body());
                } else {
                    repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                }

            }

            @Override
            public void onFailure(Call<ServiceResponse<Object>> call, Throwable t) {
                repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
            }
        });
    }

    @Override
    public void getUserInfo(String dni, String ruc, final RepositoryCallback callback) {
        Call<UserEntityData> call = ApiClient.getSodexoIntranetApiClient().getUserData(dni, ruc);
        call.enqueue(new Callback<UserEntityData>() {
            @Override
            public void onResponse(Call<UserEntityData> call, Response<UserEntityData> response) {
                try {
                    callback.onSuccess(response.body());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    callback.onError("Error");
                }
            }

            @Override
            public void onFailure(Call<UserEntityData> call, Throwable t) {
                callback.onError("Error");
            }
        });
    }

    @Override
    public void updateInfo(String dni, String address, String department, String province, String district, String email, String phone, String cellPhone, String cargo, String sex, final RepositoryCallback repositoryCallback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("dni", dni);
        params.put("DireccionTextBox", address);
        params.put("ProvinciaDropDownList", province);
        params.put("DepartamentoDropDownList", department);
        params.put("DistritoDropDownList", district);
        params.put("CorreoElectronicoTextBox", email);
        params.put("TelefonoFijoTextBox", phone);
        params.put("TelefonoCelularTextBox", cellPhone);
        params.put("CargoDropDownList", cargo);
        params.put("SexoDropDownList", sex);
        Log.d("PersonalInfo",params.toString());
        Call<ServiceResponse<Object>> call = ApiClient.getSodexoIntranetApiClient().updateUserData(params);
        call.enqueue(new Callback<ServiceResponse<Object>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Object>> call, Response<ServiceResponse<Object>> response) {
                if (response.body().isError()) {
                    repositoryCallback.onError(response.body().getMessage());
                } else {
                    repositoryCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Object>> call, Throwable t) {
                repositoryCallback.onError("Error");
            }
        });
    }

    @Override
    public void getCellInfo(String dni, final RepositoryCallback callback) {
        Call<ServiceResponse<CellInfoEntityData>> call = ApiClient.getSodexoIntranetApiClient().getPhoneData(dni);
        call.enqueue(new Callback<ServiceResponse<CellInfoEntityData>>() {
            @Override
            public void onResponse(Call<ServiceResponse<CellInfoEntityData>> call, Response<ServiceResponse<CellInfoEntityData>> response) {
                if (response.body().isError()) {
                    callback.onError(response.body().getMessage());
                } else {
                    callback.onSuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<CellInfoEntityData>> call, Throwable t) {
                callback.onError("Error");
            }
        });

    }

    @Override
    public void updateCellInfo(String dni, String cellphoneNumber, String operator, String active, final RepositoryCallback repositoryCallback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("dni", dni);
        params.put("TelefonoCelularTextBox", cellphoneNumber);
        params.put("operador", operator);
        params.put("ActivoRadioButtonList", active);
//        params.put("X-API-KEY", "89cae64e572daa4b4e5dbd95edf4dd90");
        Call<ServiceResponse<Object>> call = ApiClient.getSodexoIntranetApiClient().updateCellInfo(params);
        call.enqueue(new Callback<ServiceResponse<Object>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Object>> call, Response<ServiceResponse<Object>> response) {
                if (response.body().isError()) {
                    repositoryCallback.onError(response.body().getMessage());
                } else {
                    repositoryCallback.onSuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Object>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getOptionsnew(String userId, final RepositoryCallback repositoryCallback) {
        Call<List<IntranetOptionEntityData>> call = ApiClient.getSodexoApiClient().getIntranetOption(userId);
        call.enqueue(new Callback<List<IntranetOptionEntityData>>() {
            @Override
            public void onResponse(Call<List<IntranetOptionEntityData>> call, Response<List<IntranetOptionEntityData>> response) {
                if (response.body() != null) {
                    repositoryCallback.onSuccess(response.body());
                } else {
                    repositoryCallback.onError("Ocurrio un error. Intentelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<List<IntranetOptionEntityData>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getListBlog(String userId, final RepositoryCallback callback) {
        Call<List<BlogEntityData>> call = ApiClient.getSodexoApiClient().getBlogs(userId);
        call.enqueue(new Callback<List<BlogEntityData>>() {
            @Override
            public void onResponse(Call<List<BlogEntityData>> call, Response<List<BlogEntityData>> response) {
                if (response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Ocurrio un error. Intentelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<List<BlogEntityData>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getRegisterWithCard(String dni, String card, String password, String confirmPassword, String email, final RepositoryCallback callback) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("DNI", dni);
        param.put("CorreoElectronico", email);
        param.put("NumTarjeta", card);
        param.put("ClaveWeb", password);
        param.put("ReClaveWeb", confirmPassword);
        param.put("AppToken", "asdasdasdasdasdsdasdasd");
        param.put("Dispositivo", "AND");


        Call<ServiceResponse<Object>> call = ApiClient.getSodexoApiClient().registerWithCard(param);
        call.enqueue(new Callback<ServiceResponse<Object>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Object>> call, Response<ServiceResponse<Object>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        callback.onError(response.body().getMessage());
                    } else {
                        callback.onSuccess("");
                    }
                } else {
                    callback.onError("Ocurrio un error. Intentelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Object>> call, Throwable t) {
                callback.onError("Ocurrio un error. Intentelo nuevamente");
            }
        });
    }

    @Override
    public void getRegisterWithoutCard(String dni, String ruc, String password, String confirmPassword, String email, String name, String lastName, final RepositoryCallback callback) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("DNI", dni);
        param.put("Nombres", name);
        param.put("Apellidos", lastName);
        param.put("CorreoElectronico", email);
        param.put("Clave", password);
        param.put("ReClave", confirmPassword);
        param.put("AppToken", "asdasdasdasdasdsdasdasd");
        param.put("Dispositivo", "AND");


        Call<ServiceResponse<Object>> call = ApiClient.getSodexoApiClient().registerWithoutCard(param);
        call.enqueue(new Callback<ServiceResponse<Object>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Object>> call, Response<ServiceResponse<Object>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        callback.onError(response.body().getMessage());
                    } else {
                        callback.onSuccess("");
                    }
                } else {
                    callback.onError("Ocurrio un error. Intentelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Object>> call, Throwable t) {
                callback.onError("Ocurrio un error. Intentelo nuevamente");
            }
        });
    }

    @Override
    public void getQuizList(String dni, final RepositoryCallback callback) {
        Call<List<QuizEntityData>> call = ApiClient.getSodexoApiClient().getQuizzes(dni);
        call.enqueue(new Callback<List<QuizEntityData>>() {
            @Override
            public void onResponse(Call<List<QuizEntityData>> call, Response<List<QuizEntityData>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Ocurrio un error. Intentelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<List<QuizEntityData>> call, Throwable t) {
                callback.onError("Ocurrio un error. Intentelo nuevamente");
            }
        });
    }

    @Override
    public void getQuestions(String dni, int quizId, final RepositoryCallback callback) {
        Call<QuizDetailEntityData> call = ApiClient.getSodexoApiClient().getQuizDetail(dni, quizId);
        call.enqueue(new Callback<QuizDetailEntityData>() {
            @Override
            public void onResponse(Call<QuizDetailEntityData> call, Response<QuizDetailEntityData> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Ocurrio un error. intentelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<QuizDetailEntityData> call, Throwable t) {
                callback.onError("Ocurrio un error. intentelo nuevamente");
            }
        });
    }

    @Override
    public void sendReponseQuiz(String dni, int quizId, List<QuizResponseEntityData> list, final RepositoryCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("EncuestaId", String.valueOf(quizId));
        params.put("ListOpciones", list);
        params.put("Usuario", dni);
        Log.d("wat", params.toString());
        Call<ValidatedQuizResponse> call = ApiClient.getSodexoApiClient().sendResponse(params);
        call.enqueue(new Callback<ValidatedQuizResponse>() {
            @Override
            public void onResponse(Call<ValidatedQuizResponse> call, Response<ValidatedQuizResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isCorrect()) {
                        callback.onSuccess(null);
                    } else {
                        callback.onError(response.body().getMessage());

                    }
                } else {
                    callback.onError("Ocurrio un error. intentelo nuevamente");

                }
            }

            @Override
            public void onFailure(Call<ValidatedQuizResponse> call, Throwable t) {
                callback.onError("Ocurrio un error. intentelo nuevamente");

            }
        });
    }
}
