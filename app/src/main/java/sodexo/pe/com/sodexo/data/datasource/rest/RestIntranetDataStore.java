package sodexo.pe.com.sodexo.data.datasource.rest;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Excluder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.IntranetDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.retrofit.ApiClient;
import sodexo.pe.com.sodexo.data.model.BlockingReasonEntityData;
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
import sodexo.pe.com.sodexo.data.model.ReplenishmentAmountEntityData;
import sodexo.pe.com.sodexo.data.model.ServiceResponse;
import sodexo.pe.com.sodexo.data.model.ShippingAddressEntityData;
import sodexo.pe.com.sodexo.data.model.UserEntityData;
import sodexo.pe.com.sodexo.data.model.ValidatedQuizResponse;
import sodexo.pe.com.sodexo.domain.entity.ReplacementCardEntity;
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
        Log.d("Dni:","-"+dni+"-");

        Call<ServiceResponse<List<CardEntityData>>> call = ApiClient.getSodexoIntranetApiClient().getCardsNumber(dni);
        call.enqueue(new Callback<ServiceResponse<List<CardEntityData>>>() {
            @Override
            public void onResponse(Call<ServiceResponse<List<CardEntityData>>> call, Response<ServiceResponse<List<CardEntityData>>> response) {
                if (response.isSuccessful()) {
                    Log.d("Obtiene al tipo tarj","entro1");
                    repositoryCallback.onSuccess(response.body().getData());
                } else {
                    Log.d("Obtiene cards1","obtiene tarjetas1");
                    repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");

                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<List<CardEntityData>>> call, Throwable t) {
                Log.d("Obtiene cards fail","obtiene fail");
                repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");

            }
        });
    }

    @Override
    public void getCardDetail(String dni, String cardNumber, final RepositoryCallback callback) {
        Log.d("Dni:","-"+dni+"-");
        Log.d("CardNumber:","-"+cardNumber+"-");

        Call<CardDetailEntityData> call = ApiClient.getSodexoIntranetApiClient().getCreditCard(dni, cardNumber);
        call.enqueue(new Callback<CardDetailEntityData>() {
            @Override
            public void onResponse(Call<CardDetailEntityData> call, Response<CardDetailEntityData> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.d("Obtiene fail","obtiene tarjetas correcto");
                        callback.onSuccess(response.body());
                    } catch (Exception ex) {
                        ex.printStackTrace();

                        callback.onError("Tarjeta Bloqueada");

                    }
                } else {
                    Log.d("Obtiene cards dertail1","obtiene tarjetas detail1");
                    callback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");

                }

            }

            @Override
            public void onFailure(Call<CardDetailEntityData> call, Throwable t) {
                Log.d("Obtiene fail","obtiene tarjetas detail 2");
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
                        Log.d("Obtiene tipo de tarjeta","entro1");
                        repositoryCallback.onSuccess(response.body().getData());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Log.d("Obtiene al tipo taa1","entro1");
                        repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");

                    }
                } else {
                    Log.d("Obtiene al tipo tara2","entro2");
                    repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");

                }

            }

            @Override
            public void onFailure(Call<ServiceResponse<List<CardTypeEntitiyData>>> call, Throwable t) {
                Log.d("Obtiene al tipo tara3","entro3");
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
                        Log.d("Obtiene al ul movos","entro1");
                        callback.onSuccess(response.body());
                    } catch (Exception ex) {
                        Log.d("Obtiene al casi","entro1");
                        callback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");

                    }
                } else {
                    Log.d("Obtiene al casi2","entro2");
                    callback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");

                }
            }

            @Override
            public void onFailure(Call<LastMovementsResponse> call, Throwable t) {
                Log.d("Obtiene al casi3","entro3");
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
        Log.wtf("DATA->", "->" + dni + " - " + cardNumber + " - " + password + " - " + newPassword + " - " + repeatPassword);
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
                    repositoryCallback.onSuccess(response.body().getMessage());
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
        Log.d("PersonalInfo", params.toString());
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

    @Override
    public void blockCard(String cardNumber, String reasonId, final RepositoryCallback callback) {
        /*
        Map<String, String> params = new HashMap<String, String>();
        params.put("numeroTarjeta", cardNumber);
        */

        Call<ServiceResponse<Object>> call = ApiClient.getSodexoIntranetApiClient().blockCard(cardNumber, reasonId);
        call.enqueue(new Callback<ServiceResponse<Object>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Object>> call, Response<ServiceResponse<Object>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().isError()) {
                            callback.onError(response.body().getMessage());
                        } else {
                            callback.onSuccess(response.body().getMessage());
                        }
                    } else {
                        callback.onError("Ocurrio un error al momento de realizar su petición, por favor inténtelo nuevamente.");
                    }
                } else {
                    callback.onError("Ocurrio un error al momento de realizar su petición, por favor inténtelo nuevamente.");
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Object>> call, Throwable t) {
                callback.onError("Ocurrio un error. intentelo nuevamente");
            }
        });
    }

    @Override
    public void getReplacementCardNumbers(String dni, final RepositoryCallback repositoryCallback) {
        Call<ServiceResponse<List<CardEntityData>>> call = ApiClient.getSodexoIntranetApiClient().getReplacementCardNumbers(dni);
        call.enqueue(new Callback<ServiceResponse<List<CardEntityData>>>() {
            @Override
            public void onResponse(Call<ServiceResponse<List<CardEntityData>>> call, Response<ServiceResponse<List<CardEntityData>>> response) {
                if (response.isSuccessful()) {
                    repositoryCallback.onSuccess(response.body().getData());
                } else {
                    if (response.errorBody() != null) {
                        try {
                            ServiceResponse serviceResponse = new Gson().fromJson(response.errorBody().string(), ServiceResponse.class);
                            if(serviceResponse != null)
                                repositoryCallback.onError(serviceResponse.getMessage());
                            else
                                repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                        } catch (Exception e) {
                            repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                            Log.wtf("ERROR!!! ->" , e.getMessage());
                        }
                    } else
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
    public void getBlockingReasons(final RepositoryCallback repositoryCallback) {
        Call<List<BlockingReasonEntityData>> call = ApiClient.getSodexoIntranetApiClient().getBlockingReasons();
        call.enqueue(new Callback<List<BlockingReasonEntityData>>() {
            @Override
            public void onResponse(Call<List<BlockingReasonEntityData>> call, Response<List<BlockingReasonEntityData>> response) {
                if (response.isSuccessful()) {
                    repositoryCallback.onSuccess(response.body());
                } else {
                    repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<List<BlockingReasonEntityData>> call, Throwable t) {
                repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getShippingAddress(String cardNumber, String deliveryId, final RepositoryCallback repositoryCallback) {
        Call<ServiceResponse<List<ShippingAddressEntityData>>> call = ApiClient.getSodexoIntranetApiClient().getShippingAddress(cardNumber, deliveryId);
        call.enqueue(new Callback<ServiceResponse<List<ShippingAddressEntityData>>>() {
            @Override
            public void onResponse(Call<ServiceResponse<List<ShippingAddressEntityData>>> call, Response<ServiceResponse<List<ShippingAddressEntityData>>> response) {
                if (response.isSuccessful()) {
                    repositoryCallback.onSuccess(response.body().getData());
                } else {
                    repositoryCallback.onError("Ocurrió un error al momento de realizar su transacción. Inténtelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<List<ShippingAddressEntityData>>> call, Throwable t) {
                repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
            }
        });
    }

    @Override
    public void getReplenishmentAmount(String cardNumber, String ubigeo, final RepositoryCallback repositoryCallback) {
        Call<ServiceResponse<List<ReplenishmentAmountEntityData>>> call = ApiClient.getSodexoIntranetApiClient().getReplenishmentAmount(cardNumber, ubigeo);
        call.enqueue(new Callback<ServiceResponse<List<ReplenishmentAmountEntityData>>>() {
            @Override
            public void onResponse(Call<ServiceResponse<List<ReplenishmentAmountEntityData>>> call, Response<ServiceResponse<List<ReplenishmentAmountEntityData>>> response) {
                if (response.isSuccessful()) {
                    repositoryCallback.onSuccess(response.body().getData());
                } else {
                    if (response.errorBody() != null) {
                        try {
                            ServiceResponse serviceResponse = new Gson().fromJson(response.errorBody().string(), ServiceResponse.class);
                            if(serviceResponse != null)
                                repositoryCallback.onError(serviceResponse.getMessage());
                            else
                                repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                        } catch (Exception e) {
                            repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                            Log.wtf("ERROR!!! ->" , e.getMessage());
                        }
                    } else
                        repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<List<ReplenishmentAmountEntityData>>> call, Throwable t) {
                repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
            }
        });
    }

    @Override
    public void replaceCard(ReplacementCardEntity replacementCardEntity, final RepositoryCallback repositoryCallback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("LugarEntrega", replacementCardEntity.getDeliveryPlace());
        params.put("Direccion1", replacementCardEntity.getAddress1());
        params.put("Direccion2", replacementCardEntity.getAddress1());
        params.put("NomContacto", replacementCardEntity.getContactName());
        params.put("Telefono", replacementCardEntity.getPhoneNumber());
        params.put("Region", replacementCardEntity.getDepartmentId());
        params.put("Provincia", replacementCardEntity.getProvinceId());
        params.put("Distrito", replacementCardEntity.getDistrictId());
        params.put("NroTarjeta", replacementCardEntity.getCardNumber());
        params.put("CorreoElectronico", replacementCardEntity.getEmail());

        Call<ServiceResponse<Object>> call = ApiClient.getSodexoIntranetApiClient().replaceCard(params);
        call.enqueue(new Callback<ServiceResponse<Object>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Object>> call, Response<ServiceResponse<Object>> response) {
                if(response.isSuccessful()){
                    repositoryCallback.onSuccess(response.body().getMessage());
                }else{
                    if (response.errorBody() != null) {
                        try {
                            ServiceResponse serviceResponse = new Gson().fromJson(response.errorBody().string(), ServiceResponse.class);
                            if(serviceResponse != null)
                                repositoryCallback.onError(serviceResponse.getMessage());
                            else
                                repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                        } catch (Exception e) {
                            repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                            Log.wtf("ERROR!!! ->" , e.getMessage());
                        }
                    } else
                        repositoryCallback.onError("Ocurrio un error al momento de realizar su transacción. Inténtelo nuevamente");
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Object>> call, Throwable t) {
                repositoryCallback.onError("Ocurrió un error al momento de realizar su transacción. Inténtelo nuevamente");
                t.printStackTrace();
            }
        });
    }
}
