package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.QuizEntity;

/**
 * Created by ronaldvelasquez on 13/12/16.
 */

public interface QuizView {
    void showLoading();

    void hideLoading();

    void showError(String error);

    void showQuizList(List<QuizEntity> list);

    void showQuizDetail(QuizEntity quizEntity);
}
