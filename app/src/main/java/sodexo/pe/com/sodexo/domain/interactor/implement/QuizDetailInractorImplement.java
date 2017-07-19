package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.List;

import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.data.model.QuizResponseEntityData;
import sodexo.pe.com.sodexo.domain.interactor.QuizDetailInractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.model.GetQuestionsInterface;
import sodexo.pe.com.sodexo.presentation.model.SendResponseQuizInterface;

/**
 * Created by ronaldvelasquez on 14/12/16.
 */

public class QuizDetailInractorImplement implements QuizDetailInractor {
    private IntranetRepository repository;

    public QuizDetailInractorImplement(IntranetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getQuestions(int quizId, GetQuestionsInterface getQuestionsInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            repository.getQuestions(data.getDni(), quizId, getQuestionsInterface);
        }
    }

    @Override
    public void sendReponse(int quizId, List<QuizResponseEntityData> list, SendResponseQuizInterface sendResponseQuizInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            repository.senReponseQuiz(data.getDni(), quizId, list, sendResponseQuizInterface);
        }
    }
}
