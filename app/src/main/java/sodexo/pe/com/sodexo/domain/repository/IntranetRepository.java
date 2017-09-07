package sodexo.pe.com.sodexo.domain.repository;

import java.util.List;

import sodexo.pe.com.sodexo.data.model.QuizResponseEntityData;
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

public interface IntranetRepository {
    void login(String dni, String password, LoginInterface loginInterface);

    void getCards(String dni, GetCardsInterface callback);

    void getCardDetail(String dni, String cardNumber, GetCardDetailInterface callback);

    void getCardsType(String dni, GetAllTypeCards callback);

    void getLastMovement(String dni, String cardTypeId, GetAllMovements callback);

    void changePasswordWeb(String dni, String password, String newPassword, ChangePasswordInterface callback);

    void changePasswordCard(String dni, String cardNumber, String password, String newPassword, String repeatPassword, ChangePasswordInterface callback);

    void getUserInfo(String dni, String ruc, GetUserInfoInterface callback);

    void updateInfo(String dni, String address, String department, String province, String district, String email, String phone, String cellPhone, String cargo, String sex, UpdateUserInfoOnterface callback);

    void getCellInfo(String dni, GetCellInfoInterface callback);

    void updateCellInfo(String dni, String cellphoneNumber, String operator, String active, UpdateCellInfoInterface callback);

    void getOptions(String dni, GetIntranetOptionInterface callback);

    void getBlogList(String dni, GetBlogListInterface getBlogListInterface);

    void registerUserWithCard(String dni, String card, String password, String confirmPassword, String email, RegisterInterface registerInterface);

    void registerUserWithoutCard(String dni, String ruc, String password, String confirmPassword, String email, String name, String lastName, RegisterInterface registerInterface);

    void getQuizList(String dni, GetQuizListInterface getQuizListInterface);

    void getQuestions(String dni, int quizId, GetQuestionsInterface getQuestionsInterface);

    void senReponseQuiz(String dni, int quizId, List<QuizResponseEntityData> list, SendResponseQuizInterface sendResponseQuizInterface);

    void blockCard(String cardNumber, String reasonId, BaseParentInterface callback);

    void getReplacementCardNumbers(String dni, GetCardsInterface callback);

    void getBlockingReasons(GetBlockingReasonsInterface callback);
}
