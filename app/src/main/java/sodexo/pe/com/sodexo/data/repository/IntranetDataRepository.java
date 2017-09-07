package sodexo.pe.com.sodexo.data.repository;

import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.List;

import sodexo.pe.com.sodexo.data.datasource.rest.RestIntranetDataStore;
import sodexo.pe.com.sodexo.data.datasource.rest.interfaces.IntranetDataStore;
import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
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
import sodexo.pe.com.sodexo.data.model.UserEntityData;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.interfaces.GetAllMovements;
import sodexo.pe.com.sodexo.presentation.interfaces.GetCellInfoInterface;
import sodexo.pe.com.sodexo.presentation.model.BaseParentInterface;
import sodexo.pe.com.sodexo.presentation.model.ChangePasswordInterface;
import sodexo.pe.com.sodexo.presentation.model.GetAllTypeCards;
import sodexo.pe.com.sodexo.presentation.model.GetBlockingReasonsInterface;
import sodexo.pe.com.sodexo.presentation.model.GetBlogListInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardDetailInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardsInterface;
import sodexo.pe.com.sodexo.presentation.model.GetIntranetOptionInterface;
import sodexo.pe.com.sodexo.presentation.model.GetQuestionsInterface;
import sodexo.pe.com.sodexo.presentation.model.GetQuizListInterface;
import sodexo.pe.com.sodexo.presentation.model.GetUserInfoInterface;
import sodexo.pe.com.sodexo.presentation.model.LoginInterface;
import sodexo.pe.com.sodexo.presentation.model.RegisterInterface;
import sodexo.pe.com.sodexo.presentation.model.SendResponseQuizInterface;
import sodexo.pe.com.sodexo.presentation.model.UpdateCellInfoInterface;
import sodexo.pe.com.sodexo.presentation.model.UpdateUserInfoOnterface;

/**
 * Created by RONALD on 12/10/2016.
 */

public class IntranetDataRepository implements IntranetRepository {
    private IntranetDataMapper dataMapper;

    public IntranetDataRepository(IntranetDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @Override
    public void login(String dni, String password, final LoginInterface callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.login(dni, password, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onLoginError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                String json = new Gson().toJson((LoginEntityData) object);
                PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).edit().putString(SodexoApplication.USER_DATA, json).commit();
                callback.onLoginSuccess();
            }
        });
    }

    @Override
    public void getCards(String dni, final GetCardsInterface callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getCards(dni, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onGetCardsError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                callback.onGetCardsSucces(dataMapper.trasnformToCardsEntity((List<CardEntityData>) object));
            }
        });
    }

    @Override
    public void getCardDetail(String dni, String cardNumber, final GetCardDetailInterface callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getCardDetail(dni, cardNumber, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onGetCardDetailError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                callback.onGetCardDetailSuccess(dataMapper.trasnformToCardsDetailEntity((CardDetailEntityData) object));
            }
        });
    }

    @Override
    public void getCardsType(String dni, final GetAllTypeCards callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getCardsType(dni, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onGetAllTypeCardsError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                callback.onGetAllTypeCardsSuccess(dataMapper.trasnformToCardTypeEntity((List<CardTypeEntitiyData>) object));
            }
        });
    }

    @Override
    public void getLastMovement(String dni, String cardTypeId, final GetAllMovements callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getLastMovements(dni, cardTypeId, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onGetAllMovementsError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                callback.onGetAllMovementsSuccess(dataMapper.trasnformToMovementEntity((LastMovementsResponse) object));
            }
        });
    }

    @Override
    public void changePasswordWeb(String dni, String password, String newPassword, final ChangePasswordInterface callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.changePasswordWeb(dni, password, newPassword, new RepositoryCallback() {
            @Override
            public void onError(Object message) {
                callback.onChangePasswordError(String.valueOf(message));
            }

            @Override
            public void onSuccess(Object message) {
                callback.onChangePasswordSuccess(String.valueOf(message));
            }
        });
    }

    @Override
    public void changePasswordCard(String dni, String cardNumber, String password, String newPassword, String repeatPassword, final ChangePasswordInterface callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.changePasswordCard(dni, cardNumber, password, newPassword, repeatPassword, new RepositoryCallback() {
            @Override
            public void onError(Object message) {
                callback.onChangePasswordError(String.valueOf(message));
            }

            @Override
            public void onSuccess(Object message) {
                callback.onChangePasswordSuccess(String.valueOf(message));
            }
        });
    }

    @Override
    public void getUserInfo(String dni, String ruc, final GetUserInfoInterface callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getUserInfo(dni, ruc, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onGetUserInfoError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                callback.onGetUserInfoSuccess(dataMapper.transformToUserEntity((UserEntityData) object));
            }
        });
    }

    @Override
    public void updateInfo(String dni, String address, String department, String province, String district, String email, String phone, String cellPhone, String cargo, String sex, final UpdateUserInfoOnterface callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.updateInfo(dni, address, department, province, district, email, phone, cellPhone, cargo, sex, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onUpdateError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                callback.onUpdateSucces();
            }
        });
    }

    @Override
    public void getCellInfo(String dni, final GetCellInfoInterface callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getCellInfo(dni, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onGetCellInfoError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                callback.onGetCellInfoSuccess(dataMapper.transformToCellInfoEntity((CellInfoEntityData) object));
            }
        });
    }

    @Override
    public void getOptions(String userId, final GetIntranetOptionInterface callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getOptionsnew(userId, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onGetIntranetOptionError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                callback.onGetIntranetOptionSuccess(dataMapper.transformToOptionEntity((List<IntranetOptionEntityData>) object));
            }
        });
    }

    @Override
    public void getBlogList(String dni, final GetBlogListInterface getBlogListInterface) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getListBlog(dni, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                getBlogListInterface.onGetBlogListError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                getBlogListInterface.onGetBlogListSuccess(dataMapper.transformToBlogEntity((List<BlogEntityData>) object));
            }
        });
    }

    @Override
    public void registerUserWithCard(String dni, String card, String password, String confirmPassword, String email, final RegisterInterface registerInterface) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getRegisterWithCard(dni, card, password, confirmPassword, email, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                registerInterface.onRegisterError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                registerInterface.onRegisterSuccess();
            }
        });
    }

    @Override
    public void registerUserWithoutCard(String dni, String ruc, String password, String confirmPassword, String email, String name, String lastName, final RegisterInterface registerInterface) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getRegisterWithoutCard(dni, ruc, password, confirmPassword, email, name, lastName, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                registerInterface.onRegisterError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                registerInterface.onRegisterSuccess();
            }
        });
    }

    @Override
    public void getQuizList(String dni, final GetQuizListInterface getQuizListInterface) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getQuizList(dni, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                getQuizListInterface.onGetQuizListError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                getQuizListInterface.onGetQuizListSuccess(dataMapper.transformToQuizEntity((List<QuizEntityData>) object));
            }
        });
    }

    @Override
    public void getQuestions(String dni, int quizId, final GetQuestionsInterface getQuestionsInterface) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getQuestions(dni, quizId, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                getQuestionsInterface.onGetQuestionsError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                getQuestionsInterface.onGetQuestionsSuccess((QuizDetailEntityData) object);
            }
        });
    }

    @Override
    public void senReponseQuiz(String dni, int quizId, List<QuizResponseEntityData> list, final SendResponseQuizInterface sendResponseQuizInterface) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.sendReponseQuiz(dni, quizId, list, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                sendResponseQuizInterface.onSnedResponseError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                sendResponseQuizInterface.onSendResponseSucces();
            }
        });
    }

    @Override
    public void updateCellInfo(String dni, String cellphoneNumber, String operator, String active, final UpdateCellInfoInterface callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.updateCellInfo(dni, cellphoneNumber, operator, active, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onUpdateCellInfoError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                callback.onUpdateCellInfoSuccess();
            }
        });
    }

    @Override
    public void blockCard(String cardNumber, String reasonId, final BaseParentInterface callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.blockCard(cardNumber, reasonId, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                callback.onSuccess(String.valueOf(object));
            }
        });
    }

    @Override
    public void getReplacementCardNumbers(String dni,final GetCardsInterface callback) {
        IntranetDataStore dataStore = new RestIntranetDataStore();
        dataStore.getReplacementCardNumbers(dni, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onGetCardsError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                callback.onGetCardsSucces(dataMapper.trasnformToCardsEntity((List<CardEntityData>) object));
            }
        });
    }

    @Override
    public void getBlockingReasons(final GetBlockingReasonsInterface callback) {
        IntranetDataStore intranetDataStore = new RestIntranetDataStore();
        intranetDataStore.getBlockingReasons(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                callback.onGetBlockingReasonsError(String.valueOf(object));
            }

            @Override
            public void onSuccess(Object object) {
                callback.onGetBlockingReasonsSuccess(dataMapper.trasnformToBlockingReasonsEntity((List<BlockingReasonEntityData>)object));
            }
        });
    }
}
