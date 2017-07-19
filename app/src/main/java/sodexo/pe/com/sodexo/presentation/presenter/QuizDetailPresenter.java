package sodexo.pe.com.sodexo.presentation.presenter;

import java.util.List;

import sodexo.pe.com.sodexo.data.model.QuizResponseEntityData;

/**
 * Created by ronaldvelasquez on 14/12/16.
 */

public interface QuizDetailPresenter {
    void getQuestions(int quizId);

    void sendResponse(int quizId, List<QuizResponseEntityData> list);
}
