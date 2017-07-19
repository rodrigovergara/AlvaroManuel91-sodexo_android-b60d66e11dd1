package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.data.model.QuizDetailEntityData;

/**
 * Created by ronaldvelasquez on 14/12/16.
 */

public interface QuizDetailView {
    void showLoading();

    void hideloading();

    void showError(String error);

    void showQuestions(QuizDetailEntityData list);

    void showSuccess();

}
