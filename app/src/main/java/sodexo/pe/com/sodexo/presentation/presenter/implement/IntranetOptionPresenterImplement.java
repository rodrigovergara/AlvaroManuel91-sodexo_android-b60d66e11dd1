package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.entity.OptionIntranetEntity;
import sodexo.pe.com.sodexo.domain.interactor.IntranetOptionInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.IntranetOptionInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.IntranetOptionView;
import sodexo.pe.com.sodexo.presentation.model.GetIntranetOptionInterface;
import sodexo.pe.com.sodexo.presentation.presenter.IntranetOptionPresenter;

/**
 * Created by ronaldvelasquez on 9/12/16.
 */

public class IntranetOptionPresenterImplement implements IntranetOptionPresenter {
    private IntranetOptionView view;
    private IntranetOptionInteractor interactor;

    public IntranetOptionPresenterImplement(IntranetOptionView view) {
        this.view = view;
        IntranetRepository repository = new IntranetDataRepository(new IntranetDataMapper());
        this.interactor = new IntranetOptionInteractorImplement(repository);
    }

    @Override
    public void getIntranetOptions() {
        view.showLoading();
        interactor.getOptions(new GetIntranetOptionInterface(){
            @Override
            public void onGetIntranetOptionSuccess(List<OptionIntranetEntity> list) {
                view.hideLoading();
                view.showOptions(list);
            }

            @Override
            public void onGetIntranetOptionError(String error) {
                view.hideLoading();
                view.showError(error);
            }
        });

    }

}
