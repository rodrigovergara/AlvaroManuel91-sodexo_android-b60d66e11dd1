package sodexo.pe.com.sodexo.presentation.presenter.implement;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.entity.CellInfoEntity;
import sodexo.pe.com.sodexo.domain.entity.OperatorEntity;
import sodexo.pe.com.sodexo.domain.interactor.RegisterSmsServiceInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.RegisterSmsServiceInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.GetCellInfoInterface;
import sodexo.pe.com.sodexo.presentation.interfaces.RegisterSmsServiceView;
import sodexo.pe.com.sodexo.presentation.model.UpdateCellInfoInterface;
import sodexo.pe.com.sodexo.presentation.presenter.RegisterSmsServicePresenter;

/**
 * Created by RONALD on 13/10/2016.
 */

public class RegisterSmsServicePresenterImplement implements RegisterSmsServicePresenter {
    private RegisterSmsServiceView view;
    private RegisterSmsServiceInteractor interactor;
    public RegisterSmsServicePresenterImplement(RegisterSmsServiceView view) {
        IntranetRepository repository = new IntranetDataRepository(new IntranetDataMapper());
        this.view = view;
        interactor = new RegisterSmsServiceInteractorImplement(repository);
    }

    @Override
    public void getCellInfo() {
        view.showLoading();
        interactor.getCellInfo(new GetCellInfoInterface(){
            @Override
            public void onGetCellInfoSuccess(CellInfoEntity entity) {
                view.hideLoading();
                view.showInfo(entity);
            }

            @Override
            public void onGetCellInfoError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }

    @Override
    public void updateCellInfo(String cellNumber, OperatorEntity operatorEntity, String registerSms) {
        view.showLoading();
        interactor.updateCellInfo(cellNumber, operatorEntity.getId(), registerSms, new UpdateCellInfoInterface() {
            @Override
            public void onUpdateCellInfoSuccess() {
                view.hideLoading();
                view.showSuccess("Se ha actualizado correctamente");
            }

            @Override
            public void onUpdateCellInfoError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }
}
