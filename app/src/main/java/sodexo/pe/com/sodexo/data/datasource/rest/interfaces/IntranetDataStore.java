package sodexo.pe.com.sodexo.data.datasource.rest.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.data.model.QuizResponseEntityData;
import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;

/**
 * Created by RONALD on 11/10/2016.
 */

public interface IntranetDataStore {
    void login(String dni, String password, RepositoryCallback repositoryCallback);

    void getCards(String dni, RepositoryCallback repositoryCallback);

    void getCardDetail(String dni, String cardNumber, RepositoryCallback callback);

    void getCardsType(String dni, RepositoryCallback repositoryCallback);

    void getLastMovements(String dni, String cardTypeId, RepositoryCallback callback);

    void changePasswordWeb(String dni, String password, String newPassword, RepositoryCallback repositoryCallback);

    void changePasswordCard(String dni, String cardNumber, String password, String newPassword, String repeatPassword, RepositoryCallback repositoryCallback);

    void getUserInfo(String dni, String ruc, RepositoryCallback callback);

    void updateInfo(String dni, String address, String department, String province, String district, String email, String phone, String cellPhone, String cargo, String sex, RepositoryCallback repositoryCallback);

    void getCellInfo(String dni, RepositoryCallback callback);

    void updateCellInfo(String dni, String cellphoneNumber, String operator, String active, RepositoryCallback repositoryCallback);

    void getOptionsnew(String userId, RepositoryCallback repositoryCallback);

    void getListBlog(String userId, RepositoryCallback callback);

    void getRegisterWithCard(String dni, String card, String password, String confirmPassword, String email, RepositoryCallback callback);

    void getRegisterWithoutCard(String dni, String ruc, String password, String confirmPassword, String email, String name, String lastName, RepositoryCallback callback);

    void getQuizList(String dni, RepositoryCallback callback);

    void getQuestions(String dni, int quizId, RepositoryCallback callback);

    void sendReponseQuiz(String dni, int quizId, List<QuizResponseEntityData> list, RepositoryCallback callback);

    void replaceCard(String LugarEntrega, String Direccion1, String NomContacto, String Telefono, String Region, String Provincia, String Distrito, String Direccion2, String NroTarjeta, RepositoryCallback callback);

    void blockCard(String cardNumber, RepositoryCallback callback);
}