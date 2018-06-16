package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.data.model.QuizDetailEntityData;

/**
 * Created by ronaldvelasquez on 14/12/16.
 */

public interface GetQuestionsInterface {
    void onGetQuestionsSuccess(QuizDetailEntityData object);

    void onGetQuestionsError(String error);
}
