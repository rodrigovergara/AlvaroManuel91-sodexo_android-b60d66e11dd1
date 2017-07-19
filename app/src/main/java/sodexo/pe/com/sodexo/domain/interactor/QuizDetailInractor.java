package sodexo.pe.com.sodexo.domain.interactor;

import java.util.List;

import sodexo.pe.com.sodexo.data.model.QuizResponseEntityData;
import sodexo.pe.com.sodexo.presentation.model.GetQuestionsInterface;
import sodexo.pe.com.sodexo.presentation.model.SendResponseQuizInterface;

/**
 * Created by ronaldvelasquez on 14/12/16.
 */

public interface QuizDetailInractor {
    void getQuestions(int quizId, GetQuestionsInterface getQuestionsInterface);

    void sendReponse(int quizId, List<QuizResponseEntityData> list, SendResponseQuizInterface sendResponseQuizInterface);
}
