package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.QuizEntity;

/**
 * Created by ronaldvelasquez on 13/12/16.
 */

public interface GetQuizListInterface {
    void onGetQuizListSuccess(List<QuizEntity> quizEntities);
    void onGetQuizListError(String error);
}
