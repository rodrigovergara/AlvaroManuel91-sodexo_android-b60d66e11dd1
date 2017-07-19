package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;

import com.google.gson.Gson;

import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.domain.interactor.QuizInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.model.GetQuizListInterface;

/**
 * Created by ronaldvelasquez on 13/12/16.
 */

public class QuizInteractorImplement implements QuizInteractor {
    private IntranetRepository repository;

    public QuizInteractorImplement(IntranetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getQuizList(GetQuizListInterface getQuizListInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            repository.getQuizList(data.getDni(), getQuizListInterface);
        }

    }
}
