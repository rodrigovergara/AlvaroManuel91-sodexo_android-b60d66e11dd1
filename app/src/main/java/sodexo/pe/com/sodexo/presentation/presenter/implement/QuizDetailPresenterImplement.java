package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.model.QuizDetailEntityData;
import sodexo.pe.com.sodexo.data.model.QuizResponseEntityData;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.interactor.QuizDetailInractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.QuizDetailInractorImplement;
import sodexo.pe.com.sodexo.presentation.interfaces.QuizDetailView;
import sodexo.pe.com.sodexo.presentation.model.GetQuestionsInterface;
import sodexo.pe.com.sodexo.presentation.model.SendResponseQuizInterface;
import sodexo.pe.com.sodexo.presentation.presenter.QuizDetailPresenter;

/**
 * Created by ronaldvelasquez on 14/12/16.
 */

public class QuizDetailPresenterImplement implements QuizDetailPresenter {
    private QuizDetailView view;
    private QuizDetailInractor interactor;
    public QuizDetailPresenterImplement(QuizDetailView view) {
        this.view = view;
        interactor = new QuizDetailInractorImplement(new IntranetDataRepository(new IntranetDataMapper()));
    }

    @Override
    public void getQuestions(int quizId) {
        view.showLoading();
        interactor.getQuestions(quizId, new GetQuestionsInterface() {
            @Override
            public void onGetQuestionsSuccess(QuizDetailEntityData list) {
                view.hideloading();
                view.showQuestions(list);
            }

            @Override
            public void onGetQuestionsError(String error) {
                view.hideloading();
                view.showError(error);
            }
        });
    }

    @Override
    public void sendResponse(int quizId, List<QuizResponseEntityData> list) {
        view.showLoading();
        interactor.sendReponse(quizId, list, new SendResponseQuizInterface() {
            @Override
            public void onSendResponseSucces() {
                view.hideloading();
                view.showSuccess();
            }

            @Override
            public void onSnedResponseError(String error) {
                view.hideloading();
                view.showError(error);
            }
        });
    }
}
