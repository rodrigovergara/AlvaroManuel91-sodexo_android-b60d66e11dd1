package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.entity.QuizEntity;
import sodexo.pe.com.sodexo.domain.interactor.QuizInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.QuizInteractorImplement;
import sodexo.pe.com.sodexo.presentation.interfaces.QuizView;
import sodexo.pe.com.sodexo.presentation.model.GetQuizListInterface;
import sodexo.pe.com.sodexo.presentation.presenter.QuizPresenter;

/**
 * Created by ronaldvelasquez on 13/12/16.
 */

public class QuizPresenterImplement implements QuizPresenter {
    private QuizView view;
    private QuizInteractor interactor;
    public QuizPresenterImplement(QuizView view) {
        this.view = view;
        interactor = new QuizInteractorImplement(new IntranetDataRepository(new IntranetDataMapper()));
    }

    @Override
    public void getQuizList() {
        view.showLoading();
        interactor.getQuizList(new GetQuizListInterface() {
            @Override
            public void onGetQuizListSuccess(List<QuizEntity> quizEntities) {
                view.hideLoading();
                view.showQuizList(quizEntities);
            }

            @Override
            public void onGetQuizListError(String error) {
                view.hideLoading();
                view.showError(error);
            }
        });
    }
}
